package org.scoula.board.service;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.common.pagination.Page;
import org.scoula.common.pagination.PageRequest;

import java.util.List;

/**
 * 📌 게시판 서비스 계층 인터페이스
 * → Controller와 Mapper 사이에서 비즈니스 로직을 수행하는 계층
 */
public interface BoardService {
    // 페이지 요청에 따라 BoardDTO 목록을 포함한 Page 객체 반환
    Page<BoardDTO> getPage(PageRequest pageRequest);

    // 전체 목록 조회
    public List<BoardDTO> getList();

    // 게시글 1건 조회
    public BoardDTO get(Long no);

    // 새 게시글 등록
    public BoardDTO create(BoardDTO board);

    // 기존 게시글 수정
    public BoardDTO update(BoardDTO boardDTO);

    // 게시글 삭제
    public BoardDTO delete(Long no);

    // 첨부파일 추가
    public BoardAttachmentVO getAttachment(Long no);

    // 첨부파일 삭제
    public boolean deleteAttachment(Long no);
}