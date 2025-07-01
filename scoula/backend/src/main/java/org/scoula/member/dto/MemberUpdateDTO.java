package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 회원 정보 수정 시 사용되는 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {

    // 사용자 이름
    private String username;

    // 사용자 비밀번호
    private String password;

    // 사용자 이메일
    private String email;

    // 프로필 이미지 파일 (Multipart)
    private MultipartFile avatar;

    /**
     * DTO → VO로 변환
     * 비밀번호와 프로필 이미지는 VO에 포함하지 않음
     */
    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .email(email)
                .build();
    }
}
