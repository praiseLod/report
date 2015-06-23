package com.fdauto.report;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 总的报表引擎。
 * @{code T}为引擎需要处理的接口类型，{@code C}为引擎需要处理的模板类型
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public interface ReportEngine<T,C> {

	/**
	 * 导入内容
	 * @param context void
	 */
	void setContext(C context);
	/**
	 * 导入模板
	 * @param context void
	 */
	T setTemplate(String path);
	
	/**
	 * 使用输入流导入模板
	 * @param stream
	 * @return T
	 */
	T setTemplate(InputStream stream);
	
	/**
	 * 输出文档
	 * @param outputStream void
	 */
	void save(OutputStream stream);
	
	/**
	 * 清除模板与内容设置
	 * void
	 */
	void clear();
	
}
