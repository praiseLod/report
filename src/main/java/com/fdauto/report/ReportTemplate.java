package com.fdauto.report;

import java.io.InputStream;

/**
 * 报表模板
 * 
 * @author PraiseLod
 * @date 2015年6月9日
 * @version 
 */
public interface ReportTemplate {

	/**
	 * 返回模板所在的路径
	 * @return String
	 */
	String getReportPath();
	/**
	 * 设置模板路径
	 * @param path void
	 */
	void setSource(String path);
	
	/**
	 * 通过{@code InputStream} 来设置模板
	 * @param inputStream void
	 */
	void setSource(InputStream inputStream);
	
	/**
	 * 取得模板，以二进制流返回
	 * @return InputStream
	 */
	InputStream getReprotSource();
	/**
	 * 根据模板创建对应的文档对象
	 * @return Object
	 */
	Object createDocument();
}
