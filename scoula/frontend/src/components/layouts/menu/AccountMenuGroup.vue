<script setup>
import { computed } from 'vue';
import MenuItem from './MenuItem.vue';
import AccountMenuItem from './AccountMenuItem.vue';
import LogoutMenuItem from './LogoutMenuItem.vue';
import config from '@/config';

// 로그인/회원가입 메뉴 설정 가져오기
const { login, join } = config.accoutMenus;

// 임시 데이터 (실제 로그인 연동 전용)
const islogin = computed(() => false); // 로그인 여부
const username = computed(() => ''); // 사용자명
</script>

<template>
  <ul class="navbar-nav ms-auto">
    <!-- 로그인한 경우: 사용자 정보 + 로그아웃 버튼 -->
    <template v-if="islogin">
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
