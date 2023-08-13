package com.coding404.myweb.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {
	private int start; //시작 페이지네이션
	private int end; //끝 페이지네이션
	private boolean prev; //이전버튼 활성화여부
	private boolean next; //다음버튼 활성화여부
	private int total; //전체 게시글 수 
	private int realEnd; //실제보여지는 끝번호
	
	//
	private int page; //cri에 있는 현재 조회하고있는 페이지
	private int amount; //cri에 있는 한번에 출력할 데이터 갯수
	private Criteria cri; //페이징 기준 검색한 데이터를 뽑아줄 기준이 cri에 있음
	
	private int pnCount = 10;
	
	private List<Integer> pageList; //페이지네이션을 리스트로 저장
	
	
	//페이지네이션 클래스는 cri와 total을 매개변수로 받음
	// total => 동적쿼리에 따라 count(*) as total 의 갯수가 달라진다. 그 값을 전달하는거임
	public PageVO(Criteria cri, int total) {
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		
		//end 페이지 계산
		//page가 1을 가르킬 때, end = 10
		//page가 11을 가르치면 end = 20
		// => this.end = (int)(Math.ceil( 현재 조회하는 페이지 ) / 페이징 처리 갯수 ) * 페이징 처리 갯수;
		this.end = (int)(Math.ceil(this.page / (double)this.pnCount )) * this.pnCount;
		
		//start 페이지 계산
		//this.start = this.end - 페이징 처리 개수 +1;
		this.start = this.end -this.pnCount +1;
		
		//실제 끝번호의 계산
		// ex 총 게시글 수가 53개 => 마지막 번호는 6 (한 페이지에 10개씩 뽑는다고 가정)
		// ex 총 게시글 수가 232 개 => 마지막 번호는 24
		// this.realEnd = (int)(Math.ceil(전체 게시글 수  / 한 페이지의 데이터 갯수)); 
		this.realEnd = (int)(Math.ceil(this.total / (double)this.amount));
		
		//endPage 재결정
		// ex) 데이터가 25개 end = 10, realEnd = 3 (이런 경우에는 realEnd 번호를 따라가야한다.)
		// ex) 데이터 153 -> end = 20, realEnd = 16 (realEnd 따라감)
		// ex) 데이터 153(현페이지 3) => end=10, realEnd = 16 (end를 따라감) 
		
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		//prev 활성화 여부
		//start페이지 결정은 1, 11, 21, 31, .....
		this.prev = this.start > 1 ;
		
		//next 활성화
		// end = 10, realEnd = 16 이라고 하면 다음버튼이 있어야한다는 의미
		this.next = this.realEnd > this.end;
		
		
		//타임리프 - 리스트에 페이지네이션을 담음
		//rangeClosed(a,b) =>  a~b(를 포함하여) 까지 숫자 반환
		// => start ~ end 까지 숫자를 뽑아서 pageList에 담는 과정
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	}
	

	
	
	
}
