package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService {

	@Override
	public String getMessage(String msg, String url) {
		String message = "<script>alert( '" +msg+ "' );";
		message += "location.href='" +url+ "';</script>";
		return message;
	}

	@Override
	public String saveFile(MultipartFile img_FileName) {
		SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmss-");
		
		String sysFileName = fo.format(new Date());
		sysFileName += img_FileName.getOriginalFilename(); 
		
		File saveFile = new File(IMAGE_REPO+"/"+ sysFileName);
		
		try {
			img_FileName.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}

	@Override
	public void deleteImage(String fileName) {
		File file = new File(IMAGE_REPO+"/"+fileName);
		file.delete();
	}

}
