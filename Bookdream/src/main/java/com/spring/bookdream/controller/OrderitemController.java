package com.spring.bookdream.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.spring.bookdream.service.OrderitemService;

import com.spring.bookdream.vo.OrderitemVO;

@Controller
@RequestMapping("/detail/cart")
public class OrderitemController {
	
	
	@Autowired
	private OrderitemService orderitemService;
	
	@Autowired
	private HttpSession session;	
	
	@RequestMapping(value="/orderitem")
	public String orderitemList(OrderitemVO vo, Model model, HttpServletResponse response, String msg, String url) {
	
		// 로그인해야 진입됨
		if (session.getAttribute("user_no") == null) {
			msg = "로그인 후 이용해주세요";
			url ="/user/login.jsp";	
		    try {
		        response.setContentType("text/html; charset=utf-8");
		        PrintWriter w = response.getWriter();
		        w.write("<script>alert('"+msg+"');location.href='"+url+"';</script>");
		        w.flush();
		        w.close();
		    } catch(Exception e) {
		        e.printStackTrace();
		    }
					
		}
		
		int user_no = (int) session.getAttribute("user_no");		
		vo.setUser_no(user_no);
		
		//test
//		vo.setUser_no(1);
//		vo.setBook_no(1);		
//		vo.setBuy_now("Y");
//		vo.setProduct_count(3);

		
		// 바로 가기 구매시
		if (vo.getBuy_now().equals("Y")) {

			OrderitemVO buy_now = orderitemService.getBuyNow(vo);
			
			// 구매할 개수
			buy_now.setProduct_count(vo.getProduct_count());
			buy_now.setTotal_count(vo.getProduct_count());
			buy_now.setBuy_now("Y");

			// 상품의 총 금액
			buy_now.setTotal_price(vo.getProduct_count() * buy_now.getBookVO().getBook_price());

			model.addAttribute("orderitem", buy_now);				 
			model.addAttribute("orderitemCount", buy_now);		
			model.addAttribute("userPoint", orderitemService.userPoint(vo));
			
		} else {

			// (장바구니)주문된 상품 목록 조회
			model.addAttribute("orderitemList", orderitemService.getOrderitemList(vo));	

			// 주문된 상품의 개수, 종류, 상품금액 조회
			model.addAttribute("orderitemCount", orderitemService.getOrderitemCount(vo));	

			// 사용자 포인트 조회
			model.addAttribute("userPoint", orderitemService.userPoint(vo));

		}

		
		return "itemorder/orderitem";
		
	}
	
	
}


