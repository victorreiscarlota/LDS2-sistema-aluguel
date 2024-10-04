<script setup>
definePageMeta({
  layout: 'home',
});

const {
  data: salaoData,
  pending: salaoPending,
  refresh: salaoRefresh,
} = await useApiFetch('/salao', {
  transform: (data) => data.data,
});

const formData = ref({
  unidade_id: null,
  nome_fantasia: '',
  ativo: true,
});

const openModal = ref(false);

const postUnidades = async () => {
  try {
    const { data } = await useApiFetch('/salao', {
      method: 'POST',
      body: formData.value,
    });
    console.log('Salão cadastrado com sucesso', data);
    salaoRefresh();
    openModal.value = false;
  } catch (error) {
    console.log('Erro ao cadastrar salão', error);
  }
};
</script>

<template>
  <div>
    <h1>Salões</h1>
    <div class="flex gap-4">
      <Button @click="openModal.value = true">Adicionar Franquia</Button>
      <Button @click="salaoRefresh">Atualizar</Button>
    </div>
    <h3 v-if="salaoPending">Carregando...</h3>
    <pre v-else>
      {{ salaoData }}
    </pre>

    <Dialog
      v-model:visible="openModal.value"
      modal
      header="Adicionar Salão"
      :style="{ width: '35rem' }"
    >
      <div class="flex flex-col gap-8">
        <div class="flex flex-col gap-2">
          <label for="nome_fantasia">Nome Fantasia</label>
          <InputText
            id="nome_fantasia"
            v-model="formData.nome_fantasia"
            aria-describedby="nome_fantasia-help"
          />
          <small id="nome_fantasia-help">
            Informe o nome fantasia do salão.
          </small>
        </div>

        <div class="flex flex-col gap-2">
          <label for="unidade_id">Unidade ID</label>
          <InputText
            id="unidade_id"
            v-model="formData.unidade_id"
            aria-describedby="unidade_id-help"
          />
          <small id="unidade_id-help">Informe o ID da unidade.</small>
        </div>

        <div class="flex flex-col gap-2">
          <label for="ativo">Ativo</label>
          <InputSwitch id="ativo" v-model="formData.ativo" />
          <small id="ativo-help">Marque se o salão estiver ativo.</small>
        </div>

        <div class="flex justify-end gap-2">
          <Button
            type="button"
            label="Cancelar"
            severity="secondary"
            @click="openModal.value = false"
          />
          <Button type="button" label="Adicionar" @click="postUnidades" />
        </div>
      </div>
    </Dialog>
  </div>
</template>
