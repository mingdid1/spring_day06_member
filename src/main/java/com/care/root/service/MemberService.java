package com.care.root.service;

import java.util.List;
import java.util.Map;

import com.care.root.dto.MemberDTO;

public interface MemberService {
	
	public int logChk(String id, String pw);
	public List<MemberDTO> getList();
	// ArrayList 결과를 받아올때는 부모형태로 사용
	public void regChk(MemberDTO dto, String[] addr);
	public Map<String, Object> getMember(String id);
	// HashMap 부모형태 사용
	public void mod(MemberDTO dto, String[] addr);
	public void keepLogin(String sessionId, String id);
	public MemberDTO getUserSessionId(String sessionId);
}
