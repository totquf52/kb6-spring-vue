<script setup>
import { computed } from 'vue';
import MenuItem from './MenuItem.vue';
import { useAuthStore } from '@/stores/auth.js'; // 인증 상태를 관리하는 Pinia 스토어

import LogoutMenuItem from './LogoutMenuItem.vue';
import config from '@/config';

// config 객체에서 로그인/회원가입 메뉴 정보 추출
const { login, join } = config.accoutMenus;

// 인증 스토어 사용
const auth = useAuthStore();

// 로그인 여부 및 사용자 이름을 반응형으로 사용
const isLogin = computed(() => auth.isLogin);
const username = computed(() => auth.username);
</script>

<template>
  <ul class="navbar-nav ms-auto">
    <!-- 로그인한 경우: 사용자 정보 + 로그아웃 버튼 -->
    <template v-if="isLogin">
      <AccountMenuItem :username="username" />
      <LogoutMenuItem />
    </template>

    <!-- 로그인하지 않은 경우: 로그인/회원가입 메뉴 표시 -->
    <template v-else>
      <MenuItem :menu="login" />
      <MenuItem :menu="join" />
    </template>
  </ul>
</template>
