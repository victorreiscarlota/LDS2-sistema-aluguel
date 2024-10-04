<script setup>
import { useToast } from 'primevue/usetoast';
import { useConfirm } from 'primevue/useconfirm';

definePageMeta({
  layout: 'home',
});

const confirm = useConfirm();
const toast = useToast();

const {
  data: creditosData,
  pending: creditosPending,
  refresh: creditosRefresh,
} = await useApiFetch('/creditos');

const formVisible = ref(false);
const formData = ref({
  data_concessao: null,
  valor: null,
});

const creditosTable = [
  { field: 'data_concessao', header: 'Data de Concessão' },
  { field: 'valor', header: 'Valor' },
];

const editCredito = (data) => {
  formData.value = { ...data };
  formVisible.value = true;
};

const confirmDeleteCredito = (id) => {
  confirm.require({
    header: 'Confirmação',
    message: 'Deseja realmente deletar este crédito?',
    icon: 'pi pi-exclamation-triangle',
    rejectProps: {
      label: 'Cancelar',
      severity: 'secondary',
      outlined: true,
    },
    acceptProps: {
      label: 'Confirmar',
      severity: 'danger',
    },
    accept: () => {
      deleteCredito(id);
    },
  });
};

const deleteCredito = async (id) => {
  try {
    await useApiFetch(`/creditos?id=${id}`, {
      method: 'DELETE',
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Crédito deletado com sucesso',
      life: 3000,
    });
    creditosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao deletar crédito',
      life: 3000,
    });
  }
};

const handleSubmitLoading = ref(false);

const postCredito = async () => {
  try {
    await useApiFetch('/creditos', {
      method: 'POST',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Crédito cadastrado com sucesso',
      life: 3000,
    });
    creditosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao cadastrar crédito',
      life: 3000,
    });
  } finally {
    handleCloseDialog();
    handleSubmitLoading.value = false;
  }
};

const putCredito = async () => {
  try {
    await useApiFetch(`/creditos?id=${formData.value.id}`, {
      method: 'PUT',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Crédito editado com sucesso',
      life: 3000,
    });
    creditosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao editar crédito',
      life: 3000,
    });
  } finally {
    handleCloseDialog();
    handleSubmitLoading.value = false;
  }
};

const handleSubmit = () => {
  handleSubmitLoading.value = true;

  if (formData.value.id) {
    putCredito();
  } else {
    postCredito();
  }
};

const handleOpenDialog = () => {
  formVisible.value = true;
};

const handleCloseDialog = () => {
  formData.value = {
    data_concessao: null,
    valor: null,
  };
  formVisible.value = false;
};

// Função para formatar o valor como moeda BRL
const formatBRL = (value) => {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
  }).format(value);
};
</script>

<template>
  <div class="container mx-auto">
    <Button class="rounded-none" @click="handleOpenDialog">
      <i class="pi pi-plus" />
      Adicionar crédito
    </Button>
  </div>

  <h3 v-if="creditosPending">Carregando...</h3>

  <div v-else class="card">
    <DataTable :value="creditosData" size="large">
      <template #header>
        <div class="flex flex-wrap items-center justify-between gap-2">
          <span class="text-xl font-bold">Créditos</span>
          <Button
            icon="pi pi-refresh"
            severity="primary"
            rounded
            @click="creditosRefresh"
          />
        </div>
      </template>
      <Column
        field="data_concessao"
        header="Data de Concessão"
        :body="
          (data) => new Date(data.data_concessao).toLocaleDateString('pt-BR')
        "
      />
      <Column
        field="valor"
        header="Valor"
        :body="(data) => formatBRL(data.valor)"
      />
      <Column>
        <template #body="{ data }">
          <Button icon="pi pi-trash" @click="confirmDeleteCredito(data.id)" />
          <Button icon="pi pi-pencil" class="ml-2" @click="editCredito(data)" />
        </template>
      </Column>
    </DataTable>

    <Dialog
      v-model:visible="formVisible"
      modal
      :header="`${formData.id ? 'Editar' : 'Adicionar'} crédito`"
      :style="{ width: '25rem' }"
      @hide="handleCloseDialog"
    >
      <span class="mb-8 block text-surface-500 dark:text-surface-400">
        <span v-if="formData.id">
          Preencha os campos abaixo para editar o crédito
        </span>
        <span v-else>
          Preencha os campos abaixo para adicionar um novo crédito
        </span>
      </span>

      <div class="mb-4 flex items-center gap-4">
        <label for="data_concessao" class="w-32 font-semibold">
          Data Concessão
        </label>
        <Calendar
          id="data_concessao"
          v-model="formData.data_concessao"
          dateFormat="dd/mm/yy"
          class="flex-auto"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="valor" class="w-32 font-semibold">Valor</label>
        <InputNumber
          id="valor"
          v-model="formData.valor"
          mode="currency"
          currency="BRL"
          locale="pt-BR"
          class="flex-auto"
        />
      </div>

      <div class="flex justify-end gap-2">
        <Button
          type="button"
          label="Cancelar"
          severity="secondary"
          @click="handleCloseDialog"
        />
        <Button
          type="button"
          :loading="handleSubmitLoading"
          label="Salvar"
          @click="handleSubmit"
        />
      </div>
    </Dialog>
  </div>
</template>
