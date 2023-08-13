package com.coding404.myweb.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper
public interface ProductMapper {
	public int productRegist(ProductVO vo);//상품등록
	
	public void productFileRegist(ProductUploadVO vo);
	//public ArrayList<ProductVO> getList(String writer);
	//페이징처리 이후 조회
		public ArrayList<ProductVO> getList(@Param("writer") String writer,
											@Param("cri") Criteria cri);
	public ProductVO getDetail(String prod_id);
	public int productUpdate(ProductVO vo);
	public int productDelete(int prod_id);
	public int getTotal(@Param("writer") String writer,
						@Param("cri") Criteria cri); //검색 조건에 맞춰서 total값도 변경되어야 한다.
	
	
	public ArrayList<CategoryVO> getCategory();
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	//비동기 이미지 얻기
	public ArrayList<ProductUploadVO> getAjaxImg(int prod_id);
}
