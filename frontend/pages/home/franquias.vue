<script setup>
definePageMeta({
  layout: 'home',
});

const {
  data: franquiasData,
  pending: franquiasPending,
  refresh: franquiasRefresh,
} = await useApiFetch('/franquias', {
  transform: (data) => data.data,
});

const formData = ref({
  nome_fantasia: '',
  categoria: '',
  descricao: '',
  info_adicionao: '',
  logo: '',
});

const modalVisible = ref(false);

const handleSubmit = async (event) => {
  event.preventDefault();
  postFranquia();
};

const postFranquia = async () => {
  try {
    const { data } = await useApiFetch('/franquias', {
      method: 'POST',
      body: formData.value,
    });
    console.log('Franquia cadastrada com sucesso', data);
    franquiasRefresh();
  } catch (error) {
    console.log('Erro ao cadastrar franquia', error);
  }
};

const deleteFranquia = async (id) => {
  try {
    const res = await useApiFetch(`/franquias?id=${id}`, {
      method: 'DELETE',
    });
    console.log('Franquia deletada com sucesso', res);
    franquiasRefresh();
  } catch (error) {
    console.log('Erro ao deletar franquia', error);
  }
};

const franquiasTable = [
  { field: 'nome_fantasia', header: 'Nome fantasia' },
  { field: 'categoria', header: 'Categoria' },
];
</script>

<template>
  <div>
    <div class="mb-4 flex gap-4">
      <Button @click="modalVisible = true">Adicionar Franquia</Button>
    </div>

    <h3 v-if="franquiasPending">Carregando...</h3>

    <div v-else class="card">
      <DataTable :value="franquiasData" size="large">
        <template #header>
          <div class="flex flex-wrap items-center justify-between gap-2">
            <span class="text-xl font-bold">Franquias</span>
            <Button
              icon="pi pi-refresh"
              rounded-sm
              raised
              @click="franquiasRefresh"
            />
          </div>
        </template>
        <Column
          v-for="col of franquiasTable"
          :key="col.field"
          :field="col.field"
          :header="col.header"
        />
        <Column>
          <template #body="{ data }">
            <Button icon="pi pi-trash" @click="deleteFranquia(data.id)" />
          </template>
        </Column>
      </DataTable>
    </div>

    <Dialog
      v-model:visible="modalVisible"
      modal
      header="Adicionar franquia"
      :style="{ width: '35rem' }"
    >
      <div class="flex flex-col gap-8">
        <div class="flex flex-col gap-2">
          <label for="nome_fantasia">Nome fantasia</label>
          <InputText
            id="nome_fantasia"
            v-model="formData.nome_fantasia"
            aria-describedby="nome_fantasia-help"
          />
          <small id="nome_fantasia-help">
            Informe o nome fantasia da franquia.
          </small>
        </div>

        <div class="flex flex-col gap-2">
          <label for="categoria">Categoria</label>
          <InputText
            id="categoria"
            v-model="formData.categoria"
            aria-describedby="categoria-help"
          />
          <small id="categoria-help">
            Especifique a categoria da franquia (por exemplo, alimentação,
            educação).
          </small>
        </div>

        <div class="flex flex-col gap-2">
          <label for="descricao">Descrição</label>
          <InputText
            id="descricao"
            v-model="formData.descricao"
            aria-describedby="descricao-help"
          />
          <small id="descricao-help">
            Forneça uma breve descrição da franquia.
          </small>
        </div>

        <div class="flex flex-col gap-2">
          <label for="info_adicional">Informações adicionais</label>
          <InputText
            id="info_adicional"
            v-model="formData.info_adicional"
            aria-describedby="info_adicional-help"
          />
          <small id="info_adicional-help">
            Adicione quaisquer informações extras relevantes.
          </small>
        </div>

        <div class="flex justify-end gap-2">
          <Button
            type="button"
            label="Cancelar"
            severity="secondary"
            @click="modalVisible = false"
          />
          <Button
            type="button"
            label="Adicionar"
            @click="modalVisible = false"
          />
        </div>
      </div>
    </Dialog>
  </div>
</template>
