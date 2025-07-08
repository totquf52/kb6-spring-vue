package org.scoula.travel.service;

import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.travel.dto.TravelDTO;
import org.scoula.travel.dto.TravelImageDTO;

import java.util.List;

public interface TravelService {

    // 페이징 처리된 여행지 목록 조회
    Page<TravelDTO> getPage(PageRequest pageRequest);

    // 전체 여행지 목록 조회
    List<TravelDTO> getList();

    // 특정 여행지 상세 조회 (이미지 포함)
    TravelDTO get(Long no);

    // 특정 이미지 단건 조회
    TravelImageDTO getImage(Long no);
}