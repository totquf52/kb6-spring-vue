package org.scoula.member.service;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberUpdateDTO;

public interface MemberService {

    // 아이디 중복 여부 확인
    boolean checkDuplicate(String username);

    // username으로 회원 정보 조회
    MemberDTO get(String username);

    // 회원가입 (DTO → VO → DB 저장 → DTO 반환)
    MemberDTO join(MemberJoinDTO member);

    // 회원 정보를 수정하고, 수정된 회원 정보를 DTO로 반환하는 메서드
    MemberDTO update(MemberUpdateDTO member);

    // 비밀번호 변경 메서드: 사용자명, 이전 비밀번호, 새 비밀번호 정보가 담긴 DTO를 인자로 받음
    void changePassword(ChangePasswordDTO changePassword);
}