// 인증 가드 함수 임포트
import { isAuthenticated } from '@/util/guards';
export default [
  // 게시글 목록 페이지
  {
    path: '/board/list',
    name: 'board/list',
    component: () => import('../pages/board/BoardListPage.vue'),
  },

  // 게시글 상세 페이지
  {
    path: '/board/detail/:no',
    name: 'board/detail',
    component: () => import('../pages/board/BoardDetailPage.vue'),
  },

  {
    path: '/board/create', // 게시글 작성 페이지
    name: 'board/create',
    component: () => import('../pages/board/BoardCreatePage.vue'),
    beforeEnter: isAuthenticated, // 인증 여부 확인
  },
  {
    path: '/board/update/:no', // 게시글 수정 페이지 (no: 게시글 번호)
    name: 'board/update',
    component: () => import('../pages/board/BoardUpdatePage.vue'),
    beforeEnter: isAuthenticated, // 인증 여부 확인
  },
];
