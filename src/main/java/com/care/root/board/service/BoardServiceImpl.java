package com.care.root.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	@Override
	public Map<String, Object> boardAllList(int num) {
		
		int pageLetter = 3; // �������� �� ����
		int allCount = mapper.selectBoardCount(); // �� �� ����
		int repeat = allCount/ pageLetter;	// �� ������ ��
		if (allCount % pageLetter != 0) {
			repeat++;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.boardAllList(start, end));
		
		return map;
	}

	@Override
	public String writeSave(BoardDTO dto, MultipartFile img_FileName) {
		
		if(img_FileName.isEmpty()) {	// ������ ���� ���
			dto.setImgFileName("nan");
		}else {		// ������ �����ϴ� ���
			dto.setImgFileName(bfs.saveFile(img_FileName));
		}
		
		int result = mapper.writeSave(dto);
		String msg="", url="";
		
		if(result == 1) { 		// DB�� ���������� ����
			msg="������ �߰��Ǿ����ϴ�";
			url="/root/board/boardAllList";
			// root => requset.getContextPath()
		}else {					// DB�� ���� ����
			msg="������ �߻��߽��ϴ�";
			url="/board/writeForm";
		}
		return bfs.getMessage(msg, url);
	}

	@Override
	public BoardDTO contentView(int writeNo) {
		upHit(writeNo);
		return mapper.getContent(writeNo);
	}
	
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}

	@Override
	public BoardDTO getContent(int writeNo) {
		return mapper.getContent(writeNo);
	}

	@Override
	public String modify(BoardDTO dto, MultipartFile file) {
		
		String originName = null;
		
		if( !file.isEmpty()) {	// ������
			originName = dto.getImgFileName();
			dto.setImgFileName(bfs.saveFile(file));
		}
		
		int result = mapper.modify(dto);
		
		String msg="", url="";
		if(result == 1) {
			// ���� �̹��� ���� originName
			// bfs.deleteImage(originName);
			msg = "���� �Ǿ����ϴ�";
			url = "/root/board/content_view?writeNo="+dto.getWriteNo();
		
		}else {
			// ������ �̹��� ���� dto.getImgFileName
			// bfs.deleteImage(dto.getImgFileName());
			originName = dto.getImgFileName();
			msg = "���� �߻�!";
			url = "/root/board/modify_form?writeNo="+dto.getWriteNo();
		}
		
		bfs.deleteImage(originName);
		
		return bfs.getMessage(msg, url);
	}

	@Override
	public String delete(int writeNo, String fileName) {
		int result = mapper.delete(writeNo);
		
		String msg="", url="";
		if(result == 1) {
			bfs.deleteImage(fileName);
			msg= "���� �Ǿ����ϴ�";
			url = "/root/board/boardAllList";
		
		}else {
			msg = "���� �߻�!";
			url = "/root/board/content_view?writeNo="+writeNo;
		}
		
		return bfs.getMessage(msg, url);
	}
	
	
	
	

	@Override
	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
	}

	@Override
	public List<BoardRepDTO> getRepList(int write_group) {
		return mapper.getRepList(write_group);
	}

}
