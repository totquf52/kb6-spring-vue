package org.scoula.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelVO {

    // 여행 글 번호 (PK)
    private Long no;

    // 지역(구/군 등)
    private String district;

    // 제목
    private String title;

    // 설명
    private String description;

    // 주소
    private String address;

    // 연락처
    private String phone;

    // 여행 이미지 목록 (1:N 관계)
    private List<TravelImageVO> images;
}