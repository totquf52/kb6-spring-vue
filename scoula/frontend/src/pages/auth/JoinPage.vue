<template>
  <div class="mt-5 mx-auto" style="width: 500px">
    <h1 class="my-5"><i class="fa-solid fa-user-plus"></i> 회원 가입</h1>

    <form @submit.prevent="join">
      <!-- 사용자 ID 입력 -->
      <div class="mb-3 mt-3">
        <label for="username" class="form-label">
          <i class="fa-solid fa-user"></i> 사용자 ID :
          <!-- ID 중복 확인 버튼 -->
          <button
            type="button"
            class="btn btn-success btn-sm py-0 me-2"
            @click="checkUsername"
          >
            ID 중복 확인
          </button>
          <!-- 중복 결과 메시지 -->
          <span :class="disableSubmit.value ? 'text-primary' : 'text-danger'">
            {{ checkError }}
          </span>
        </label>
        <input
          type="text"
          class="form-control"
          placeholder="사용자 ID"
          id="username"
          v-model="member.username"
          @input="changeUsername"
        />
      </div>
      <!-- 아바타 이미지 업로드 -->
      <div>
        <label for="avatar" class="form-label">
          <i class="fa-solid fa-user-astronaut"></i> 아바타 이미지:
        </label>
        <!-- ref : avatar 요소 참조, 초기값은 null -->
        <input
          type="file"
          class="form-control"
          id="avatar"
          ref="avatar"
          accept="image/png, image/jpeg"
        />
      </div>
      <!-- 이메일 입력 -->
      <div class="mb-3 mt-3">
        <label for="email" class="form-label">
          <i class="fa-solid fa-envelope"></i>
          email
        </label>
        <input
          type="email"
          class="form-control"
          placeholder="Email"
          id="email"
          v-model="member.email"
        />
      </div>
      <!-- 비밀번호 입력 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호"
          id="password"
          v-model="member.password"
        />
      </div>

      <!-- 비밀번호 확인 -->
      <div class="mb-3">
        <label for="password" class="form-label">
          <i class="fa-solid fa-lock"></i> 비밀번호 확인:
        </label>
        <input
          type="password"
          class="form-control"
          placeholder="비밀번호 확인"
          id="password2"
          v-model="member.password2"
        />
      </div>

      <!-- 가입 버튼 (중복 체크 후에만 활성화) -->
      <button
        type="submit"
        class="btn btn-primary mt-4"
        :disabled="disableSubmit"
      >
        <i class="fa-solid fa-user-plus"></i> 확인
      </button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import authApi from '@/api/authApi'; // 회원 API 모듈

const router = useRouter();

// 아바타 파일 input 태그용 ref
const avatar = ref(null);

// 에러 메시지 출력용 ref
const checkError = ref('');

// 사용자 입력 정보
const member = reactive({
  username: '',
  email: '',
  password: '',
  password2: '',
  avatar: null,
});

// 가입 버튼 비활성화 여부 (true: 비활성화)
const disableSubmit = ref(true);

// 사용자 ID 중복 체크
const checkUsername = async () => {
  if (!member.username) {
    return alert('사용자 ID를 입력하세요.');
  }

  // API 호출로 중복 여부 확인 (true: 중복, false: 사용 가능)
  disableSubmit.value = await authApi.checkUsername(member.username);
  checkError.value = disableSubmit.value
    ? '이미 사용중인 ID입니다.'
    : '사용가능한 ID입니다.';
};

// 사용자 ID 입력 변경 시 중복 확인 초기화
const changeUsername = () => {
  disableSubmit.value = true;
  checkError.value = member.username ? 'ID 중복 체크를 하셔야 합니다.' : '';
};

// 회원가입 처리 함수
const join = async () => {
  if (member.password !== member.password2) {
    return alert('비밀번호가 일치하지 않습니다.');
  }

  // 아바타 파일 선택 시 member에 저장
  if (avatar.value?.files.length > 0) {
    member.avatar = avatar.value.files[0];
  }

  try {
    await authApi.create(member); // 회원가입 요청
    router.push({ name: 'home' }); // 홈 또는 로그인 페이지로 이동
  } catch (e) {
    console.error(e);
  }
};
</script>
