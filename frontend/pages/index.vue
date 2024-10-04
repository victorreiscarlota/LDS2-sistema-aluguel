<script setup>
definePageMeta({
  layout: 'default',
});

const formData = reactive({
  email: '',
  password: '',
  remember_me: false,
});

const handleSubmitLoading = ref(false);
const handleSubmit = () => {
  handleSubmitLoading.value = true;
  setTimeout(() => {
    handleSubmitLoading.value = false;
    navigateTo('/home/automoveis');
  }, 3000);
};
</script>

<template>
  <div class="flex flex-wrap">
    <div
      class="relative max-w-5xl bg-surface-0 bg-opacity-75 p-16 dark:bg-surface-950 dark:bg-opacity-75 xl:w-1/2"
    >
      <a
        href="https://github.com/victorreiscarlota/LDS2-sistema-aluguel"
        target="_blank"
      >
        <div
          class="absolute left-16 top-8 cursor-pointer text-sm font-medium hover:underline"
        >
          <i class="pi pi-github mr-1 text-xs" />
          Ir para repositorio
        </div>
      </a>

      <div>
        <i class="pi pi-car mb-12 mt-8 text-5xl" />
      </div>
      <div class="mb-4 font-medium">Painel Administrativo</div>
      <p class="mb-8 text-sm text-surface-700 dark:text-surface-0/70">
        Este painel é utilizado para criar e configurar todos os seus serviços
        no sistema de gerenciamento de vagas. Os recursos disponíveis neste
        painel incluem:
      </p>

      <ul class="m-0 list-none p-0">
        <li class="mb-6 flex justify-start">
          <div>
            <span
              class="flex h-[38px] w-[38px] items-center justify-center rounded-sm bg-surface-950 text-surface-0"
            >
              <i class="pi pi-clock"></i>
            </span>
          </div>
          <div class="ml-4">
            <span class="mb-12">Configuração de horário de funcionamento</span>
            <p class="mt-1 text-sm text-surface-700 dark:text-surface-0/70">
              Definição e ajuste dos horários de disponibilidade de vagas e
              gerenciamento de filas.
            </p>
          </div>
        </li>
        <li class="mb-6 flex justify-start">
          <div>
            <span
              class="flex h-[38px] w-[38px] items-center justify-center rounded-sm bg-surface-950 text-surface-0"
            >
              <i class="pi pi-face-smile"></i>
            </span>
          </div>
          <div class="ml-4">
            <span class="mb-12">Criação e edição de disponibilidade</span>
            <p class="mt-1 text-sm text-surface-700 dark:text-surface-0/70">
              Configuração de vagas disponíveis, tanto para reservas quanto para
              lista de espera.
            </p>
          </div>
        </li>
        <li class="flex justify-start">
          <div>
            <span
              class="flex h-[38px] w-[38px] items-center justify-center rounded-sm bg-surface-950 text-surface-0"
            >
              <i class="pi pi-lock"></i>
            </span>
          </div>
          <div class="ml-4">
            <span class="mb-12">Gerenciamento de permissões</span>
            <p class="mt-1 text-sm text-surface-700 dark:text-surface-0/70">
              Criação, edição e exclusão de usuários e definição de permissões
              para o Painel Operacional.
            </p>
          </div>
        </li>
      </ul>
    </div>
    <div
      class="w-full max-w-5xl bg-surface-0 p-16 dark:bg-surface-950 xl:w-1/2"
    >
      <div class="mb-8 text-xl font-medium">Login</div>

      <label
        for="email1"
        class="mb-2 block text-sm text-surface-900 dark:text-surface-0"
      >
        Email
      </label>
      <InputText
        id="email1"
        v-model="formData.email"
        type="text"
        class="mb-4 w-full"
      />

      <label
        for="password1"
        class="mb-2 block text-sm text-surface-900 dark:text-surface-0"
      >
        Senha
      </label>

      <Password
        :feedback="false"
        toggle-mask
        v-model="formData.password"
        class="mb-4 w-full"
        input-class="w-full"
      />

      <div class="mb-12 flex items-center justify-between">
        <div class="flex items-center">
          <Checkbox
            id="rememberme1"
            v-model="formData.remember_me"
            :binary="true"
            class="mr-2"
          />
          <label class="text-sm" for="rememberme1">Lembrar de mim</label>
        </div>
        <a
          class="ml-2 cursor-pointer text-right text-sm font-medium text-primary-500 no-underline hover:underline dark:text-primary-400"
        >
          Esqueceu sua senha?
        </a>
      </div>

      <Button
        label="Entrar"
        icon="pi pi-user"
        class="w-full"
        :loading="handleSubmitLoading"
        @click="handleSubmit"
      />

      <Divider>OU</Divider>

      <Button
        icon="pi pi-shop"
        label="Entrar com iHungry"
        class="w-full"
        severity="secondary"
      />

      <div
        class="mx-auto mt-8 flex justify-center text-sm text-surface-700 dark:text-surface-0/70"
      >
        Não tem uma conta?
        <NuxtLink
          to="/pricing"
          class="text-primary ml-2 cursor-pointer font-medium text-surface-950 hover:underline dark:text-surface-50"
        >
          Conheça nossos planos
        </NuxtLink>
      </div>
    </div>
  </div>
</template>
