package com.luan.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class HtmlTransJspUtils {
	//资源上下文路径     "${pageContext.rquest.contextPath}/css"
	private static String  contextPath = "${ctx}";
	public static void main(String[] args) throws Exception {
		//
		File f = new File("E:\\git_repository\\luanQT\\luanQT\\src\\main\\webapp\\WEB-INF\\jsp");
		for(File s : f.listFiles()){
			String str = s.getAbsolutePath();
			if(str.endsWith(".html")){
				writeFile(str);
				fileRename(str);
			}
			
		}
	}
 
	/**
	 * 读取指定文件下的内容，乱码问题已经处理,编码为UTF-8
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String fileName) throws Exception {
		String result = "";
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStreamReader = new InputStreamReader(new FileInputStream(
					fileName), "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					read = read.contains("css/")?read.replace("css/", contextPath+"/css/"):read;
					read = read.contains("js/")?read.replace("js/", contextPath+"/js/"):read;
					read = read.contains("img/")?read.replace("img/", contextPath+"/img/"):read;
					result = result + read + "\r\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
		}
		System.out.println("读取出来的文件内容是：" + "\r\n" + result);
		return result;
 
	}
 
	/**
	 * 文件的写入
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public static void writeFile(String fileName) throws Exception {
		try {
			String oldContent = readFile(fileName);
			FileOutputStream fo = new FileOutputStream(fileName);
			//读取头文件的内容
			String headString ="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n";			
			fo.write(headString.getBytes("utf-8"));
			fo.write(oldContent.getBytes("utf-8"));
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 修改文件的后缀名，即html变成jsp
	 * @param fileName
	 */
	public static void fileRename(String fileName) {
		File oldFile = new File(fileName);
		System.out.println("修改前文件名称是：" + oldFile.getName());
		String rootPath = oldFile.getParent();
		System.out.println("根路径是：" + rootPath);
		File newFile = new File(rootPath , oldFile.getName().replace("html", "jsp"));
		System.out.println("修改后文件名称是：" + newFile.getName());
		if (oldFile.renameTo(newFile)) {
			System.out.println("修改成功!");
		} else {
			System.out.println("修改失败");
		}
	}
 
}