// 인증 상태 확인용 가드 함수 정의
import { useAuthStore } from '@/stores/auth';

// 로그인 여부를 확인하고, 로그인 안 되어 있으면 로그인 페이지로 리디렉트
export const isAuthenticated = (to, from) => {
  const auth = useAuthStore(); // Pinia store에서 인증 상태 가져오기

  if (!auth.isLogin) {
    console.log('로그인 필요.....');
    // 로그인 페이지로 리디렉트하면서 현재 이동하려는 페이지 이름을 쿼리로 전달
    return {
      name: 'login',
      query: { next: to.name },
    };
  }

  console.log('로그인 인증');
};
