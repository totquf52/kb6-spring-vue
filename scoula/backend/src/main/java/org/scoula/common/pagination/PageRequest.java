package org.scoula.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.AccessLevel;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE) // of() 팩토리 메서드로만 객체 생성 유도
public class PageRequest {

    private int page;   // 요청한 페이지 번호
    private int amount; // 한 페이지당 출력할 데이터 개수

    // 기본 생성자: page=1, amount=10으로 초기화
    public PageRequest() {
        page = 1;
        amount = 10;
    }

    // 정적 팩토리 메서드: 원하는 page, amount로 객체 생성
    public static PageRequest of(int page, int amount) {
        return new PageRequest(page, amount);
    }

    // OFFSET 계산: (page - 1) * amount
    public int getOffset() {
        return (page - 1) * amount;
    }
}