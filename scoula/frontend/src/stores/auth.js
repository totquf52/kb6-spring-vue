// 인증 상태를 관리하는 Pinia 스토어
import { ref, computed, reactive } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

// 초기 상태 정의
const initState = {
  token: '', // 접근 토큰(JWT 등)
  user: {
    username: '', // 사용자 ID
    email: '', // 사용자 이메일
    roles: [], // 권한 목록
  },
};

// 스토어 정의
export const useAuthStore = defineStore('auth', () => {
  const state = ref({ ...initState });

  // 로그인 여부, !! : 강제로 boolean 형변환
  const isLogin = computed(() => !!state.value.user.username);

  // 로그인된 사용자 ID
  const username = computed(() => state.value.user.username);

  // 로그인된 사용자 이메일
  const email = computed(() => state.value.user.email);

  const login = async (member) => {
    // state.value.token = 'test token';
    // state.value.user = {
    //   username: member.username,
    //   email: member.username + '@test.com',
    // };

    const { data } = await axios.post('/api/auth/login', member);
    state.value = { ...data };

    // ‘auth’라는 키에 JSON 직렬화해서 로컬 스토리지에 저장
    localStorage.setItem('auth', JSON.stringify(state.value));
  };

  // 로그아웃 함수
  const logout = () => {
    localStorage.clear(); // localStorage 제거
    state.value = { ...initState }; // 상태 초기화
  };

  // 토큰 조회 함수
  const getToken = () => state.value.token;

  // 초기 상태 로딩 (새로고침 시 유지)
  const load = () => {
    const auth = localStorage.getItem('auth');
    if (auth != null) {
      // 로컬스토리지에 있던 데이터 역직렬화
      state.value = JSON.parse(auth);
      console.log(state.value); // 디버깅용 출력
    }
  };

  // 사용자 이메일 정보를 업데이트하고 로컬스토리지에 반영
  const changeProfile = (member) => {
    state.value.user.email = member.email;
    localStorage.setItem('auth', JSON.stringify(state.value));
  };

  load(); // 컴포넌트 마운트 시 호출

  // 외부에서 사용할 항목들 반환
  return {
    state,
    username,
    email,
    isLogin,
    changeProfile,
    login,
    logout,
    getToken,
  };
});
