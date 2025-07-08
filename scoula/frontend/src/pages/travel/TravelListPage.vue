<template>
  <div>
    <h1 class="mb-3">
      <i class="fa-solid fa-person-walking-luggage"></i> 여행지 목록
    </h1>

    <!-- 전체 게시물 수 출력 -->
    <div>총 {{ page.totalCount }}건</div>

    <!-- 여행지 목록 카드 렌더링 -->
    <div class="row">
      <div
        class="col-sm-12 col-md-6 col-lg-4 col-xl-3 mb-3"
        v-for="travel in travels"
        :key="travel.no"
      >
        <travel-card :travel="travel" />
      </div>
    </div>

    <!-- 페이지네이션 -->
    <div class="mt-5 flex-grow-1 text-center">
      <vue-awesome-paginate
        :total-items="page.totalCount"
        :items-per-page="pageRequest.amount"
        :max-pages-shown="5"
        :show-ending-buttons="true"
        v-model="pageRequest.page"
        @click="handlePageChange"
      >
        <template #first-page-button>
          <i class="fa-solid fa-backward-fast"></i>
        </template>
        <template #prev-button>
          <i class="fa-solid fa-caret-left"></i>
        </template>
        <template #next-button>
          <i class="fa-solid fa-caret-right"></i>
        </template>
        <template #last-page-button>
          <i class="fa-solid fa-forward-fast"></i>
        </template>
      </vue-awesome-paginate>
    </div>
  </div>
</template>

<script setup>
// API 및 라이브러리 임포트
import api from '@/api/travelApi';
import { ref, reactive, computed, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import TravelCard from '@/components/travel/TravelCard.vue';

// 현재 라우트 객체와 라우터 가져오기
const cr = useRoute();
const router = useRouter();

// 여행지 목록 및 페이지 정보 저장용
const page = ref({});

// 여행지 목록을 page 객체에서 계산하여 추출
const travels = computed(() => page.value.list);

// 쿼리 스트링 기반 페이지네이션 상태 저장
const pageRequest = reactive({
  page: parseInt(cr.query.page) || 1,
  amount: parseInt(cr.query.amount) || 12,
});

// 페이지 변경 시 URL 쿼리 변경
const handlePageChange = async (pageNum) => {
  router.push({
    query: {
      page: pageNum,
      amount: pageRequest.amount,
    },
  });
};

// 쿼리 파라미터가 바뀌면 pageRequest 값 갱신 + 데이터 다시 불러오기
watch(cr, async () => {
  pageRequest.page = parseInt(cr.query.page);
  pageRequest.amount = parseInt(cr.query.amount);
  await load(pageRequest);
});

// 여행지 목록 데이터를 API에서 불러오는 함수
const load = async (query) => {
  try {
    page.value = await api.getList(query); // page: { list: [], totalCount: 123 }
    console.log(page.value); // 디버깅용 출력
  } catch (e) {
    console.error(e);
  }
};

// 첫 마운트 시 초기 데이터 불러오기
load(pageRequest);
</script>
