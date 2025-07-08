package org.scoula.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.scoula.common.pagination.PageRequest;
import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;

import java.util.List;

@Mapper
public interface TravelMapper {

    // 전체 데이터 수 얻기 (페이징용)
    int getTotalCount();

    // 지역 목록 얻기 (ex. 강남구, 해운대구 등)
    List<String> getDistricts();

    // 전체 여행지 목록
    List<TravelVO> getTravels();

    // 페이지 요청에 따른 여행지 목록 (페이징)
    List<TravelVO> getPage(PageRequest pageRequest);

    // 지역별 여행지 목록
    List<TravelVO> getTravelsByDistrict(String district);

    // 특정 여행지 정보
    TravelVO getTravel(Long no);

    // 특정 여행지에 포함된 이미지 목록
    List<TravelImageVO> getImages(Long travelNo);

    // 이미지 번호로 이미지 정보 가져오기
    TravelImageVO getImage(Long no);
}