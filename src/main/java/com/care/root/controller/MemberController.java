package com.care.root.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.root.common.LoginSession;
import com.care.root.dto.MemberDTO;
import com.care.root.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController implements LoginSession{
	
	@Autowired MemberService ms;
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("logChk")
	public String logChk(@RequestParam String id,
						@RequestParam String pw,
						@RequestParam(required=false, defaultValue="off") String autoLogin,
						HttpSession session,
						RedirectAttributes rs) {
		int result = ms.logChk(id, pw);
		if (result == 0) {
			// LOGIN이 가지고 있는 값은 login이므로 세션 이름은 "login" 생성
			// session.setAttribute(LoginSession.LOGIN, id);
			// 상속 받아서 사용가능
			// session.setAttribute(LOGIN, id);
			
			System.out.println("logChk : "+ autoLogin);
			rs.addAttribute("id", id);
			rs.addAttribute("autoLogin", autoLogin);
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@GetMapping("successLogin")
	public String successLogin(@RequestParam String id,
							@RequestParam String autoLogin,
							HttpSession session,
							HttpServletResponse res) {
		
		System.out.println("successLogin : "+ autoLogin);
		
		if(autoLogin.equals("on")) {
			int limitTime = 60*60*24*90;	//90일 설정

			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			// session.getId() : 고유한 값(JSESSIONID의 값) 
			// 					-> 식별자 값으로 사용가능
			
			loginCookie.setPath("/");	// "/" 하위 모든 경로에 적용
			loginCookie.setMaxAge(limitTime);	// 지정한 90일동안 사용
			res.addCookie(loginCookie);
			
			ms.keepLogin(session.getId(), id);
		}
		session.setAttribute(LOGIN,id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session,
				@CookieValue(required = false) Cookie loginCookie,
				HttpServletResponse res) {
				// @CookieValue(value="loginCookie", required = false) Cookie cookie) {
				// 지정한 이름과 다르게 사용할 때
		if (loginCookie != null) {
			loginCookie.setMaxAge(0);
			res.addCookie(loginCookie);
			
			ms.keepLogin("nan", (String)session.getAttribute(LoginSession.LOGIN));
		}
		session.invalidate();
		// return "redirect:login";
		return "redirect:/index";
	}

	@GetMapping("list")
	public String list(Model model) {
		System.out.println("ctrl list 동작");
		
		model.addAttribute("list", ms.getList());
		return "member/list";
	}
	
	@GetMapping("register_view")
	public String register() {
		return "member/register_view";
	}
	
	@PostMapping("regChk")
	public String regChk(HttpServletRequest req, MemberDTO dto) {
//		String[] addr = req.getParameterValues("addr");
		// 동일한 이름의 값을 배열형식으로 가져옴
		
//		String ad = "";
//		for (String a : addr) {
//			System.out.println(a);
//			ad += a+"/";
//		}
//		System.out.println(ad);
//		System.out.println("----------------------");
//		
//		String[] addr02 = ad.split("/");
//		for (String a2 : addr02) {
//			System.out.println(a2);
//		}
//		System.out.println("ctrl dto : "+ dto.getId());
//		System.out.println("ctrl dto : "+ dto.getPw());
	
		ms.regChk(dto, req.getParameterValues("addr"));
		
		return "redirect:login";
	}
	
	@GetMapping("info")
	public String info (@RequestParam String id, Model model) {
		model.addAttribute("mem", ms.getMember(id));
		return "member/info";
	}
	
	@GetMapping("modify")
	public String modify(@RequestParam String id, Model model) {
		model.addAttribute("mem", ms.getMember(id));
		return "member/modify";
	}
	
	@PostMapping("mod")
	public String mod(HttpServletRequest req, MemberDTO dto) {
		ms.mod(dto, req.getParameterValues("addr"));
		return "redirect:list";
	}
}
