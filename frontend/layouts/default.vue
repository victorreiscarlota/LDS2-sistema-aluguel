<template>
  <div
    class="bg-img-aura flex h-screen w-screen items-center justify-center bg-cover bg-no-repeat p-20"
  >
    <slot />
  </div>
  <!-- <div
    v-for="(blob, index) in blobs"
    :key="index"
    class="blob animate-pulse dark:bg-primary-400"
    :style="{ top: blob.top + 'px', left: blob.left + 'px' }"
  /> -->
</template>

<script setup>
const generateBlobs = (count) => {
  const blobs = [];
  const maxWidth = window.innerWidth - 100;
  const maxHeight = window.innerHeight - 100;
  for (let i = 0; i < count; i++) {
    blobs.push({
      top: Math.random() * maxHeight,
      left: Math.random() * maxWidth,
    });
  }
  return blobs;
};

const blobs = generateBlobs(8);
</script>

<style scoped>
.blob {
  position: absolute;
  width: 100px;
  height: 200px;
  filter: blur(10px);
}
</style>
