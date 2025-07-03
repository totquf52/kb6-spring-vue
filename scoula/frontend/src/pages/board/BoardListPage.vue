<template>
  <div>
    <h1 class="mb-3"><i class="fa-solid fa-paste"></i> 게시글 목록</h1>

    <!-- 총 게시글 수 -->
    <div class="mt-5 text-end">총 {{ page.totalCount }}건</div>

    <!-- 게시글 목록 테이블 -->
    <table class="table table-striped">
      <thead>
        <tr>
          <th style="width: 60px">No</th>
          <th>제목</th>
          <th style="width: 100px">작성자</th>
          <th style="width: 120px">작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="article in articles" :key="article.no">
          <td>{{ article.no }}</td>
          <td>
            <router-link
              :to="{
                name: 'board/detail',
                params: { no: article.no },
                query: cr.query,
              }"
            >
              {{ article.title }}
            </router-link>
          </td>
          <td>{{ article.writer }}</td>
          <td>{{ moment(article.regDate).format('YYYY-MM-DD') }}</td>
        </tr>
      </tbody>
    </table>

    <!-- 페이지네이션 컴포넌트 -->
    <div class="my-5 d-flex">
      <div class="flex-grow-1 text-center">
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
      <!-- ✏️ 글 작성 버튼 -->
      <div>
        <router-link
          :to="{ name: 'board/create', query: cr.query }"
          class="btn btn-primary"
        >
          <i class="fa-solid fa-pen-to-square"></i> 글 작성
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import api from '@/api/boardApi';
import { ref, reactive, computed, watch } from 'vue';
import moment from 'moment';
import { useRoute, useRouter } from 'vue-router';

const cr = useRoute(); // 현재 라우트 정보
const router = useRouter(); // 라우터 이동 제어

const page = ref({}); // 전체 페이지 정보
const articles = computed(() => page.value.list); // 게시글 목록만 추출

// 쿼리 파라미터로부터 초기 페이지 요청 정보 설정
const pageRequest = reactive({
  page: parseInt(cr.query.page) || 1,
  amount: parseInt(cr.query.amount) || 10,
});

// 페이지 번호 변경 핸들러 (페이지네이션 클릭 시 호출됨)
const handlePageChange = async (pageNum) => {
  // URL query 변경 (라우트 이동)
  router.push({
    query: { page: pageNum, amount: pageRequest.amount },
  });
};

// 라우트가 변경되면 페이지 재로딩
watch(cr, async (newValue) => {
  console.log('WATCH', cr.query.page);
  pageRequest.page = parseInt(cr.query.page);
  pageRequest.amount = parseInt(cr.query.amount);
  await load(pageRequest);
});

// 실제 API 요청 함수
const load = async (query) => {
  try {
    page.value = await api.getList(query); // API 호출하여 page 구조로 저장
    console.log(page.value);
  } catch (e) {
    console.error('로드 실패', e);
  }
};

// 페이지 첫 로딩 시 실행
load(pageRequest);
</script>
