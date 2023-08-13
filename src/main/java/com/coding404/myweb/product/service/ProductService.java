package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {
	
	
	public int productRegist(ProductVO vo, List<MultipartFile> list);
	//페이징처리 이전 조회
	//public ArrayList<ProductVO> getList(String writer);
	//페이징처리 이후 조회
	public ArrayList<ProductVO> getList(String writer, Criteria cri);
	public ProductVO getDetail(String prod_id);
	public int productUpdate(ProductVO vo);
	public int productDelete(int prod_id);
	public int getTotal(String writer, Criteria cri);
	
	//카테고리 처리
	public ArrayList<CategoryVO> getCategory();
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	public ArrayList<ProductUploadVO> getAjaxImg(int prod_id);
}
