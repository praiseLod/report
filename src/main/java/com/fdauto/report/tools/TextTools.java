package com.fdauto.report.tools;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.velocity.VelocityContext;

import com.fdauto.report.text.TextEngine;
import com.fdauto.report.text.impl.VelocityTextEngine;

/**
 * 文本快捷工具
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public class TextTools {

	private static final TextEngine engine = new VelocityTextEngine();
	
	private TextTools() {
		super();
	}
	
	public static String mergeString(String template,VelocityContext context){
		return engine.merge(template, context);
	}
	
	public static String merge(String path,VelocityContext context){
		engine.setTemplate(path);
		return engine.merge(context);
	}
	
	public static InputStream saveTo(String path,VelocityContext context){
		engine.setTemplate(path);
		engine.setContext(context);
		return engine.ConvertTo();
	}
	
	public static void save(OutputStream stream,String path,VelocityContext context){
		engine.setTemplate(path);
		engine.setContext(context);
		engine.saveTo(stream);
	}
	public static String merge(InputStream stream,VelocityContext context){
		engine.setTemplate(stream);
		engine.setContext(context);
		return engine.merge();
	};
	
	
}
