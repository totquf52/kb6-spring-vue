<!-- components/layouts/menu/LogoutMenuItem.vue -->
<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const logout = (e) => {
  // 로그아웃 처리 로직 (토큰 삭제 등 추가 가능)
  router.push('/'); // 메인 페이지로 이동
};
</script>

<template>
  <!-- prevent 수식어로 a 태그 기본 동작 방지 -->
  <a href="#" class="nav-link" @click.prevent="logout">
    <i class="fa-solid fa-right-from-bracket"></i>
    로그아웃
  </a>
</template>
