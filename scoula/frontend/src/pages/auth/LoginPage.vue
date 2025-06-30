<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5">
      <i class="fa-solid fa-right-to-bracket"></i>
      로그인
    </h1>

    <!-- 로그인 폼 제출 시 login() 호출 -->
    <form @submit.prevent="login">
      <!-- 사용자 ID 입력 -->
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i>
          사용자 ID:
        </label>
        <input
          type="text"
          class="form-control"
          placeholder="사용자 ID"
          v-model="member.username"
        />
      </div>

      <!-- 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i>
          비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          v-model="member.password"
        />
      </div>

      <!-- 에러 메시지 출력 -->
      <div v-if="error" class="text-danger">{{ error }}</div>

      <!-- 로그인 버튼 (입력값 없을 경우 비활성화) -->
      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-right-to-bracket"></i>
        로그인
      </button>
    </form>
  </div>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth'; // 인증 스토어(Pinia) 호출
import { useRouter } from 'vue-router'; // 라우터 사용

const router = useRouter(); // 페이지 이동용 라우터
const auth = useAuthStore(); // 인증 관련 상태 및 액션 사용

// 로그인 폼 데이터
const member = reactive({
  username: '',
  password: '',
});

// 에러 메시지 저장용
const error = ref('');

// 입력값이 모두 존재하지 않으면 버튼 비활성화
const disableSubmit = computed(() => !(member.username && member.password));

// 로그인 처리 함수
const login = async () => {
  console.log(member); // 디버깅용 출력
  try {
    await auth.login(member); // 인증 스토어의 login 액션 호출
    console.log(auth.isLogin);
    router.push('/'); // 로그인 성공 시 홈으로 이동
  } catch (e) {
    // 로그인 실패 처리
    console.log('에러=======', e);
    error.value = e.response.data; // 에러 메시지 출력
  }
};
</script>
