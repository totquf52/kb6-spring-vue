<!-- components/layouts/menu/MenuGroup.vue -->
<script setup>
import MenuItem from './MenuItem.vue';

const props = defineProps({
  menus: { type: Array, required: true },
});
</script>

<template>
  <ul class="navbar-nav">
    <!-- :menu는 자식한테 넘겨줄 props -->
    <MenuItem v-for="menu in menus" :key="menu.url" :menu="menu" />
  </ul>
</template>
