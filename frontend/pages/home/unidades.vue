<script setup>
definePageMeta({
  layout: 'home',
});

const {
  data: unidadesData,
  pending: unidadesPending,
  refresh: unidadesRefresh,
} = await useApiFetch('/unidades', {
  transform: (data) => data.data,
});

const formData = ref({
  franquia_id: null,
  nome_fantasia: '',
  cidade: '',
  logradouro: '',
  cep: '',
  telefone: '',
  email: '',
  foto: '',
  ativo: true,
});

const openModal = ref(false);

const handleSubmit = async (event) => {
  event.preventDefault();
  postUnidades();
};

const isValidCEP = (cep) => {
  const cepPattern = /^[0-9]{5}-?[0-9]{3}$/;
  return cepPattern.test(cep);
};

const isValidPhone = (phone) => {
  const phonePattern = /^\(?[1-9]{2}\)? ?9?[0-9]{4}-?[0-9]{4}$/;
  return phonePattern.test(phone);
};

const isValidEmail = (email) => {
  const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailPattern.test(email);
};

const postUnidades = async () => {
  try {
    const { data } = await useApiFetch('/unidades', {
      method: 'POST',
      body: formData.value,
    });
    console.log('Unidade cadastrada com sucesso', data);
    unidadesRefresh();
    openModal.value = false;
  } catch (error) {
    console.log('Erro ao cadastrar unidade', error);
  }
};

const unidadesTable = [
  { field: 'nome_fantasia', header: 'Nome fantasia' },
  { field: 'cidade', header: 'Cidade' },
  { field: 'logradouro', header: 'Logradouro' },
  { field: 'cep', header: 'CEP' },
  { field: 'telefone', header: 'Telefone' },
  { field: 'email', header: 'E-mail' },
  { field: 'ativo', header: 'Ativo' },
];
</script>

<template>
  <div>
    <div class="mb-4 flex gap-4">
      <Button @click="openModal = true">Adicionar Unidade</Button>
    </div>

    <h3 v-if="unidadesPending">Carregando...</h3>

    <div v-else class="card">
      <DataTable :value="unidadesData" showGridlines>
        <template #header>
          <div class="flex flex-wrap items-center justify-between gap-2">
            <span class="text-xl font-bold">Unidades</span>
            <Button
              icon="pi pi-refresh"
              rounded-sm
              raised
              @click="unidadesRefresh"
            />
          </div>
        </template>
        <Column
          v-for="col of unidadesTable"
          :key="col.field"
          :field="col.field"
          :header="col.header"
        />
      </DataTable>
    </div>

    <Dialog
      v-model:visible="openModal"
      modal
      header="Adicionar Unidade"
      :style="{ width: '35rem' }"
    >
      <form @submit="handleSubmit">
        <div class="flex flex-col gap-8">
          <div class="flex flex-col gap-2">
            <label for="franquia_id">Franquia</label>
            <InputText
              id="franquia_id"
              v-model="formData.franquia_id"
              aria-describedby="franquia_id-help"
            />
            <small id="franquia_id-help">Informe o ID da franquia.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="nome_fantasia">Nome Fantasia</label>
            <InputText
              id="nome_fantasia"
              v-model="formData.nome_fantasia"
              aria-describedby="nome_fantasia-help"
            />
            <small id="nome_fantasia-help">
              Informe o nome fantasia da unidade.
            </small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="cidade">Cidade</label>
            <InputText
              id="cidade"
              v-model="formData.cidade"
              aria-describedby="cidade-help"
            />
            <small id="cidade-help">Informe a cidade da unidade.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="logradouro">Logradouro</label>
            <InputText
              id="logradouro"
              v-model="formData.logradouro"
              aria-describedby="logradouro-help"
            />
            <small id="logradouro-help">Informe o logradouro da unidade.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="cep">CEP</label>
            <InputText
              id="cep"
              v-model="formData.cep"
              aria-describedby="cep-help"
            />
            <small id="cep-help">Informe o CEP da unidade.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="telefone">Telefone</label>
            <InputText
              id="telefone"
              v-model="formData.telefone"
              aria-describedby="telefone-help"
            />
            <small id="telefone-help">Informe o telefone da unidade.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="email">E-mail</label>
            <InputText
              id="email"
              v-model="formData.email"
              aria-describedby="email-help"
            />
            <small id="email-help">Informe o e-mail da unidade.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="foto">Foto</label>
            <InputText
              id="foto"
              v-model="formData.foto"
              aria-describedby="foto-help"
            />
            <small id="foto-help">Informe a URL da foto da unidade.</small>
          </div>

          <div class="flex flex-col gap-2">
            <label for="ativo">Ativo</label>
            <Checkbox
              id="ativo"
              v-model="formData.ativo"
              true-value="true"
              false-value="false"
            />
            <small id="ativo-help">Marque se a unidade está ativa.</small>
          </div>

          <div class="flex justify-end gap-2">
            <Button
              type="button"
              label="Cancelar"
              severity="secondary"
              @click="openModal = false"
            />
            <Button type="submit" label="Adicionar" />
          </div>
        </div>
      </form>
    </Dialog>
  </div>
</template>
