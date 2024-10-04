<script setup>
import { useToast } from 'primevue/usetoast';
import { useConfirm } from 'primevue/useconfirm';

definePageMeta({
  layout: 'home',
});

const confirm = useConfirm();
const toast = useToast();

// Fetching contratos data
const {
  data: contratosData,
  pending: contratosPending,
  refresh: contratosRefresh,
} = await useApiFetch('/contratos');

// Fetching creditos data for select options
const { data: creditosData } = await useApiFetch('/creditos');

// Fetching automoveis data for select options
const { data: automoveisData } = await useApiFetch('/automoveis');

const formVisible = ref(false);
const formData = ref({
  dataContrato: null,
  tipo: '',
  automovelIds: [],
  creditoId: null,
});

const contratosTable = [
  { field: 'dataContrato', header: 'Data do Contrato' },
  { field: 'tipo', header: 'Tipo' },
  { field: 'creditoId', header: 'Crédito' },
  { field: 'automovelIds', header: 'Automóveis' },
];

const editContrato = (data) => {
  formData.value = { ...data };
  formVisible.value = true;
};

const confirmDeleteContrato = (id) => {
  confirm.require({
    header: 'Confirmação',
    message: 'Deseja realmente deletar este contrato?',
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
      deleteContrato(id);
    },
  });
};

const deleteContrato = async (id) => {
  try {
    await useApiFetch(`/contratos?id=${id}`, {
      method: 'DELETE',
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Contrato deletado com sucesso',
      life: 3000,
    });
    contratosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao deletar contrato',
      life: 3000,
    });
  }
};

const handleSubmitLoading = ref(false);

const postContrato = async () => {
  try {
    await useApiFetch('/contratos', {
      method: 'POST',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Contrato cadastrado com sucesso',
      life: 3000,
    });
    contratosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao cadastrar contrato',
      life: 3000,
    });
  } finally {
    handleCloseDialog();
    handleSubmitLoading.value = false;
  }
};

const putContrato = async () => {
  try {
    await useApiFetch(`/contratos?id=${formData.value.id}`, {
      method: 'PUT',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Contrato editado com sucesso',
      life: 3000,
    });
    contratosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao editar contrato',
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
    putContrato();
  } else {
    postContrato();
  }
};

const handleOpenDialog = () => {
  formVisible.value = true;
};

const handleCloseDialog = () => {
  formData.value = {
    dataContrato: null,
    tipo: '',
    automovelIds: [],
    creditoId: null,
  };
  formVisible.value = false;
};

// Função para formatar a data
const formatDate = (date) => {
  return new Date(date).toLocaleDateString('pt-BR');
};

// Função para obter o nome do crédito pelo ID
const getCreditoNome = (creditoId) => {
  const credito = creditosData.value.find((c) => c.id === creditoId);
  return credito ? `Crédito #${credito.id}` : 'N/A';
};

// Função para obter os nomes dos automóveis pelos IDs
const getAutomoveisNomes = (automovelIds) => {
  return automovelIds
    .map((id) => {
      const automovel = automoveisData.value.find((a) => a.id === id);
      return automovel ? automovel.modelo : 'N/A';
    })
    .join(', ');
};
</script>

<template>
  <div class="container mx-auto">
    <Button class="rounded-none" @click="handleOpenDialog">
      <i class="pi pi-plus" />
      Adicionar contrato
    </Button>
  </div>

  <h3 v-if="contratosPending">Carregando...</h3>

  <div v-else class="card">
    <DataTable :value="contratosData" size="large">
      <template #header>
        <div class="flex flex-wrap items-center justify-between gap-2">
          <span class="text-xl font-bold">Contratos</span>
          <Button
            icon="pi pi-refresh"
            severity="primary"
            rounded
            @click="contratosRefresh"
          />
        </div>
      </template>
      <Column
        field="dataContrato"
        header="Data do Contrato"
        :body="(data) => formatDate(data.dataContrato)"
      />
      <Column field="tipo" header="Tipo" />
      <Column
        field="creditoId"
        header="Crédito"
        :body="(data) => getCreditoNome(data.creditoId)"
      />
      <Column
        field="automovelIds"
        header="Automóveis"
        :body="(data) => getAutomoveisNomes(data.automovelIds)"
      />
      <Column>
        <template #body="{ data }">
          <Button icon="pi pi-trash" @click="confirmDeleteContrato(data.id)" />
          <Button
            icon="pi pi-pencil"
            class="ml-2"
            @click="editContrato(data)"
          />
        </template>
      </Column>
    </DataTable>

    <Dialog
      v-model:visible="formVisible"
      modal
      :header="`${formData.id ? 'Editar' : 'Adicionar'} contrato`"
      :style="{ width: '30rem' }"
      @hide="handleCloseDialog"
    >
      <span class="mb-8 block text-surface-500 dark:text-surface-400">
        <span v-if="formData.id">
          Preencha os campos abaixo para editar o contrato
        </span>
        <span v-else>
          Preencha os campos abaixo para adicionar um novo contrato
        </span>
      </span>

      <div class="mb-4 flex items-center gap-4">
        <label for="dataContrato" class="w-32 font-semibold">
          Data do Contrato
        </label>
        <Calendar
          id="dataContrato"
          v-model="formData.dataContrato"
          dateFormat="dd/mm/yy"
          class="flex-auto"
        />
      </div>
      <div class="mb-4 flex items-center gap-4">
        <label for="tipo" class="w-32 font-semibold">Tipo</label>
        <InputText
          id="tipo"
          v-model="formData.tipo"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-4 flex items-center gap-4">
        <label for="creditoId" class="w-32 font-semibold">Crédito</label>
        <Dropdown
          id="creditoId"
          v-model="formData.creditoId"
          :options="creditosData"
          optionLabel="id"
          optionValue="id"
          placeholder="Selecione um crédito"
          class="flex-auto"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="automovelIds" class="w-32 font-semibold">Automóveis</label>
        <MultiSelect
          id="automovelIds"
          v-model="formData.automovelIds"
          :options="automoveisData"
          optionLabel="modelo"
          optionValue="id"
          placeholder="Selecione os automóveis"
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
