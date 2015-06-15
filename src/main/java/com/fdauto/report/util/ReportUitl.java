package com.fdauto.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

/**
 * @author praiseLod
 * @date 2015年6月6日
 * @version
 * 
 */
public class ReportUitl {
	
	/**
	 * 为指定的文件获在在类路径下的绝对路径
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static String getClassPath(String fileName) throws FileNotFoundException{
		String path = null;
		path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath().substring(1);
		if(isAvaliablePath(path))
			return path;
		 throw new FileNotFoundException(path);
	}
	
	
	/**
	 * 在类路径下取得指定的文件，并以二进制流返回
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static InputStream getClassPathResource(String fileName) throws FileNotFoundException{
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if(stream ==null)
			 throw new FileNotFoundException("类路径下没有名为 "+fileName+" 的文件");
		return stream;
	}
	
	/**
	 * 从文件路径下取得文件，以二进行流返回
	 * @param fileName
	 * @return
	 */
	public static InputStream getFileResource(String path) throws IOException{
		FileInputStream stream = FileUtils.openInputStream(new File(path));
		if(stream ==null)
			 throw new FileNotFoundException(path);
		return stream;
	}
	
	/**
	 * 先从类路径下查找文件，如果没找到就从文件路径下查找
	 * @param fileName
	 * @return
	 * @throws IOException InputStream
	 */
	public static InputStream fileOrClassPathResource(String fileName) throws IOException{
		InputStream stream;
		try {
			stream = getClassPathResource(fileName);
		} catch (IOException e) {
			stream = getFileResource(fileName);
		}
		return stream;
	}
	
	public static String getRealPath(HttpServletRequest request,String fileName) throws FileNotFoundException{
		HttpSession session = request.getSession();
		String path = session.getServletContext().getRealPath(fileName);
		if(isAvaliablePath(path))
			return path;
		throw new FileNotFoundException(path);
	}
	
	/**
	 * 检查{@code path} 是否为一个可用的资源文件
	 * @param path
	 * @return
	 */
	public static boolean isAvaliablePath(String path){
		File file = new File(path);
		return file.exists();
	}
}
