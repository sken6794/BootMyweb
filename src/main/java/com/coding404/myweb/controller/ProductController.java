package com.coding404.myweb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/productDetail")
	public String productDetail(@RequestParam("prod_id") String id, Model model) {
		
		model.addAttribute("vo", productService.getDetail(id));
		
		
		return "product/productDetail";
	}
	
	@GetMapping("/productList")
	public String productList(Model model, Criteria cri) { //cri를 받을수도 있고 없으면 1,10이 기본값으로 해놨음 
		
		//로그인 기능이 없어서 amdin이라는 계정기반으로 조회
		// 있으면 나중에 세션이나 어디서 로그인정보를 불러와서 걔를 전달
		String writer = "admin";
		//model.addAttribute("list", productService.getList(writer));
		PageVO pageVo = new PageVO(cri, productService.getTotal(writer, cri));
		
		model.addAttribute("list",productService.getList(writer, cri));
		model.addAttribute("pageVO",pageVo);
		
		System.out.println(pageVo.toString());
		
		return "product/productList";
	}
	
	@GetMapping("/productReg")
	public String productReg() {
		return "product/productReg";
	}
	
	//post방식
	//등록요청
	@PostMapping("/registForm")
	public String registForm(ProductVO vo, RedirectAttributes re, @RequestParam("file") List<MultipartFile> list ) {//업로드 기능 추가
		
		//1. 빈 객체 검사
		list = list.stream().filter(t->t.isEmpty()==false).collect(Collectors.toList());
		
		//2. 확장자 검사 (img 유형의 파일만 받아야 함 영상이나 엑셀파일 기타 다른 파일은 안됨)
		for(MultipartFile file : list) {
			if(file.getContentType().contains("image")==false) {
				re.addFlashAttribute("msg","jpg, jpeg, png 이미지 파일만 등록 가능합니다");
				return "redirect:/product/productList"; //이미지가 아니라면 list목록으로 보냄
			}
		}
		
		//3. 파일을 처리하는 작업은 service 위임 => 애시당초 controller Request객체를 받고 service위임 전략.
		
		
		int result = productService.productRegist(vo,list);
		String msg = result ==1 ? "등록되었습니다" : "등록에 문제가 발생하였습니다.";
		
		re.addFlashAttribute("msg", msg);
		
		return "redirect:/product/productList";
	}
	
	//수정요청
	@PostMapping("/modifyForm")
	public String modifyForm(ProductVO vo, RedirectAttributes re) {
		
		int result = productService.productUpdate(vo);
		String msg = result == 1? "수정되었습니다." : "수정에 문제가 발생했습니다.";
		re.addFlashAttribute("msg",msg);
		
		
		
		return "redirect:/product/productList";
	}
	
	
	//삭제요청
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("prod_id") int id, RedirectAttributes re) {
		
		int result = productService.productDelete(id);
		String msg = result == 1? "삭제되었습니다." : "삭제 과정에 문제가 발생했습니다.";
		re.addFlashAttribute("msg",msg);
		
		
		return "redirect:/product/productList";
	}
	
	
}
