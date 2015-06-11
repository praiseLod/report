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

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.fdauto.report.text.TextEngine;
import com.fdauto.report.text.TextTemplate;

/**
 * 基于velocity的文本模板工作引擎
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public class VelocityTextEngine implements TextEngine {

	private VelocityContext context;
	private VelocityEngine engine;
	private TextTemplate template;
	
	public VelocityTextEngine() {
		this(null);
	}
	
	public VelocityTextEngine(String propsFilename) {
		super();
		this.engine =(propsFilename==null?new VelocityEngine():new VelocityEngine(propsFilename));
	}

	@Override
	public void saveTo(OutputStream stream) {
		Writer out = new OutputStreamWriter(stream);
		Reader reader = new InputStreamReader(this.template.getReprotSource());
		this.engine.evaluate(this.context, out, new Date().toString(), reader);
		closeResource(reader, out);
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
		StringWriter out = new StringWriter();
		this.engine.evaluate(context, out, new Date().toString(), template);
		closeResource(null, out);
		return out.toString();
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
		Reader reader = new InputStreamReader(this.template.getReprotSource());
		StringWriter out = new StringWriter();
		this.engine.evaluate(this.context, out, new Date().toString(), reader);
		closeResource(reader, out);
		return out.toString();
	}

	@Override
	public String merge(TextTemplate template, VelocityContext context) {
		this.context = context;
		this.template = template;
		return merge();
	}
	
	private void closeResource(Reader r,Writer w){
		try {
			if(r!=null)
				r.close();
			if(w!=null)
				w.close();
		} catch (IOException e) {
		}
	}

	@Override
	public InputStream ConvertTo() {
		String content = merge();
		return new ByteArrayInputStream(content.getBytes());
	}
	
}
