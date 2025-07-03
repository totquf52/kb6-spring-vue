package org.scoula.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.mapper.BoardMapper;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;
import org.scoula.common.util.UploadFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final static String BASE_DIR = "c:/upload/board";
    private final BoardMapper mapper;

    @Override
    public Page<BoardDTO> getPage(PageRequest pageRequest) {

        // 페이징된 게시글 목록 조회 (BoardVO)
        List<BoardVO> boards = mapper.getPage(pageRequest);

        // 전체 게시글 수 조회
        int totalCount = mapper.getTotalCount();

        // BoardVO → BoardDTO 변환
        List<BoardDTO> dtoList = boards.stream()
                .map(BoardDTO::of) // VO → DTO 변환 메서드
                .toList();

        // Page 객체 생성 및 반환
        return Page.of(pageRequest, totalCount, dtoList);
    }

    // 전체 게시글 목록 조회
    @Override
    public List<BoardDTO> getList() {
        log.info("getList..........");
        return mapper.getList().stream()
                .map(BoardDTO::of)
                .toList();
    }

    // 게시글 단건 조회 (첨부 포함)
    @Override
    public BoardDTO get(Long no) {
        log.info("get......" + no);
        BoardDTO board = BoardDTO.of(mapper.get(no));
        log.info("===================" + board);
        return Optional.ofNullable(board)
                .orElseThrow(NoSuchElementException::new);
    }

    // 게시글 등록 + 첨부파일 업로드
    @Transactional // 게시글 + 첨부 insert 트랜잭션 처리
    @Override
    public BoardDTO create(BoardDTO board) {
        log.info("create......" + board);

        BoardVO boardVO = board.toVo();
        mapper.create(boardVO); // 게시글 등록

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(boardVO.getNo(), files);
        }

        return get(boardVO.getNo());
    }

    // 게시글 수정 + 첨부파일 업로드
    @Override
    public BoardDTO update(BoardDTO board) {
        log.info("update...... " + board);

        BoardVO boardVO = board.toVo();
        log.info("update...... " + boardVO);
        mapper.update(boardVO); // 게시글 수정

        // 파일 업로드 처리
        List<MultipartFile> files = board.getFiles();
        if (files != null && !files.isEmpty()) {
            upload(board.getNo(), files);
        }

        return get(board.getNo());
    }

    // 게시글 삭제
    @Override
    public BoardDTO delete(Long no) {
        log.info("delete...." + no);

        BoardDTO board = get(no); // 삭제 전 정보 반환용 조회
        mapper.delete(no); // 실제 삭제
        return board;
    }

    // 첨부파일 단건 조회
    @Override
    public BoardAttachmentVO getAttachment(Long no) {
        return mapper.getAttachment(no);
    }

    // 첨부파일 삭제 (성공 여부 반환)
    @Override
    public boolean deleteAttachment(Long no) {
        return mapper.deleteAttachment(no) == 1;
    }

    // 파일 업로드 처리 (내부 전용 메서드)
    private void upload(Long bno, List<MultipartFile> files) {
        for (MultipartFile part : files) {
            if (part.isEmpty()) continue;

            try {
                // 실제 파일 업로드
                String uploadPath = UploadFiles.upload(BASE_DIR, part);

                // DB 등록용 VO 생성
                BoardAttachmentVO attach = BoardAttachmentVO.of(part, bno, uploadPath);
                mapper.createAttachment(attach);

            } catch (IOException e) {
                throw new RuntimeException(e);
                // log.error(e.getMessage());
            }
        }
    }
}