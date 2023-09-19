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
		
		int pageLetter = 3; // 페이지당 글 개수
		int allCount = mapper.selectBoardCount(); // 글 총 개수
		int repeat = allCount/ pageLetter;	// 총 페이지 수
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
		
		if(img_FileName.isEmpty()) {	// 파일이 없는 경우
			dto.setImgFileName("nan");
		}else {		// 파일이 존재하는 경우
			dto.setImgFileName(bfs.saveFile(img_FileName));
		}
		
		int result = mapper.writeSave(dto);
		String msg="", url="";
		
		if(result == 1) { 		// DB에 성공적으로 저장
			msg="새글이 추가되었습니다";
			url="/root/board/boardAllList";
			// root => requset.getContextPath()
		}else {					// DB에 저장 실패
			msg="문제가 발생했습니다";
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
		
		if( !file.isEmpty()) {	// 수정됨
			originName = dto.getImgFileName();
			dto.setImgFileName(bfs.saveFile(file));
		}
		
		int result = mapper.modify(dto);
		
		String msg="", url="";
		if(result == 1) {
			// 기존 이미지 삭제 originName
			// bfs.deleteImage(originName);
			msg = "수정 되었습니다";
			url = "/root/board/content_view?writeNo="+dto.getWriteNo();
		
		}else {
			// 수정된 이미지 삭제 dto.getImgFileName
			// bfs.deleteImage(dto.getImgFileName());
			originName = dto.getImgFileName();
			msg = "문제 발생!";
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
			msg= "삭제 되었습니다";
			url = "/root/board/boardAllList";
		
		}else {
			msg = "문제 발생!";
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
