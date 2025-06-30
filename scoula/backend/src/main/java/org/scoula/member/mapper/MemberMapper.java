package org.scoula.member.mapper;

import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {

    MemberVO get(String username); // 회원 정보 + 권한 목록 조회 (JOIN 포함)

    MemberVO findByUsername(String username); // ID 중복 체크용

    int insert(MemberVO member); // 회원 정보 삽입

    int insertAuth(AuthVO auth); // 권한 정보 삽입
}