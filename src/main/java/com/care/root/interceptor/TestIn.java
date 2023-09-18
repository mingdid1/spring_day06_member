package com.care.root.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.LoginSession;

public class TestIn extends HandlerInterceptorAdapter
						implements LoginSession{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("���ο� ���ͼ��� ���!!");
		
		HttpSession session = request.getSession();
		
		// session.getAttribute(LOGIN) === null
		if(StringUtils.isEmpty(session.getAttribute(LOGIN))) {
			String msg="<script>alert('�α��� ���� �ϼ���');";
			msg += "location.href='/root/member/login';</script>";
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.print(msg);
		
			return false;
		}
		return true;
	}	
}
