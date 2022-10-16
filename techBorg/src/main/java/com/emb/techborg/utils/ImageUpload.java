package com.emb.techborg.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.emb.techborg.controller.CategoryController;

@Component
public class ImageUpload {

	private final String UPLOAD_FOLDER = "C:\\gitpro\\TechBorgProject\\techBorg\\src\\main\\resources\\static\\img\\image-product";
	
	private static final Logger log = LogManager.getLogger(ImageUpload.class);
	
	{
		BasicConfigurator.configure();
	}
	
	public boolean uploadImage(MultipartFile imageProduct){	
		boolean isUpload = false;
        try {
            Files.copy(imageProduct.getInputStream(),
                    Paths.get(UPLOAD_FOLDER + File.separator, imageProduct.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;

        }catch (Exception e){
        	log.error(e);
        }
        return isUpload;
	}
	
	public boolean checkExisted(MultipartFile imageProduct){
		boolean isExisted = false;
        try {
            File file = new File(UPLOAD_FOLDER + "\\" + imageProduct.getOriginalFilename());
            isExisted = file.exists();
        }catch (Exception e){
        	log.error(e);
        }
        return isExisted;
    }	        
}
