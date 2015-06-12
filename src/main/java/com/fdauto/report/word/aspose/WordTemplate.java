package com.fdauto.report.word.aspose;

import com.aspose.words.Document;
import com.fdauto.report.ReportTemplate;

/**
 * 基于aspose word的word模板
 * 
 * @author PraiseLod
 * @date 2015年6月12日
 * @version 
 */
public  interface WordTemplate extends ReportTemplate {
	/**
	 * 根据模板创建对应的文档对象
	 * @return Object
	 */
	Document createDocument();
}
