<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-lock"></i>
      비밀번호 변경
    </h1>

    <!-- 비밀번호 변경 form -->
    <form @submit.prevent="onSubmit">
      <!-- 이전 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i>
          이전 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="이전 비밀번호"
          v-model="changePassword.oldPassword"
          @input="inputPassword"
        />
      </div>

      <!-- 새 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i>
          새 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="새 비밀번호"
          v-model="changePassword.newPassword"
          @input="resetError"
        />
      </div>

      <!-- 새 비밀번호 확인 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i>
          새 비밀번호 확인:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="새 비밀번호 확인"
          v-model="changePassword.newPassword2"
          @input="resetError"
        />
      </div>

      <!-- 에러 메시지 출력 -->
      <div v-if="error" class="text-danger">
        {{ error }}
      </div>

      <!-- 확인 버튼 (조건부 비활성화) -->
      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-check"></i>
        확인
      </button>
    </form>
  </div>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
import authApi from '@/api/authApi';

// 라우터, 인증 스토어 불러오기
const router = useRouter();
const auth = useAuthStore();

// 사용자 입력값을 reactive 객체로 관리
const changePassword = reactive({
  username: auth.username, // 현재 사용자명
  oldPassword: '', // 기존 비밀번호
  newPassword: '', // 새 비밀번호
  newPassword2: '', // 새 비밀번호 확인용
});

// 입력값 중 하나라도 비어 있으면 제출 비활성화
const disableSubmit = computed(
  () =>
    !changePassword.oldPassword ||
    !changePassword.newPassword ||
    !changePassword.newPassword2
);

// 에러 메시지 상태
const error = ref('');

// 입력 시 에러 초기화
const inputPassword = () => (error.value = '');
const resetError = () => (error.value = '');

// 제출 시 실행되는 함수
const onSubmit = async () => {
  // 새 비밀번호 불일치 시 에러 처리
  if (changePassword.newPassword != changePassword.newPassword2) {
    error.value = '새 비밀번호가 일치하지 않습니다.';
    return;
  }

  try {
    // 비밀번호 변경 API 요청
    await authApi.changePassword(changePassword);

    // 성공 시 알림 후 프로필 페이지 이동
    alert('비밀번호를 수정했습니다.');
    router.push({ name: 'profile' });
  } catch (e) {
    // 실패 시 서버 응답을 에러로 표시
    error.value = e.response.data;
  }
};
</script>
