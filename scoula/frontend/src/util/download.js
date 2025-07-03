export const downloadFile = async (fileUrl) => {
  try {
    const link = document.createElement('a');      // 가짜 <a> 태그 생성
    link.href = fileUrl;                           // 다운로드 링크 설정
    document.body.appendChild(link);               // DOM에 추가
    link.click();                                  // 강제로 클릭 (다운로드 시작)
    document.body.removeChild(link);               // DOM에서 제거
  } catch (error) {
    console.error(error);                          // 에러 출력
  }
};