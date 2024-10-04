<script setup>
import { useToast } from 'primevue/usetoast';
import { useConfirm } from 'primevue/useconfirm';

definePageMeta({
  layout: 'home',
});

const confirm = useConfirm();
const toast = useToast();

const {
  data: automoveisData,
  pending: automoveisPending,
  refresh: automoveisRefresh,
} = await useApiFetch('/automoveis');

const formVisible = ref(false);
const formData = ref({
  ano: '',
  marca: '',
  matricula: '',
  modelo: '',
  placa: '',
});

const automoveisTable = [
  { field: 'ano', header: 'Ano' },
  { field: 'marca', header: 'Marca' },
  { field: 'matricula', header: 'Matrícula' },
  { field: 'modelo', header: 'Modelo' },
  { field: 'placa', header: 'Placa' },
];

const editAutomovel = (data) => {
  formData.value = { ...data };
  formVisible.value = true;
};

const confirmDeleteAutomovel = (id) => {
  confirm.require({
    header: 'Confirmação',
    message: 'Deseja realmente deletar este automóvel?',
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
      deleteAutomovel(id);
    },
  });
};
const deleteAutomovel = async (id) => {
  try {
    await useApiFetch(`/automoveis?id=${id}`, {
      method: 'DELETE',
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Automóvel deletado com sucesso',
      life: 3000,
    });
    automoveisRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao deletar automóvel',
      life: 3000,
    });
  }
};

const handleSubmitLoading = ref(false);

const postAutomovel = async () => {
  try {
    await useApiFetch('/automoveis', {
      method: 'POST',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Automóvel cadastrado com sucesso',
      life: 3000,
    });
    automoveisRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao cadastrar automóvel',
      life: 3000,
    });
  } finally {
    handleCloseDialog();
    handleSubmitLoading.value = false;
  }
};

const putAutomovel = async () => {
  try {
    await useApiFetch(`/automoveis?id=${formData.value.id}`, {
      method: 'PUT',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Automóvel editado com sucesso',
      life: 3000,
    });
    automoveisRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao editar automóvel',
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
    putAutomovel();
  } else {
    postAutomovel();
  }
};

const handleOpenDialog = () => {
  formVisible.value = true;
};
const handleCloseDialog = () => {
  formData.value = {
    ano: '',
    marca: '',
    matricula: '',
    modelo: '',
    placa: '',
  };
  formVisible.value = false;
};
</script>

<template>
  <div class="container mx-auto">
    <Button class="rounded-none" @click="handleOpenDialog">
      <i class="pi pi-plus" />
      Adicionar automóvel
    </Button>
  </div>

  <h3 v-if="automoveisPending">Carregando...</h3>

  <div v-else class="card">
    <DataTable :value="automoveisData" size="large">
      <template #header>
        <div class="flex flex-wrap items-center justify-between gap-2">
          <span class="text-xl font-bold">Automoveis</span>
          <Button
            icon="pi pi-refresh"
            severity="primary"
            rounded
            @click="automoveisRefresh"
          />
        </div>
      </template>
      <Column
        v-for="col of automoveisTable"
        :key="col.field"
        :field="col.field"
        :header="col.header"
      />
      <Column>
        <template #body="{ data }">
          <Button icon="pi pi-trash" @click="confirmDeleteAutomovel(data.id)" />
          <Button
            icon="pi pi-pencil"
            class="ml-2"
            @click="editAutomovel(data)"
          />
        </template>
      </Column>
    </DataTable>

    <Dialog
      v-model:visible="formVisible"
      modal
      :header="`${formData.id ? 'Editar' : 'Adicionar'} automóvel`"
      :style="{ width: '25rem' }"
      @hide="handleCloseDialog"
    >
      <span class="mb-8 block text-surface-500 dark:text-surface-400">
        <span v-if="formData.id">
          Preencha os campos abaixo para adicionar um novo automóvel
        </span>
        <span v-else>Preencha os campos abaixo para editar o automóvel</span>
      </span>
      <div class="mb-4 flex items-center gap-4">
        <label for="ano" class="w-24 font-semibold">Ano</label>
        <InputMask
          id="ano"
          v-model="formData.ano"
          mask="9999"
          class="flex-auto"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="marca" class="w-24 font-semibold">Marca</label>
        <InputText
          id="marca"
          v-model="formData.marca"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="matricula" class="w-24 font-semibold">Matrícula</label>
        <InputText
          id="matricula"
          v-model="formData.matricula"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="modelo" class="w-24 font-semibold">Modelo</label>
        <InputText
          id="modelo"
          v-model="formData.modelo"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="placa" class="w-24 font-semibold">Placa</label>
        <InputMask
          id="placa"
          v-model="formData.placa"
          mask="aaa-9a99"
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
