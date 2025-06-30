<script setup>
import { useRouter } from 'vue-router'; // 라우터 객체 사용
import { useAuthStore } from '@/stores/auth'; // 인증 상태를 관리하는 Pinia 스토어

// 인증 스토어 인스턴스 가져오기
const store = useAuthStore();

// 라우터 객체 생성
const router = useRouter();

// 로그아웃 함수 정의
const logout = (e) => {
  // 인증 상태 초기화
  store.logout();
  router.push('/');
};
</script>

<template>
  <a href="#" class="nav-link" @click.prevent="logout">
    <i class="fa-solid fa-right-from-bracket"></i>
    로그아웃
  </a>
</template>
