package com.fdauto.report;

import java.io.InputStream;

/**
 * 总的报表模板接口
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public interface ReportTemplate {
	/**
	 * 返回模板所在的路径
	 * @return String
	 */
	String getTemplatePath();
	/**
	 * 设置模板路径
	 * @param path void
	 */
	void setSource(String path);
	
	/**
	 * 通过{@code InputStream} 来设置模板
	 * @param inputStream void
	 */
	void setSource(InputStream Stream);
	
	/**
	 * 以二进制流形式取得模板
	 * @return InputStream
	 */
	InputStream getReprotSource();
}
