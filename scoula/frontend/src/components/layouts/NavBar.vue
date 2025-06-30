<!-- components/layouts/NavBar.vue -->
<script setup>
import { reactive, computed } from 'vue';
import config from '@/config';
// 하위 메뉴 컴포넌트 import
import MenuGroup from './menu/MenuGroup.vue';
import AccountMenuGroup from './menu/AccountMenuGroup.vue';

// 네비게이션 상태 (토글 버튼 눌렀는지 여부)
let state = reactive({ isNavShow: false });

// 네비게이션 클래스 동적 변경
let navClass = computed(
  () =>
    state.isNavShow
      ? 'collapse navbar-collapse show' // 펼쳐진 상태
      : 'collapse navbar-collapse' // 접힌 상태
);

// 토글 함수: 버튼 클릭 시 상태 반전
const toggleNavShow = () => (state.isNavShow = !state.isNavShow);
</script>

<template>
  <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <div class="container-fluid">
      <!-- 홈으로 이동하는 로고 -->
      <router-link class="navbar-brand" to="/">
        <i class="fa-solid fa-house"></i>
        Scoula
      </router-link>

      <!-- 햄버거 버튼: 클릭 시 메뉴 열기/닫기 -->
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#collapsibleNavbar"
        @click="toggleNavShow"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div :class="navClass" id="collapsibleNavbar">
        <!-- 일반 메뉴 그룹 -->
        <MenuGroup :menus="config.menus" />

        <!-- 계정 관련 메뉴 그룹 -->
        <AccountMenuGroup />
      </div>
    </div>
  </nav>
</template>

<style scoped>
/* 필요 시 스타일 작성 */
</style>
