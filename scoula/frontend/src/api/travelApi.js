import api from '@/api'; // axios 인스턴스

const BASE_URL = '/api/travel'; // 여행 API 기본 경로

export default {
  // 여행지 목록 조회 (params: 페이지번호, size 등)
  async getList(params) {
    const { data } = await api.get(BASE_URL, { params });
    console.log('TRAVEL GET LIST: ', data);
    return data;
  },

  // 특정 여행지 상세 조회 (no: 여행지 번호)
  async get(no) {
    const { data } = await api.get(`${BASE_URL}/${no}`);
    console.log('TRAVEL GET', data);
    return data;
  },
};
