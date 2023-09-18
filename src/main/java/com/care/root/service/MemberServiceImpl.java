package com.care.root.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.root.dto.MemberDTO;
import com.care.root.mybatis.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired MemberMapper mm;
	
	BCryptPasswordEncoder encoder;
	public MemberServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}
	
	@Override
	public int logChk(String id, String pw) {
		MemberDTO dto = mm.getMember(id);
		int result = 1;
		if(dto != null) {
			System.out.println(dto.getId());
			System.out.println(dto.getPw());
			System.out.println(dto.getAddr());
			if (encoder.matches(pw, dto.getPw()) || pw.equals(dto.getPw())) {
				result = 0;
			}
		}
		return result;
	}

	@Override
	public List<MemberDTO> getList() {
		List<MemberDTO> list = null;
		try {
			list = mm.getList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void regChk(MemberDTO dto, String[] addr) {
		String ad = "";
		for(String a : addr) {
			ad += a+"/";
		}
		dto.setAddr(ad);
		System.out.println("평문 : "+ dto.getPw());
		System.out.println("암호문 : "+ encoder.encode(dto.getPw()));
		
		dto.setPw(encoder.encode(dto.getPw()));
		try {
			mm.regChk(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getMember(String id) {
		MemberDTO dto = mm.getMember(id);
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
		System.out.println(dto.getAddr());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);

		String[] addr = dto.getAddr().split("/");
		if(addr.length > 1) {
			map.put("addr1", addr[0]);
			map.put("addr2", addr[1]);
			map.put("addr3", addr[2]);
		}
		return map;
	}

	@Override
	public void mod(MemberDTO dto, String[] addr) {
		String ad = "";
		for(String a : addr) {
			ad += a+"/";
		}
		
		dto.setAddr(ad);
		dto.setPw(encoder.encode(dto.getPw()));
		
		try {
			mm.mod(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void keepLogin(String sessionId, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", sessionId);
		map.put("id", id);
		mm.keepLogin(map);
	}

	@Override
	public MemberDTO getUserSessionId(String sessionId) {
		
		return mm.getUserSessionId(sessionId);
	}
}
