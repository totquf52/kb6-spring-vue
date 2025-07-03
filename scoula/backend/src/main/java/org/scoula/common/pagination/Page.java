package org.scoula.common.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Page<T> {

    private int totalCount;   // 전체 데이터 개수
    private int totalPage;    // 전체 페이지 수

    @JsonIgnore
    private PageRequest pageRequest;  // 요청 정보 (page, amount)

    private List<T> list;     // 현재 페이지에 표시할 데이터 목록

    // 정적 팩토리 메서드: Page 객체 생성
    public static <T> Page<T> of(PageRequest pageRequest, int totalCount, List<T> list) {
        // 전체 페이지 수 계산 (총 게시글 수 / 한 페이지당 글 수)
        int totalPage = (int) Math.ceil((double) totalCount / pageRequest.getAmount());
        return new Page<>(totalCount, totalPage, pageRequest, list);
    }

    // 현재 페이지 번호 반환
    public int getPageNum() {
        return pageRequest.getPage();
    }

    // 한 페이지당 데이터 개수 반환
    public int getAmount() {
        return pageRequest.getAmount();
    }
}