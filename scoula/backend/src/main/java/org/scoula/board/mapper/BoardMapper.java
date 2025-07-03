package org.scoula.board.mapper;

import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.domain.BoardVO;
import org.scoula.common.pagination.PageRequest;

import java.util.List;

public interface BoardMapper {
    // 전체 게시글 수 조회 (페이징 전체 페이지 수 계산용)
    int getTotalCount();

    // 페이징 처리된 게시글 목록 조회
    List<BoardVO> getPage(PageRequest pageRequest);

    // 📝 게시글 관련 기능
    List<BoardVO> getList();               // 전체 게시글 목록 조회
    BoardVO get(Long no);                  // 특정 게시글 조회
    void create(BoardVO board);            // 게시글 등록
    int update(BoardVO board);             // 게시글 수정
    int delete(Long no);                   // 게시글 삭제

    // 📎 첨부파일 관련 기능
    void createAttachment(BoardAttachmentVO attach);         // 첨부파일 등록
    List<BoardAttachmentVO> getAttachmentList(Long bno);     // 게시글 번호로 첨부파일 목록 조회
    BoardAttachmentVO getAttachment(Long no);                // 첨부파일 번호로 단일 조회
    int deleteAttachment(Long no);                           // 첨부파일 삭제
}