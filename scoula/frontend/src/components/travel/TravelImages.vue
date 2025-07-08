<script setup>
import { ref } from 'vue';
const props = defineProps({
  images: { Type: Array, required: true },
});
console.log(props.images);
let activeImage = ref(props.images[0].url);
const onClick = (ix) => {
  console.log(ix, props.images[ix].url);
  activeImage.value = props.images[ix].url;
};
</script>

<template>
  <div class="w-100 my-4">
    <!-- 선택된 대표 이미지 표시 -->
    <img :src="activeImage" style="width: 100%; height: 400px" />

    <div class="d-flex">
      <!-- 썸네일 이미지 목록 출력 -->
      <div class="flex-fill" v-for="(image, ix) in images" :key="image.no">
        <img
          class="thumbnail"
          :src="image.url"
          style="width: 100%; height: 100px"
          @click="onClick(ix)"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 이미지 비율 유지 */
img {
  object-fit: cover;
}

/* 썸네일에 마우스 포인터 표시 */
.thumbnail {
  cursor: pointer;
}

/* 선택된 썸네일에 빨간 테두리 표시 */
.active {
  border: 1px solid red;
}
</style>
