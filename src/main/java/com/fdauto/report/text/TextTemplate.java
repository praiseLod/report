package com.fdauto.report.text;

import com.fdauto.report.ReportTemplate;
import com.fdauto.report.core.type.CharSet;

public interface TextTemplate extends ReportTemplate {
	/**
	 * 设置模板路径
	 * @param path void
	 */
	void setSource(String path,CharSet charSet);
	CharSet getEncoding();
}
