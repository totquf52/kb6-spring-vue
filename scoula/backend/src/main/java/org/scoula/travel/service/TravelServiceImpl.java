package org.scoula.travel.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.travel.domain.TravelImageVO;
import org.scoula.travel.domain.TravelVO;
import org.scoula.travel.dto.TravelDTO;
import org.scoula.travel.dto.TravelImageDTO;
import org.scoula.travel.mapper.TravelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    private final TravelMapper mapper;

    // 페이지 요청에 따른 여행지 목록 조회 (이미지 포함)
    @Override
    public Page<TravelDTO> getPage(PageRequest pageRequest) {
        List<TravelDTO> travels = mapper.getPage(pageRequest)
                .stream()
                .map(TravelDTO::of)
                .collect(Collectors.toList());

        // 각 여행지에 해당하는 이미지 조회 및 세팅
        travels.forEach(travel -> {
            List<TravelImageDTO> images = mapper.getImages(travel.getNo())
                    .stream()
                    .map(TravelImageDTO::of)
                    .collect(Collectors.toList());
            travel.setImages(images);
        });

        int totalCount = mapper.getTotalCount();

        return Page.of(pageRequest, totalCount, travels);
    }

    // 전체 여행지 목록 조회
    @Override
    public List<TravelDTO> getList() {
        return mapper.getTravels()
                .stream()
                .map(TravelDTO::of)
                .collect(Collectors.toList());
    }

    // 특정 여행지 상세 조회
    @Override
    public TravelDTO get(Long no) {
        TravelVO travel = mapper.getTravel(no);
        if (travel == null) {
            throw new NoSuchElementException();
        }
        return TravelDTO.of(travel);
    }

    // 특정 이미지 상세 조회
    @Override
    public TravelImageDTO getImage(Long no) {
        TravelImageVO image = mapper.getImage(no);
        return TravelImageDTO.of(image);
    }
}