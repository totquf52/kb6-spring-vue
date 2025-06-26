<script setup>
import config from '@/config'; // 사이트 정보 (title, subtitle) import
</script>

<template>
  <div class="jumbotron p-5 bg-primary text-white">
    <h1>{{ config.title }}</h1>
    <p>{{ config.subtitle }}</p>
  </div>
</template>

<style scoped>
.jumbotron {
  background-image: url('@/assets/images/background.jpg'); /* 배경 이미지 */
  background-repeat: no-repeat; /* 반복 없음 */
  background-position: center; /* 중앙 정렬 */
  background-size: cover; /* 꽉 채우기 */
  color: white; /* 텍스트 색상 */
  padding: 2rem; /* 안쪽 여백 */
}
</style>
