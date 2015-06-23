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
		String result =engine.merge(context);
		engine.clear();
		return result;
	}
	
	public static InputStream saveTo(String path,VelocityContext context){
		engine.setTemplate(path);
		engine.setContext(context);
		InputStream stream = engine.saveTo();
		engine.clear();
		return stream;
	}
	
	public static void save(OutputStream stream,String path,VelocityContext context){
		engine.setTemplate(path);
		engine.setContext(context);
		engine.save(stream);
		engine.clear();
	}
	public static String merge(InputStream stream,VelocityContext context){
		engine.setTemplate(stream);
		engine.setContext(context);
		String result =engine.merge();
		engine.clear();
		return result;
	};
	
	
}
