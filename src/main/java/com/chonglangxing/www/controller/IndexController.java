package com.chonglangxing.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LiuChen
 * @date   2016年5月24日 下午2:35:20
 *
 */

@Controller
public class IndexController {
	
	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		
		return "index";
	}
}
