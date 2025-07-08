<script setup>
// 부모 컴포넌트에서 전달받은 travel 객체 props 정의
const props = defineProps({
  travel: { type: Object, required: true },
});

// travel 이미지 목록 확인
console.log(props.travel.images);

// 배경 이미지 설정 (첫 번째 이미지 사용)
const background = {
  backgroundImage: `url('${props.travel.images[0].url}')`,
};
</script>

<template>
  <!-- 여행지 이름과 지역 표시, 배경 이미지 적용 -->
  <div
    class="travel-header mb-4 d-flex justify-content-center"
    :style="background"
  >
    <h1 class="align-content-center">
      {{ travel.district }} {{ travel.title }}
    </h1>
  </div>
</template>

<style scoped>
.travel-header {
  height: 300px;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  color: white;
}
</style>
