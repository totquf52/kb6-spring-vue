<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import api from '@/api/travelApi';

import TravelHeader from '@/components/travel/TravelHeader.vue';
import TravelImages from '@/components/travel/TravelImages.vue';
import TravelMap from '@/components/travel/TravelMap.vue';

const cr = useRoute(); // 현재 라우트 정보 (params, query 등 접근 가능)
const router = useRouter(); // 라우터 기능 (이동 등)

const no = cr.params.no; // 여행지 번호 추출
const travel = ref({}); // 여행지 데이터 저장 변수

// 목록으로 돌아가기 함수
const back = () => {
  router.push({ name: 'travel/list', query: cr.query });
};

// 여행지 상세 정보 가져오기
const load = async () => {
  travel.value = await api.get(no); // API 호출로 여행지 데이터 조회

  // 줄바꿈 문자(\n 등)를 <p> 태그로 변환하여 description에 저장
  let descriptions = travel.value.description.replace(
    /\. /gm,
    (t) => t + '<p/><p>'
  );
  travel.value.description = `<p>${descriptions}</p>`;
};

load(); // 컴포넌트 로드 시 데이터 요청
</script>

<template>
  <!-- 여행지 제목/주소 등을 보여주는 헤더 컴포넌트 -->
  <travel-header :travel="travel" />

  <!-- 여행지 설명 HTML 형식으로 출력 -->
  <div class="content" v-html="travel.description"></div>

  <!-- 여행지 이미지 목록 출력 -->
  <travel-images :images="travel.images"></travel-images>

  <!-- 여행지 전화번호 출력 -->
  <div>
    <i class="fa-solid fa-square-phone-flip"></i>
    전화번호: {{ travel.phone }}
  </div>

  <!-- Kakao Map 지도 컴포넌트에 제목과 주소 전달 -->
  <travel-map :title="travel.title" :address="travel.address" />

  <!-- 목록으로 돌아가기 버튼 -->
  <button class="btn btn-primary me-2" @click="back">
    <i class="fa-solid fa-list"></i> 목록
  </button>
</template>
