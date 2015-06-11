package com.fdauto.report.word.aspose;

import com.aspose.words.Document;
import com.fdauto.report.ReportTemplate;

public  interface WordTemplate extends ReportTemplate {
	/**
	 * 根据模板创建对应的文档对象
	 * @return Object
	 */
	Document createDocument();
}
