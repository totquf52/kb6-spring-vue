import api from '@/api'; // 공통 axios 인스턴스

const BASE_URL = '/api/board';
// multipart 요청을 위한 헤더 설정
const headers = { 'Content-Type': 'multipart/form-data' };

export default {
  // 게시글 목록 조회 API
  async getList(params) {
    const { data } = await api.get(BASE_URL, { params });
    console.log('BOARD GET LIST:', data);
    return data;
  },
  // 게시글 등록 API
  async create(article) {
    const formData = new FormData();

    // 기본 정보 추가
    formData.append('title', article.title);
    formData.append('writer', article.writer);
    formData.append('content', article.content);

    // 첨부파일이 있다면 반복 추가
    if (article.files) {
      for (let i = 0; i < article.files.length; i++) {
        formData.append('files', article.files[i]);
      }
    }

    // API 호출
    const { data } = await api.post(BASE_URL, formData, { headers });
    console.log('BOARD POST: ', data);
    return data;
  },

  // 게시글 상세조회 (GET /api/board/{no})
  async get(no) {
    const { data } = await api.get(`${BASE_URL}/${no}`); // 번호로 게시글 조회
    console.log('BOARD GET', data); // 응답 데이터 출력
    return data; // 조회된 게시글 반환
  },

  // 게시글 삭제 (DELETE /api/board/{no})
  async delete(no) {
    const { data } = await api.delete(`${BASE_URL}/${no}`); // 번호로 게시글 삭제 요청
    console.log('BOARD DELETE: ', data); // 응답 데이터 출력
    return data; // 삭제 결과 반환
  },

  // 게시글 수정
  async update(article) {
    const formData = new FormData();

    // 기본 게시글 정보 추가
    formData.append('no', article.no);
    formData.append('title', article.title);
    formData.append('writer', article.writer);
    formData.append('content', article.content);

    // 첨부파일이 있다면 files[]로 추가
    if (article.files) {
      for (let i = 0; i < article.files.length; i++) {
        formData.append('files', article.files[i]);
      }
    }

    // PUT 요청으로 수정 처리
    const { data } = await api.put(`${BASE_URL}/${article.no}`, formData, {
      headers,
    });
    console.log('BOARD PUT: ', data);
    return data;
  },

  // 첨부파일 삭제
  async deleteAttachment(no) {
    // DELETE 요청으로 첨부파일 삭제 처리
    const { data } = await api.delete(`${BASE_URL}/deleteAttachment/${no}`);
    console.log('ATTACHMENT DELETE: ', data);
    return data;
  },
};
