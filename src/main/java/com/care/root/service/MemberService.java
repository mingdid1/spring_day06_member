package com.care.root.service;

import java.util.List;
import java.util.Map;

import com.care.root.dto.MemberDTO;

public interface MemberService {
	
	public int logChk(String id, String pw);
	public List<MemberDTO> getList();
	// ArrayList ����� �޾ƿö��� �θ����·� ���
	public void regChk(MemberDTO dto, String[] addr);
	public Map<String, Object> getMember(String id);
	// HashMap �θ����� ���
	public void mod(MemberDTO dto, String[] addr);
	public void keepLogin(String sessionId, String id);
	public MemberDTO getUserSessionId(String sessionId);
}
