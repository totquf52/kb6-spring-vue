<template>
  <div class="card" style="width: 100%">
    <!-- 이미지 클릭 시 상세 페이지 이동 -->
    <router-link
      :to="{
        name: 'travel/detail',
        params: { no: travel.no },
        query: cr.query,
      }"
    >
      <img
        class="card-img-top"
        :src="travel.images[0].url"
        :alt="travel.title"
      />
    </router-link>

    <div class="card-body">
      <!-- 제목 클릭 시 상세 페이지 이동 -->
      <h5 class="card-title">
        <router-link
          :to="{
            name: 'travel/detail',
            params: { no: travel.no },
            query: cr.query,
          }"
        >
          {{ travel.title }}
        </router-link>
      </h5>

      <!-- 설명 일부만 표시 (최대 250자) -->
      <p class="card-text">{{ travel.description.substring(0, 250) }}</p>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const cr = useRoute(); // 현재 라우터 정보 가져오기

// travel 객체를 부모로부터 props로 전달받음
const props = defineProps({
  travel: { type: Object, required: true },
});
</script>

<style>
/* 이미지 크기 고정 + 비율 유지 */
.card-img-top {
  height: 200px;
  object-fit: cover;
}

/* 설명 2줄로 자르고 말줄임 표시 */
.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

/* 링크 밑줄 제거 */
a {
  text-decoration: none;
}
</style>
