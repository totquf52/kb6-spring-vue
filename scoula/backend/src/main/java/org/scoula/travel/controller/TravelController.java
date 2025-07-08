package org.scoula.travel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.common.util.UploadFiles;
import org.scoula.travel.dto.TravelDTO;
import org.scoula.travel.dto.TravelImageDTO;
import org.scoula.travel.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/travel")
public class TravelController {
    final TravelService service;

    @GetMapping("") // 여행지 목록 (페이징)
    public ResponseEntity<Page> getTravels(PageRequest pageRequest) {
        return ResponseEntity.ok(service.getPage(pageRequest));
    }

    @GetMapping("/{no}") // 특정 여행지 상세
    public ResponseEntity<TravelDTO> getTravels(@PathVariable("no") Long no) {
        return ResponseEntity.ok(service.get(no));
    }

    @GetMapping("/image/{no}") // 이미지 출력
    public void viewImage(@PathVariable Long no, HttpServletResponse response) {
        TravelImageDTO image = service.getImage(no);
        File file = new File(image.getPath());
        UploadFiles.downloadImage(response, file);
    }
}