package org.scoula.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.travel.domain.TravelVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelDTO {

    // 업로드용 이미지 파일 리스트
    private List<MultipartFile> imageFiles;

    // 여행지 정보 필드
    private long no;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;

    // 조회용 이미지 정보 리스트
    private List<TravelImageDTO> images;

    // VO → DTO 변환 메서드
    public static TravelDTO of(TravelVO vo) {
        TravelDTO travel = TravelDTO.builder()
                .no(vo.getNo())
                .district(vo.getDistrict())
                .title(vo.getTitle())
                .description(vo.getDescription())
                .address(vo.getAddress())
                .phone(vo.getPhone())
                .build();

        // 이미지 리스트도 함께 변환
        if (vo.getImages() != null) {
            travel.setImages(
                    vo.getImages()
                            .stream()
                            .map(TravelImageDTO::of)
                            .collect(Collectors.toList())
            );
        }

        return travel;
    }
}