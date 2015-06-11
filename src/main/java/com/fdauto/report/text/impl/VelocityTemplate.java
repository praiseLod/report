package com.fdauto.report.text.impl;

import java.io.InputStream;

import com.fdauto.report.core.type.CharSet;
import com.fdauto.report.impl.AbstractTemplate;
import com.fdauto.report.text.TextTemplate;

/**
 * 
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public class VelocityTemplate extends AbstractTemplate implements TextTemplate {

	private CharSet charSet;

	public VelocityTemplate() {
		super();
	}

	public VelocityTemplate(InputStream templateStream) {
		super(templateStream);
	}

	public VelocityTemplate(String templatePath) {
		super(templatePath);
	}

	@Override
	public void setSource(String path, CharSet charSet) {
		this.templatePath =path;
		this.charSet = charSet;
		
	}

	@Override
	public CharSet getEncoding() {
		return this.charSet;
	}


}
