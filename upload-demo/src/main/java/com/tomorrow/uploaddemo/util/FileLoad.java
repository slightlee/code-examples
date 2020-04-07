package com.tomorrow.uploaddemo.util;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class FileLoad {

	/**
	 * 单文件上传
	 */
	public static String upload(MultipartFile file){
		String fileName = file.getOriginalFilename();
		if(fileName.indexOf("\\") != -1){
			fileName = fileName.substring(fileName.lastIndexOf("\\"));
		}
		String filePath = "/Users/ming/local/img/";

		File targetFile = new File(filePath);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath+fileName);
			out.write(file.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
		return "上传成功!";
	}

	/**
	 * 多文件上传
	 */
	public static String uploads(MultipartFile[] file){

			// map 储存MultipartFile[]文件数组 File类上传路径及数组 上传路径及名称（将其保存进数据库）
			Map map = new HashMap();
			// 完整路径
			String listFile = "";

			// 设置文件上传大小为50MB
			int fileSize = 50000000;

			String fileFinishName = null;

			String path = "usr/local/";
			String fileLocation = "/Users/ming/local/img/";


			// 判断文件数组是否为空
			if (file != null) {
				// 计算一次文件累加不饿能超过50MB
				long fs = 0;
				for (int i = 0; i < file.length; i++) {
					fs += file[i].getSize();// 文件大小累加
				}
				System.out.println(fs);
				// 判断文件累加不能超过50MB
				if (fs < fileSize) {
					for (int i = 0; i < file.length; i++) {

						try {
							// 如果目录不存在则创建
							File uploadDir = new File(fileLocation);
							if (!uploadDir.exists()) {
								uploadDir.mkdir();
							}
							//获取源文件名称
							String fileName = file[i].getOriginalFilename();
							fileFinishName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
							//上传文件到指定目录下
							File uploadFile = new File(uploadDir + uploadDir.separator + fileFinishName);
							file[i].transferTo(uploadFile);
							if(file.length > 1){
								listFile += path + fileFinishName +";";
							}else{
								listFile = path + fileFinishName;
							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}

					}
				} else {
					// request.setAttribute("fileError", "上传文件总数大于50MB");
				}
			}
			return listFile;
	}

}
