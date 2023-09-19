package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	
	public int selectBoardCount();
	
	// 2개 이상의 변수를 넘길때는 param이라는 어노테이션을 사용해야함
	public List<BoardDTO> boardAllList(@Param("s") int start, @Param("e") int end);
	public int writeSave(BoardDTO dto);
	public BoardDTO getContent(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	public int delete(int writeNo);
	
	public void addReply(BoardRepDTO dto);
	public List<BoardRepDTO> getRepList(int write_group);
}
