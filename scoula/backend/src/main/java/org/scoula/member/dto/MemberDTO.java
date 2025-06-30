package org.scoula.member.dto;

import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String username;
    private String email;
    private Date regDate;
    private Date updateDate;

    private MultipartFile avatar;           // 프로필 이미지

    private List<String> authList;          // 권한 목록

    // VO → DTO 변환 (정적 메서드)
    public static MemberDTO of(MemberVO m) {
        return MemberDTO.builder()
                .username(m.getUsername())
                .email(m.getEmail())
                .regDate(m.getRegDate())
                .updateDate(m.getUpdateDate())
                .authList(
                        m.getAuthList().stream()
                                .map(a -> a.getAuth())     // VO에서 권한 문자열 추출
                                .toList()
                )
                .build();
    }

    // DTO → VO 변환 (인스턴스 메서드)
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .email(email)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}