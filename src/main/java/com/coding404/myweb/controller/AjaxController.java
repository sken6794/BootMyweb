package com.coding404.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.product.service.ProductService;

@RestController
public class AjaxController {

	@Autowired
	private ProductService productService;

	@Value("${project.upload.path}")
	private String uploadPath;
	@GetMapping("/getCategory")
	public ResponseEntity<ArrayList<CategoryVO>> getCategory(){

		return new ResponseEntity<>(productService.getCategory(), HttpStatus.OK);
	}

	@GetMapping("/getCategoryChild/{a}/{b}/{c}")
	public ResponseEntity<ArrayList<CategoryVO>> getCategoryChild(  @PathVariable("a") String group_id,
			@PathVariable("b") int category_lv,
			@PathVariable("c") int category_detail_lv){
		//System.out.println(group_id+" : " + category_lv +" : " + category_detail_lv);

		CategoryVO vo = CategoryVO.builder()
				.group_id(group_id)
				.category_lv(category_lv)
				.category_detail_lv(category_detail_lv)
				.build();
		return new ResponseEntity<>(productService.getCategoryChild(vo), HttpStatus.OK);
	}


	////////////////////////////////////////////////실습///////////////////////
	//이미지 데이터 화면에 응답하기
	/*
	 * 1. 화면에서 넘어오는 getAjaxImg 요청을 받는 REST API를 생성
	 * 1-1. 서비스 , 매퍼 함수 생성
	 * 2. getAjaxImg()로 업로드 테이블에서 조회한 결과를 화면으로 전달
	 */
	@PostMapping("/getAjaxImg")
	public ResponseEntity<ArrayList<ProductUploadVO>> getAjaxImg(@RequestBody ProductUploadVO vo){
		System.out.println(vo.toString());

		return new ResponseEntity<ArrayList<ProductUploadVO>>(productService.getAjaxImg(vo.getProd_id()), HttpStatus.OK);
	}
	
	
	//이미지 띄워주기
	@GetMapping("/display")
	public  ResponseEntity<byte[]> display(@RequestParam("filename") String filename,
										   @RequestParam("filepath") String filepath,
										   @RequestParam("uuid") String uuid) {

		byte[] data=null;
		//읽어올 경로
		String path = uploadPath+"/"+filepath+"/"+uuid+"_"+filename;
		try {
			data = FileCopyUtils.copyToByteArray(new File(path));///파일 경로 기반으로 데이터를 구함
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
}
