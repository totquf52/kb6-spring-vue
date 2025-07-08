package org.scoula.travel.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.travel.domain.TravelImageVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelImageDTO {

    private static final String BASE = "c:/upload/travel/";

    private long no;
    private String filename;
    private long travelNo;

    // VO → DTO 변환 메서드
    public static TravelImageDTO of(TravelImageVO vo) {
        return TravelImageDTO.builder()
                .no(vo.getNo())
                .filename(vo.getFilename())
                .travelNo(vo.getTravelNo())
                .build();
    }

    // 서버 내부에서 사용할 실제 파일 경로 반환
    @JsonIgnore // 프론트에 전달되지 않도록 처리
    public String getPath() {
        return BASE + filename;
    }

    // 프론트엔드에서 사용할 이미지 API 주소
    public String getUrl() {
        return "/api/travel/image/" + no;
    }
}