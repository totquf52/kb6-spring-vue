package org.scoula.member.dto;

import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;
import lombok.*;

// Lombok 어노테이션으로 생성자, getter/setter, builder 등을 자동 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinDTO {
    private String username;
    private String password;
    private String email;
    private MultipartFile avatar; // 프로필 이미지 (파일 업로드용)

    // DTO를 VO로 변환하는 메서드
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
