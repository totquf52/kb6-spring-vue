<!-- components/layouts/menu/MenuItem.vue -->
<script setup>
// props는 부모에서 받아온 내용
const props = defineProps({
  menu: { type: Object, required: true },
});
</script>

<template>
  <li class="nav-item">
    <router-link class="nav-link" :to="menu.url">
      <i :class="menu.icon"></i>
      {{ menu.title }}
    </router-link>
  </li>
</template>

<style></style>
