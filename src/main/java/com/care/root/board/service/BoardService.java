package com.care.root.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.care.root.board.dto.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> boardAllList();
	public String writeSave(BoardDTO dto , MultipartFile img_FileName);
	public BoardDTO contentView(int writeNo);
}
