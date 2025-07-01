import api from '@/api';

// 공통 설정
const BASE_URL = '/api/member';
const headers = { 'Content-Type': 'multipart/form-data' };

export default {
  // username 중복 체크
  // 반환값: true → 중복(사용 불가), false → 사용 가능
  async checkUsername(username) {
    const { data } = await api.get(`${BASE_URL}/checkusername/${username}`);
    console.log('AUTH GET CHECKUSERNAME', data);
    return data;
  },

  // 회원가입 요청 (POST)
  // 아바타 업로드가 포함되므로 FormData + multipart/form-data 필요
  async create(member) {
    const formData = new FormData();
    formData.append('username', member.username);
    formData.append('email', member.email);
    formData.append('password', member.password);

    if (member.avatar) {
      formData.append('avatar', member.avatar); // 파일 포함
    }

    const { data } = await api.post(BASE_URL, formData, { headers });
    console.log('AUTH POST: ', data);
    return data;
  },

  // 회원 정보 수정 요청 함수
  async update(member) {
    const formData = new FormData();

    // 필수 항목들 폼데이터에 추가
    formData.append('username', member.username);
    formData.append('password', member.password);
    formData.append('email', member.email);

    // 아바타 파일이 있을 경우에만 추가
    if (member.avatar) {
      formData.append('avatar', member.avatar);
    }

    // 서버에 PUT 요청 전송
    const { data } = await api.put(
      `${BASE_URL}/${member.username}`,
      formData,
      headers
    );

    console.log('AUTH PUT: ', data);
    return data;
  },

  async changePassword(formData) {
    const { data } = await api.put(
      `${BASE_URL}/${formData.username}/changepassword`,
      formData
    );

    console.log('AUTH PUT: ', data);
    return data;
  },
};
