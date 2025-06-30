<script setup>
const props = defineProps({ username: String });

// 아바타 이미지 경로를 username을 이용해 생성
const avatar = `/api/member/${props.username}/avatar`;
</script>

<template>
  <li class="nav-item">
    <router-link class="nav-link" to="/auth/profile">
      <!-- 사용자 아바타 이미지 출력 -->
      <img :src="avatar" class="avatar avatar-sm" />
      {{ username }}
    </router-link>
  </li>
</template>

<style></style>
