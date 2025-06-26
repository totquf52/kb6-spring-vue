<!-- App.vue -->
<script setup>
import { RouterView } from 'vue-router';
import DefaultLayout from './components/layouts/DefaultLayout.vue';
</script>

<template>
  <DefaultLayout>
    <!-- DefaultLayout의 slot 부분에 RouterView가 전달된다 -->
    <RouterView />
  </DefaultLayout>
</template>

<style scoped></style>
