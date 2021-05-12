package com.thuctap.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thuctap.exception.FileStorageException;
import com.thuctap.exception.MyFileNotFoundException;
import com.thuctap.property.FileStorageProperties;
import com.thuctap.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	
	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStoragePropertities) {
		this.fileStorageLocation = Paths.get(fileStoragePropertities.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored", e);
		}
	}

	@Override
	public String storeFile(MultipartFile file) throws IOException {
		if (!(file.getOriginalFilename().endsWith(".png") || file.getOriginalFilename().endsWith(".jpeg") || file.getOriginalFilename().endsWith(".jpg")))
			throw new FileStorageException("Only PNG, JPEG and JPG images are allowed");
		
	/*	File f = new File("F:\\react-app\\Img\\"+file.getOriginalFilename());
		
		f.createNewFile();
		FileOutputStream fout = new FileOutputStream(f);
		fout.write(file.getBytes());
		fout.close();
		BufferedImage image = ImageIO.read(f);
		int height = image.getHeight();
		int width = image.getWidth();
		
		if(width > 1000 || height > 1000) {
			   if(f.exists())
				   f.delete();
			   throw new FileStorageException("Invalid file dimensions. File dimension should note be more than 300 X 300");
		   }
		
		   if(f.exists()) {
			   f.delete();
		   }*/
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());   	  
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence");
			}
			String newFileName = System.currentTimeMillis() + "_" + fileName;
			Path targetLocation = this.fileStorageLocation.resolve(newFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return newFileName;
		} catch (Exception e) {
			throw new FileStorageException(String.format("Could not store file %s !! Please try again!", fileName), e);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

}
