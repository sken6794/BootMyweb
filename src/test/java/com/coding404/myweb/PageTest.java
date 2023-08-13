package com.coding404.myweb;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductMapper;
import com.coding404.myweb.util.Criteria;

@SpringBootTest
public class PageTest {
		
		@Autowired
		private ProductMapper productMapper;
	//Test 클래스에서 더미데이터 넣는 과정
//		@Test
//		public void testCode1() {
//			for(int i = 1; i<=100; i++) {
//				
//				ProductVO vo = ProductVO.builder()
//						.prod_enddate("2023-06-18")
//						.prod_writer("aaa123")
//						.prod_name("test"+i)
//						.prod_price(1000+i)
//						.prod_count(100+i)
//						.prod_discount(1+i)
//						.prod_purchase_yn("Y")
//						.prod_content("내용테스트"+i+"번")
//						.prod_comment("commetnTest")
//						.build();
//				productMapper.productRegist(vo);
//			}
//			
//		}
		
		@Test
		public void testCode2() {
			Criteria cri = new Criteria(3, 10); //1번페이지, 10개 데이터
			
			ArrayList<ProductVO> list = productMapper.getList("admin", cri);
			System.out.println(list.toString());
		}
}
