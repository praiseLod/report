package com.fdauto.report.impl;

import java.io.IOException;
import java.io.InputStream;

import com.fdauto.report.ReportTemplate;
import com.fdauto.report.exception.ReportException;
import com.fdauto.report.util.ReportUitl;

/**
 * 统一模板抽象类
 * 
 * @author PraiseLod
 * @date 2015年6月11日
 * @version 
 */
public class AbstractTemplate implements ReportTemplate {
	
	protected String templatePath;
	protected InputStream templateStream;
	
	public AbstractTemplate() {
		super();
	}

	public AbstractTemplate(String templatePath) {
		super();
		this.templatePath = templatePath;
	}

	public AbstractTemplate(InputStream templateStream) {
		super();
		this.templateStream = templateStream;
	}

	@Override
	public String getTemplatePath() {
		return templatePath;
	}

	@Override
	public void setSource(String path) {
		this.templatePath = path;
	}

	@Override
	public void setSource(InputStream inputStream) {
		this.templateStream = inputStream;
	}

	@Override
	public InputStream getReprotSource() {
		if(templatePath == null&& templateStream==null)
			throw new ReportException("无法获取模板，原因：模板状态为空");
		
		try {
			if(this.templateStream==null)
				this.templateStream = ReportUitl.fileOrClassPathResource(templatePath);
		} catch (IOException e) {
			throw new ReportException("无法获取模板，原因：无效的模板路径【"+templatePath+"】");
		}
		
		return this.templateStream;
	}

}
