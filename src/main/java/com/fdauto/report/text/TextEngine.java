package com.fdauto.report.text;

import java.io.InputStream;

import org.apache.velocity.VelocityContext;

import com.fdauto.report.ReportEngine;

/**
 * 文本引擎
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public interface TextEngine extends ReportEngine<TextTemplate, VelocityContext> {
	/**
	 * 将给定的字符串作为模板，合并内容后并输出
	 * @param template
	 * @param context
	 * @return String
	 */
	String merge(String template,VelocityContext context);
	/**
	 * 将给定的内容与模板合并，并输出
	 * @param context
	 * @return String
	 */
	String merge(VelocityContext context);
	/**
	 * 合并已给定的模板与内容，并输出
	 * @return String
	 */
	String merge();
	/**
	 * 设置模板与内容。并输出文档
	 * @param template
	 * @param context
	 * @return String
	 */
	String merge(TextTemplate template,VelocityContext context);
	/**
	 * 将最后生成文档保存为二进行流
	 * @return InputStream
	 */
	InputStream saveTo();
}
