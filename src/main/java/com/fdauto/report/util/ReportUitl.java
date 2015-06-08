package com.fdauto.report.util;

import java.io.File;
import java.io.InputStream;

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
	 * 在类路径下取得指定的文件
	 * @param fileName
	 * @return
	 */
	public static InputStream getClassPathResource(String fileName){
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
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
