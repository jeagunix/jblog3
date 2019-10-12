package kr.co.itcen.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileuploadException;

@Service
public class FileuploadService {
	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "assets/logo";
	public String restore(MultipartFile multipartFile) {
		String url = "";
		try {				
			if(multipartFile==null) {
				return url;
			}
			
			String originalFilename = multipartFile.getOriginalFilename();			
			String saveFileName = generateSaveFileName(originalFilename.substring(originalFilename.lastIndexOf('.')+1));			
			byte[] fileData =  multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH+"/"+saveFileName);
			os.write(fileData);
			os.close();
		
			url = saveFileName;
			
		} catch (IOException e) {
			throw new FileuploadException();
		}
		
		return url;
	}

	private String generateSaveFileName(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += "."+extName;
		return filename;
	}
	
}