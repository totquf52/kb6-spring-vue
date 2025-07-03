<template>
  <h1><i class="fa-regular fa-pen-to-square"></i> 글 작성</h1>

  <form @submit.prevent="submit">
    <!-- 제목 입력 -->
    <div class="mb-3 mt-3">
      <label for="title" class="form-label">제목</label>
      <input
        type="text"
        class="form-control"
        id="title"
        v-model="article.title"
        placeholder="제목"
      />
    </div>

    <!-- 첨부파일 입력 -->
    <div class="mb-3 mt-3">
      <label for="files" class="form-label">첨부파일</label>
      <input
        type="file"
        class="form-control"
        id="files"
        ref="files"
        multiple
        placeholder="첨부파일"
      />
    </div>

    <!-- 내용 입력 -->
    <div class="mb-3 mt-3">
      <label for="content" class="form-label">내용</label>
      <textarea
        class="form-control"
        id="content"
        v-model="article.content"
        placeholder="내용"
        rows="10"
      ></textarea>
    </div>

    <!-- 버튼 영역 -->
    <div class="my-5 text-center">
      <button
        type="submit"
        class="btn btn-primary me-3"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-check"></i> 확인
      </button>
      <router-link
        class="btn btn-primary"
        :to="{ name: 'board/list', query: cr.query }"
      >
        <i class="fa-solid fa-list"></i> 목록
      </router-link>
    </div>
  </form>
</template>

<script setup>
import boardApi from '@/api/boardApi';
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRoute, useRouter } from 'vue-router';

const auth = useAuthStore();
const router = useRouter();

// 파일 참조 변수
const files = ref(null);

// 게시글 데이터 객체
const article = reactive({
  writer: auth.username,
  title: '',
  content: '',
  files: null,
});

// 제목이 비어있으면 버튼 비활성화
const disableSubmit = computed(() => !article.title);

// 폼 제출 처리
const submit = async () => {
  if (!confirm('등록할까요?')) return;

  // 파일이 선택된 경우 파일 배열 저장
  if (files.value.files.length > 0) {
    article.files = files.value.files;
  }

  // API 호출
  await boardApi.create(article);
  router.push('/board/list');
};
</script>
