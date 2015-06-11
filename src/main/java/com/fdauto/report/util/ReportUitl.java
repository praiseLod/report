package com.fdauto.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
	 */
	public static String getClassPath(String fileName){
		String path = null;
		path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
		System.out.println(path);
		if(isAvaliablePath(path))
			return path;
		return null;
	}
	
	
	/**
	 * 在类路径下取得指定的文件，并以二进制流返回
	 * @param fileName
	 * @return
	 */
	public static InputStream getClassPathResource(String fileName){
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}
	
	/**
	 * 从文件路径下取得文件，以二进行流返回
	 * @param fileName
	 * @return
	 * @throws IOException InputStream
	 */
	public static InputStream getFileResource(String fileName) throws IOException{
		FileInputStream stream = FileUtils.openInputStream(new File(fileName));
		return stream;
	}
	
	/**
	 * 先从类路径下查找文件，如果没找到就从文件路径下查找
	 * @param fileName
	 * @return
	 * @throws IOException InputStream
	 */
	public static InputStream fileOrClassPathResource(String fileName) throws IOException{
		InputStream stream = getClassPathResource(fileName);
		if(stream==null)
			stream = getFileResource(fileName);
		return stream;
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
