<template>
  <h1><i class="fa-regular fa-pen-to-square"></i> 글 수정</h1>

  <form @submit.prevent="submit">
    <!-- 제목 입력 -->
    <div class="mb-3 mt-3">
      <label for="title" class="form-label"> 제목 </label>
      <input
        type="text"
        class="form-control"
        placeholder="제목"
        id="title"
        v-model="article.title"
      />
      <div class="invalid-feedback">제목은 필수 요소입니다.</div>
    </div>

    <!-- 기존 첨부파일 목록 -->
    <div class="mb-3 mt-3">
      <label class="form-label"> 기존 첨부파일 </label>
      <div v-for="file in attachments" :key="file.no" class="attach">
        <i class="fa-solid fa-paperclip"></i> {{ file.filename }}
        <i
          class="fa-solid fa-trash-can text-danger ms-2"
          @click="removeFile(file.no, file.filename)"
        ></i>
      </div>
    </div>

    <!-- 새로운 첨부파일 선택 -->
    <div class="mb-3 mt-3">
      <label for="files" class="form-label"> 첨부파일 </label>
      <input
        type="file"
        class="form-control"
        placeholder="첨부파일"
        id="files"
        ref="files"
        multiple
      />
    </div>

    <!-- 내용 입력 -->
    <div class="mb-3 mt-3">
      <label for="content" class="form-label"> 내용 </label>
      <textarea
        class="form-control"
        placeholder="내용"
        id="content"
        v-model="article.content"
        rows="10"
      ></textarea>
    </div>

    <!-- 버튼들 -->
    <div class="my-5 text-center">
      <button type="submit" class="btn btn-primary me-3">
        <i class="fa-solid fa-check"></i> 확인
      </button>
      <button type="button" class="btn btn-primary me-3" @click="reset">
        <i class="fa-solid fa-undo"></i> 취소
      </button>
      <button class="btn btn-primary" @click="back">
        <i class="fa-solid fa-arrow-left"></i> 돌아가기
      </button>
    </div>
  </form>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import boardApi from '@/api/boardApi';

// 라우터 정보
const cr = useRoute();
const router = useRouter();
const no = cr.params.no; // 게시글 번호

// 반응형 데이터 정의
const article = reactive({});
const attachments = ref([]);
const orgArticle = ref({});
const files = ref(null);

// 돌아가기
const back = () => {
  router.push({ name: 'board/detail', params: { no }, query: cr.query });
};

// 첨부파일 삭제
const removeFile = async (no, filename) => {
  if (!confirm(filename + '을 삭제할까요?')) return;

  await boardApi.deleteAttachment(no); // 서버에서 삭제
  const ix = attachments.value.findIndex((f) => f.no === no);
  attachments.value.splice(ix, 1); // 로컬 목록에서도 삭제
};

// 수정 제출
const submit = async () => {
  if (!confirm('수정할까요?')) return;

  if (files.value.files.length > 0) {
    article.files = files.value.files;
  }

  await boardApi.update(article); // 수정 API 호출
  router.push({ name: 'board/detail', params: { no }, query: cr.query });
};

// 입력값 초기화
const reset = () => {
  article.no = orgArticle.value.no;
  article.title = orgArticle.value.title;
  article.writer = orgArticle.value.writer;
  article.content = orgArticle.value.content;
  console.log(article);
};

// 기존 게시글 불러오기
const load = async () => {
  const data = await boardApi.get(no);
  orgArticle.value = { ...data };
  attachments.value = data.attaches;
  reset();
};

load(); // 컴포넌트 로딩 시 실행
</script>

<style>
.fa-trash-can {
  cursor: pointer;
}
</style>
