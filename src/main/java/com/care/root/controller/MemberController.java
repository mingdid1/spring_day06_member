package com.care.root.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
						HttpSession session,
						RedirectAttributes rs) {
		int result = ms.logChk(id, pw);
		if (result == 0) {
			// LOGIN�� ������ �ִ� ���� login�̹Ƿ� ���� �̸��� "login" ����
			// session.setAttribute(LoginSession.LOGIN, id);
			// ��� �޾Ƽ� ��밡��
			// session.setAttribute(LOGIN, id);
			
			rs.addAttribute("id", id);
			return "redirect:successLogin";
		}
		return "redirect:login";
	}
	
	@GetMapping("successLogin")
	public String successLogin(@RequestParam String id, 
							HttpSession session) {
		
		session.setAttribute(LOGIN,id);
		return "member/successLogin";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	@GetMapping("list")
	public String list(Model model) {
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
		// ������ �̸��� ���� �迭�������� ������
		
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
