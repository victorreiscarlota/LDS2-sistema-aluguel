<script setup>
import { useToast } from 'primevue/usetoast';
import { useConfirm } from 'primevue/useconfirm';

definePageMeta({
  layout: 'home',
});

const confirm = useConfirm();
const toast = useToast();

// Buscando dados dos pedidos
const {
  data: pedidosData,
  pending: pedidosPending,
  refresh: pedidosRefresh,
} = await useApiFetch('/pedidos');

// Buscando dados dos clientes para as opções do seletor
const { data: clientesData } = await useApiFetch('/clientes');

const formVisible = ref(false);
const formData = ref({
  dataPedido: null,
  status: '',
  clienteId: null,
});

const pedidosTable = [
  { field: 'dataPedido', header: 'Data do Pedido' },
  { field: 'status', header: 'Status' },
  { field: 'clienteId', header: 'Cliente' },
];

const editPedido = (data) => {
  formData.value = { ...data };
  formVisible.value = true;
};

const confirmDeletePedido = (id) => {
  confirm.require({
    header: 'Confirmação',
    message: 'Deseja realmente deletar este pedido?',
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
      deletePedido(id);
    },
  });
};

const deletePedido = async (id) => {
  try {
    await useApiFetch(`/pedidos?id=${id}`, {
      method: 'DELETE',
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Pedido deletado com sucesso',
      life: 3000,
    });
    pedidosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao deletar pedido',
      life: 3000,
    });
  }
};

const handleSubmitLoading = ref(false);

const postPedido = async () => {
  try {
    await useApiFetch('/pedidos', {
      method: 'POST',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Pedido cadastrado com sucesso',
      life: 3000,
    });
    pedidosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao cadastrar pedido',
      life: 3000,
    });
  } finally {
    handleCloseDialog();
    handleSubmitLoading.value = false;
  }
};

const putPedido = async () => {
  try {
    await useApiFetch(`/pedidos?id=${formData.value.id}`, {
      method: 'PUT',
      body: formData.value,
    });
    toast.add({
      severity: 'success',
      summary: 'Sucesso',
      detail: 'Pedido editado com sucesso',
      life: 3000,
    });
    pedidosRefresh();
  } catch (error) {
    toast.add({
      severity: 'error',
      summary: 'Erro',
      detail: 'Erro ao editar pedido',
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
    putPedido();
  } else {
    postPedido();
  }
};

const handleOpenDialog = () => {
  formVisible.value = true;
};

const handleCloseDialog = () => {
  formData.value = {
    dataPedido: null,
    status: '',
    clienteId: null,
  };
  formVisible.value = false;
};

// Função para formatar a data
const formatDate = (date) => {
  return new Date(date).toLocaleDateString('pt-BR');
};

// Função para obter o nome do cliente pelo ID
const getClienteNome = (clienteId) => {
  const cliente = clientesData.value.find((c) => c.id === clienteId);
  return cliente ? cliente.nome : 'N/A';
};
</script>

<template>
  <div class="container mx-auto">
    <Button class="rounded-none" @click="handleOpenDialog">
      <i class="pi pi-plus" />
      Adicionar pedido
    </Button>
  </div>

  <h3 v-if="pedidosPending">Carregando...</h3>

  <div v-else class="card">
    <DataTable :value="pedidosData" size="large">
      <template #header>
        <div class="flex flex-wrap items-center justify-between gap-2">
          <span class="text-xl font-bold">Pedidos</span>
          <Button
            icon="pi pi-refresh"
            severity="primary"
            rounded
            @click="pedidosRefresh"
          />
        </div>
      </template>
      <Column
        field="dataPedido"
        header="Data do Pedido"
        :body="(data) => formatDate(data.dataPedido)"
      />
      <Column field="status" header="Status" />
      <Column
        field="clienteId"
        header="Cliente"
        :body="(data) => getClienteNome(data.clienteId)"
      />
      <Column>
        <template #body="{ data }">
          <Button icon="pi pi-trash" @click="confirmDeletePedido(data.id)" />
          <Button icon="pi pi-pencil" class="ml-2" @click="editPedido(data)" />
        </template>
      </Column>
    </DataTable>

    <Dialog
      v-model:visible="formVisible"
      modal
      :header="`${formData.id ? 'Editar' : 'Adicionar'} pedido`"
      :style="{ width: '30rem' }"
      @hide="handleCloseDialog"
    >
      <span class="mb-8 block text-surface-500 dark:text-surface-400">
        <span v-if="formData.id">
          Preencha os campos abaixo para editar o pedido
        </span>
        <span v-else>
          Preencha os campos abaixo para adicionar um novo pedido
        </span>
      </span>

      <div class="mb-4 flex items-center gap-4">
        <label for="dataPedido" class="w-32 font-semibold">
          Data do Pedido
        </label>
        <Calendar
          id="dataPedido"
          v-model="formData.dataPedido"
          dateFormat="dd/mm/yy"
          class="flex-auto"
        />
      </div>
      <div class="mb-4 flex items-center gap-4">
        <label for="status" class="w-32 font-semibold">Status</label>
        <InputText
          id="status"
          v-model="formData.status"
          class="flex-auto"
          autocomplete="off"
        />
      </div>
      <div class="mb-8 flex items-center gap-4">
        <label for="clienteId" class="w-32 font-semibold">Cliente</label>
        <Dropdown
          id="clienteId"
          v-model="formData.clienteId"
          :options="clientesData"
          optionLabel="nome"
          optionValue="id"
          placeholder="Selecione um cliente"
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
