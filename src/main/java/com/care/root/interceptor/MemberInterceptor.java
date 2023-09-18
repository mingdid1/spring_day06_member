package com.care.root.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.care.root.common.LoginSession;

// servlet-context.xml beans �־������ ��밡��
public class MemberInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// ����Ʈ ������ session���� �α��� ���� Ȯ�� �� �޽��� ���� 
		HttpSession session = request.getSession();
		if( session.getAttribute(LoginSession.LOGIN) == null) {
			// response.sendRedirect("login");
			
			String msg="<script>alert('�α��� ���� �ϼ���');";
			msg += "location.href='/root/member/login';</script>";
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.print(msg);
		
			return false;
		}
		System.out.println("list ctrl �� ���� ------");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("-------- list ctrl �� ����");
	}
	
}
