package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.common.util.UploadFiles;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member") // 전체 URI prefix
public class MemberController {

    final MemberService service;

    // 아이디 중복 체크 (GET /api/member/checkusername/{username})
    @GetMapping("/checkusername/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(service.checkDuplicate(username));
    }

    // 회원가입 (POST /api/member)
    @PostMapping("")
    public ResponseEntity<MemberDTO> join(@ModelAttribute MemberJoinDTO member) {
        return ResponseEntity.ok(service.join(member));
    }

    @GetMapping("/{username}/avatar")
    public void getAvatar(@PathVariable String username, HttpServletResponse response) {
        String avatarPath = "c:/upload/avatar/" + username + ".png";
        File file = new File(avatarPath);

        // 아바타 이미지 없으면 디폴트 이미지로 대체
        if (!file.exists()) {
            file = new File("c:/upload/avatar/unknown.png");
        }

        // 이미지 파일 응답 전송
        UploadFiles.downloadImage(response, file);
    }
}