package org.scoula.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelImageVO {

    // 이미지 번호 (PK)
    private Long no;
    // 저장된 파일 이름
    private String filename;
    // 연결된 여행글 번호 (FK)
    private Long travelNo;
}