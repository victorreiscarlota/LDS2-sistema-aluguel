<script setup>
const colorMode = useColorMode();
const route = useRoute();

const changeColorTheme = () => {
  colorMode.preference = colorMode.preference === 'light' ? 'dark' : 'light';
};

const buttonIcon = computed(() => {
  return colorMode.preference === 'light' ? 'pi pi-moon' : 'pi pi-sun';
});

const checkExact = (path) => {
  console.log(route.path, path);
  return route.path === path ? 'primary' : 'secondary';
};
</script>

<template>
  <nav
    class="sticky top-0 z-50 border-b border-surface-200 bg-surface-0 bg-opacity-90 backdrop-blur dark:border-surface-800 dark:bg-surface-950 dark:bg-opacity-90"
  >
    <div class="container mx-auto flex justify-between py-2">
      <div class="flex flex-1">
        <span class="my-auto text-2xl font-black tracking-tighter">Aracar</span>
      </div>

      <div class="my-auto flex items-center">
        <Button
          @click="navigateTo('/')"
          :severity="checkExact('/')"
          size="small"
          label="Início"
          text
          rounded-sm
          arial-label="Início"
        />
        <Button
          severity="secondary"
          size="small"
          label="Soluções"
          text
          rounded-sm
          arial-label="Soluções"
          disabled
        />
        <Button
          @click="navigateTo('/pricing')"
          :severity="checkExact('/pricing')"
          size="small"
          label="Preços"
          text
          rounded-sm
          arial-label="Preços"
        />
        <Button
          severity="secondary"
          size="small"
          label="Contato"
          text
          rounded-sm
          arial-label="Contato"
          disabled
        />
      </div>

      <div class="flex flex-1 items-center justify-end">
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
          icon-pos="right"
          arial-label="Login"
          severity="secondary"
          outlined
          size="small"
          @click="navigateTo('/painel')"
        >
          <span class="font-bold text-surface-900 dark:text-surface-50">
            Entrar com sua conta
          </span>
        </Button>
        <Button
          icon="pi pi-arrow-right"
          label="Começar agora"
          severity="contrast"
          size="small"
          icon-pos="right"
          arial-label="Cadastro"
          class="ms-2"
        />
      </div>
    </div>
  </nav>
</template>

<style>
.p-button-icon-only {
  color: var(--p-surface-900, red) !important;
  font-weight: 700;
}

.dark .p-button-icon-only {
  color: var(--p-surface-50) !important;
}
</style>
