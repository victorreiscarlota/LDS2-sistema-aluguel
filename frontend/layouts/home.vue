<script setup>
import { useConfirm } from 'primevue/useconfirm';
import { useToast } from 'primevue/usetoast';

const confirm = useConfirm();
const toast = useToast();
const colorMode = useColorMode();

const changeColorTheme = () => {
  colorMode.preference = colorMode.preference === 'light' ? 'dark' : 'light';
};

const buttonIcon = computed(() => {
  return colorMode.preference === 'light' ? 'pi pi-moon' : 'pi pi-sun';
});

const confirmLogout = (event) => {
  confirm.require({
    target: event.target,
    message: 'Confirmar saída',
    icon: 'pi pi-exclamation-triangle',
    position: 'top',
    rejectProps: {
      label: 'Cancelar',
      severity: 'secondary',
      text: true,
    },
    acceptProps: {
      label: 'Sair',
      text: true,
      severity: 'danger',
    },
    accept: () => {
      toast.add({
        severity: 'info',
        summary: 'Confirmed',
        detail: 'Saída confirmada',
        life: 3000,
      });
      navigateTo('/');
    },
    reject: () => {},
  });
};
</script>

<template>
  <Toast />
  <ConfirmPopup />
  <div class="h-screen bg-surface-100 dark:bg-surface-950">
    <div
      class="sticky top-0 z-50 border-b border-surface-200 bg-surface-0 bg-opacity-90 backdrop-blur dark:border-surface-800 dark:bg-surface-950 dark:bg-opacity-90"
    >
      <div class="container mx-auto flex items-center py-2">
        <h1
          class="flex-1 text-3xl font-black tracking-tighter text-surface-900 dark:text-surface-100"
        >
          <i class="pi pi-car mr-2 text-2xl" />
          Aracar
        </h1>

        <ul class="flex gap-4">
          <NuxtLink to="/home/automoveis">
            <li
              class="cursor-pointer rounded px-4 py-2 font-medium text-surface-700 hover:bg-surface-50 active:bg-surface-100 dark:text-surface-300 dark:hover:bg-surface-800 dark:active:bg-surface-900"
            >
              <i class="pi pi-car mr-2" />
              Automovéis
            </li>
          </NuxtLink>
          <NuxtLink to="/home/clientes">
            <li
              class="cursor-pointer rounded px-4 py-2 font-medium text-surface-700 hover:bg-surface-50 active:bg-surface-100 dark:text-surface-300 dark:hover:bg-surface-800 dark:active:bg-surface-900"
            >
              <i class="pi pi-users mr-2" />
              Clientes
            </li>
          </NuxtLink>

          <NuxtLink to="/home/contratos">
            <li
              class="cursor-pointer rounded px-4 py-2 font-medium text-surface-700 hover:bg-surface-50 active:bg-surface-100 dark:text-surface-300 dark:hover:bg-surface-800 dark:active:bg-surface-900"
            >
              <i class="pi pi-file mr-2" />
              Contratos
            </li>
          </NuxtLink>

          <NuxtLink to="/home/creditos">
            <li
              class="cursor-pointer rounded px-4 py-2 font-medium text-surface-700 hover:bg-surface-50 active:bg-surface-100 dark:text-surface-300 dark:hover:bg-surface-800 dark:active:bg-surface-900"
            >
              <i class="pi pi-money-bill mr-2" />
              Créditos
            </li>
          </NuxtLink>

          <NuxtLink to="/home/pedidos">
            <li
              class="cursor-pointer rounded px-4 py-2 font-medium text-surface-700 hover:bg-surface-50 active:bg-surface-100 dark:text-surface-300 dark:hover:bg-surface-800 dark:active:bg-surface-900"
            >
              <i class="pi pi-shopping-cart mr-2" />
              Pedidos
            </li>
          </NuxtLink>
        </ul>

        <div class="flex flex-1 justify-end">
          <Button
            :icon="buttonIcon"
            @click="changeColorTheme"
            severity="secondary"
            text
            rounded
            arial-label="Trocar tema"
          />
          <Divider layout="vertical" />
          <Button
            icon="pi pi-sign-out"
            severity="danger"
            text
            rounded
            arial-label="Trocar tema"
            @click="confirmLogout($event)"
          />
        </div>
      </div>
    </div>

    <div
      class="container mx-auto mt-8 max-h-screen min-h-96 flex-1 overflow-x-hidden"
    >
      <slot />
    </div>
  </div>
</template>

<style scoped>
.router-link-active li {
  border-color: var(--p-surface-900);
  background-color: var(--p-surface-50);
  color: var(--p-surface-900);
  outline-width: 1px;
  outline-style: auto;
  outline-color: var(--p-surface-300);
}

.dark .router-link-active li {
  border-color: var(--p-surface-100);
  background-color: var(--p-surface-900);
  color: var(--p-surface-100);
}
</style>
