package com.fdauto.report.text.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.fdauto.report.exception.ReportException;
import com.fdauto.report.text.TextEngine;
import com.fdauto.report.text.TextTemplate;
import com.fdauto.report.util.ReportUitl;

/**
 * 基于velocity的文本模板工作引擎
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public class VelocityTextEngine implements TextEngine {

	private static final String velocityContent = "velocity.properties";
	
	private VelocityContext context;
	private VelocityEngine engine;
	private TextTemplate template;
	
	public VelocityTextEngine() {
		this(velocityContent);
	}

	/**
	 * 引擎自动从类路径下读取.properties的配置
	 * @param velocityContent
	 */
	public VelocityTextEngine(String velocityContent) {
		super();
		try {
			this.engine = new VelocityEngine();
			Properties properties = new Properties();
			properties.load(ReportUitl.getClassPathResource(velocityContent));
			this.engine.init(properties);
		} catch (Exception e) {
			throw new ReportException(e);
		}
	}

	@Override
	public void save(OutputStream stream) {
		try {
			Writer out = new OutputStreamWriter(stream);
			Reader reader = new InputStreamReader(this.template.getReprotSource(),this.template.getEncoding().toString());
			this.engine.evaluate(this.context, out, new Date().toString(), reader);
		} catch (Exception e) {
			throw new ReportException(e);
		}finally{
			this.template.closeResource();
			this.template =null;
			closeResource(null, stream);
		}
	}
	
	@Override
	public void setContext(VelocityContext context) {
		this.context = context;
	}

	@Override
	public TextTemplate setTemplate(String path) {
		if(this.template == null){
			this.template = new VelocityTemplate();
		}
		this.template.setSource(path);
		return this.template;
	}

	@Override
	public String merge(String template, VelocityContext context) {
		StringWriter out=null;
		try {
			out = new StringWriter();
			this.engine.evaluate(context, out, new Date().toString(), template);
			
			return out.toString();
		} catch (Exception e) {
			throw new ReportException(e);
		} finally{
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
				}
			out=null;
		}
	}

	@Override
	public TextTemplate setTemplate(InputStream stream) {
		if(this.template == null){
			this.template = new VelocityTemplate();
		}
		this.template.setSource(stream);
		return this.template;
	}

	@Override
	public String merge(VelocityContext context) {
		this.context = context;
		return merge();
	}

	@Override
	public String merge() {
		StringWriter out =null;
		try {
			Reader reader = new InputStreamReader(this.template.getReprotSource(),this.template.getEncoding().toString());
			out = new StringWriter();
			this.engine.evaluate(this.context, out, new Date().toString(), reader);
			
			return out.toString();
		} catch (Exception e) {
			throw new ReportException(e);
		} finally{
			this.template.closeResource();
			this.template =null;
			if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
				}
			out=null;
		}
	}

	@Override
	public String merge(TextTemplate template, VelocityContext context) {
		this.context = context;
		this.template = template;
		return merge();
	}
	
	private void closeResource(InputStream in,OutputStream out){
		try {
			if(in!=null)
				in.close();
				in=null;
			if(out!=null)
				out.close();
				out=null;
		} catch (IOException e) {
		}
	}

	@Override
	public InputStream saveTo() {
		String content = merge();
		return new ByteArrayInputStream(content.getBytes());
	}
	
}
