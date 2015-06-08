package com.fdauto.report.base;

import com.fdauto.report.ReportTemplate;

public class DefaultReportTemplate implements ReportTemplate {

	protected String templatePath;
	
	
	public DefaultReportTemplate() {
		this(null);
	}

	public DefaultReportTemplate(String templatePath) {
		super();
		this.templatePath = templatePath;
	}

	@Override
	public String getPath() {
		return this.templatePath;
	}

	@Override
	public void setPath(String path) {
		this.templatePath = path;
	}
	
}
