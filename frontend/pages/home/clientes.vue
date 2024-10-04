<script setup>
import { useToast } from 'primevue/usetoast';
import { useConfirm } from 'primevue/useconfirm';

definePageMeta({
  layout: 'home',
});

const confirm = useConfirm();
const toast = useToast();

const {
  data: clientesData,
  pending: clientesPending,
  refresh: clientesRefresh,
} = await useApiFetch('/clientes');

const formVisible = ref(false);
const formData = ref({
  cpf: '',
  endereco: '',
  nome: '',
  profissao: '',
  rg: '',
});

const clientesTable = [
  { field: 'cpf', header: 'CPF' },
  { field: 'nome', header: 'Nome' },
  { field: 'endereco', header: 'Endereço' },
  { field: 'profissao', header: 'Profissão' },
  { field: 'rg', header: 'RG' },
];

const editCliente = (data) => {
  formData.value = { ...data };
  formVisible.value = true;
};

const confirmDeleteCliente = (id) => {
  confirm.require({
    header: 'Confirmação',
    message: 'Deseja realmente deletar este cliente?',
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
      deleteCliente(id);
    },
  });
};

const deleteCliente = async (id) => {
  try {
    await useApiFetch(`/clientes?id=${id}`, {
      method: 'DELETE',
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Cliente deletado com sucesso',
      life: 3000,
    });
    clientesRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao deletar cliente',
      life: 3000,
    });
  }
};

const handleSubmitLoading = ref(false);

const postCliente = async () => {
  try {
    await useApiFetch('/clientes', {
      method: 'POST',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Cliente cadastrado com sucesso',
      life: 3000,
    });
    clientesRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao cadastrar cliente',
      life: 3000,
    });
  } finally {
    handleCloseDialog();
    handleSubmitLoading.value = false;
  }
};

const putCliente = async () => {
  try {
    await useApiFetch(`/clientes?id=${formData.value.id}`, {
      method: 'PUT',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Cliente editado com sucesso',
      life: 3000,
    });
    clientesRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao editar cliente',
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
    putCliente();
  } else {
    postCliente();
  }
};

const handleOpenDialog = () => {
  formVisible.value = true;
};

const handleCloseDialog = () => {
  formData.value = {
    cpf: '',
    endereco: '',
    nome: '',
    profissao: '',
    rg: '',
  };
  formVisible.value = false;
};
</script>

<template>
  <div class="container mx-auto">
    <Button class="rounded-none" @click="handleOpenDialog">
      <i class="pi pi-plus" />
      Adicionar cliente
    </Button>
  </div>

  <h3 v-if="clientesPending">Carregando...</h3>

  <div v-else class="card">
    <DataTable :value="clientesData" size="large">
      <template #header>
        <div class="flex flex-wrap items-center justify-between gap-2">
          <span class="text-xl font-bold">Clientes</span>
          <Button
            icon="pi pi-refresh"
            severity="primary"
            rounded
            @click="clientesRefresh"
          />
        </div>
      </template>
      <Column
        v-for="col of clientesTable"
        :key="col.field"
        :field="col.field"
        :header="col.header"
      />
      <Column>
        <template #body="{ data }">
          <Button icon="pi pi-trash" @click="confirmDeleteCliente(data.id)" />
          <Button icon="pi pi-pencil" class="ml-2" @click="editCliente(data)" />
        </template>
      </Column>
    </DataTable>

    <Dialog
      v-model:visible="formVisible"
      modal
      :header="`${formData.id ? 'Editar' : 'Adicionar'} cliente`"
      :style="{ width: '25rem' }"
      @hide="handleCloseDialog"
    >
      <span class="mb-8 block text-surface-500 dark:text-surface-400">
        <span v-if="formData.id">
          Preencha os campos abaixo para editar o cliente
        </span>
        <span v-else>
          Preencha os campos abaixo para adicionar um novo cliente
        </span>
      </span>

      <div class="mb-4 flex items-center gap-4">
        <label for="cpf" class="w-24 font-semibold">CPF</label>
        <InputMask
          id="cpf"
          v-model="formData.cpf"
          mask="999.999.999-99"
          class="flex-auto"
        />
      </div>
      <div class="mb-4 flex items-center gap-4">
        <label for="nome" class="w-24 font-semibold">Nome</label>
        <InputText
          id="nome"
          v-model="formData.nome"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-4 flex items-center gap-4">
        <label for="endereco" class="w-24 font-semibold">Endereço</label>
        <InputText
          id="endereco"
          v-model="formData.endereco"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-4 flex items-center gap-4">
        <label for="profissao" class="w-24 font-semibold">Profissão</label>
        <InputText
          id="profissao"
          v-model="formData.profissao"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="rg" class="w-24 font-semibold">RG</label>
        <InputMask
          id="rg"
          v-model="formData.rg"
          mask="99.999.999-9"
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
