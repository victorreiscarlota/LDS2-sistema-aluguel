<script setup>
const items = ref([
  {
    separator: true,
  },
  {
    label: 'Geral',
    items: [
      {
        label: 'Home',
        icon: 'pi pi-home',
        command: () => {
          navigateTo('/home');
        },
      },
      {
        label: 'Franquias',
        icon: 'pi pi-briefcase',
        command: () => {
          navigateTo('/home/franquias');
        },
      },
      {
        label: 'Unidades',
        icon: 'pi pi-map-marker',
        command: () => {
          navigateTo('/home/unidades');
        },
      },
      {
        label: 'Salões',
        icon: 'pi pi-shop',
        command: () => {
          navigateTo('/home/salao');
        },
      },
    ],
  },
  {
    label: 'Perfil',
    items: [
      {
        label: 'Configurações',
        icon: 'pi pi-cog',
      },
    ],
  },
  {
    separator: true,
  },
]);
</script>

<template>
  <div class="card flex justify-center">
    <Menu :model="items" class="w-full md:w-60">
      <template #start>
        <span class="inline-flex items-center gap-1 px-2 py-2">
          <span class="text-xl font-semibold">
            Aracar
            <span class="text-primary">APP</span>
          </span>
        </span>
      </template>
      <template #submenulabel="{ item }">
        <span class="text-primary font-bold">{{ item.label }}</span>
      </template>
      <template #item="{ item, props }">
        <a v-ripple class="flex items-center" v-bind="props.action">
          <span :class="item.icon" />
          <span>{{ item.label }}</span>
          <Badge v-if="item.badge" class="ml-auto" :value="item.badge" />
          <span
            v-if="item.shortcut"
            class="border-surface bg-emphasis text-muted-color ml-auto rounded border p-1 text-xs"
          >
            {{ item.shortcut }}
          </span>
        </a>
      </template>
      <template #end>
        <button
          v-ripple
          class="relative flex w-full cursor-pointer items-start overflow-hidden rounded-none border-0 bg-transparent p-2 pl-4 transition-colors duration-200 hover:bg-surface-100 dark:hover:bg-surface-800"
        >
          <Avatar
            src="https://avatars.githubusercontent.com/u/39428840?v=4"
            class="mr-2"
            shape="circle"
          />
          <span class="inline-flex flex-col items-start">
            <span class="font-bold">Luís Brescia</span>
            <span class="text-sm">Admin</span>
          </span>
        </button>
      </template>
    </Menu>
  </div>
</template>
