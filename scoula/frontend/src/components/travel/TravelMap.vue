<script setup>
import { reactive, ref } from 'vue';
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';

const props = defineProps({
  title: { Type: String, required: true },
  address: { Type: String, required: true },
});

// 서울 시청 좌표
const coordinate = reactive({
  lat: 37.566826,
  lng: 126.9786567,
});
// KakaoMap이 로드되었을 때 호출되는 콜백 함수
const onLoadKakaoMap = () => {
  // 주소 → 좌표 변환 객체 생성
  const geocoder = new kakao.maps.services.Geocoder();

  // 입력된 주소로 좌표 검색
  geocoder.addressSearch(props.address, (result, status) => {
    console.log(result);
    // 정상적으로 결과를 받아온 경우
    if (status === kakao.maps.services.Status.OK) {
      coordinate.lat = result[0].y; // 위도 설정
      coordinate.lng = result[0].x; // 경도 설정
    }
  });
};
const visibleRef = ref(false); // 초기에는 정보창(hidden)

// 마커 클릭 시 정보창 보이기/숨기기 전환
const onClickKakaoMapMarker = () => {
  visibleRef.value = !visibleRef.value;
};
</script>

<template>
  <div><i class="fa-solid fa-map-location-dot"></i> 주소: {{ address }}</div>
  <kakao-map
    :lat="coordinate.lat"
    :lng="coordinate.lng"
    :level="3"
    :draggable="true"
    style="width: 100%"
    @onLoadKakaoMap="onLoadKakaoMap"
  >
    <!-- 마커 컴포넌트 -->
    <kakao-map-marker
      :lat="coordinate.lat"
      :lng="coordinate.lng"
      :infoWindow="{ content: title, visible: visibleRef }"
      :clickable="true"
      @onClickKakaoMapMarker="onClickKakaoMapMarker"
    />
  </kakao-map>
</template>
