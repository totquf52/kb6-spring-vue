export default [
  {
    path: '/travel/list', // 여행지 목록 페이지
    name: 'travel/list',
    component: () => import('../pages/travel/TravelListPage.vue'),
  },
  {
    path: '/travel/detail/:no', // 여행지 상세 페이지
    name: 'travel/detail',
    component: () => import('../pages/travel/TravelDetailPage.vue'),
  },
];
