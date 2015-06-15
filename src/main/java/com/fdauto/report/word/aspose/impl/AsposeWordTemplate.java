package com.fdauto.report.word.aspose.impl;

import java.io.IOException;
import java.io.InputStream;

import com.aspose.words.Document;
import com.fdauto.report.exception.ReportException;
import com.fdauto.report.impl.AbstractTemplate;
import com.fdauto.report.word.aspose.WordTemplate;

/**
 * aspose文档模板
 * <p>aspose可支持的文档类型为<em> doc , docx , xhtml , html , txt , mhtml , xml , odt , ooxml , rif </em>
 * 
 * @author PraiseLod
 * @date 2015年6月9日
 * @version 
 */
public class AsposeWordTemplate extends AbstractTemplate implements WordTemplate {
	
	public AsposeWordTemplate() {
		super();
	}

	public AsposeWordTemplate(String templatePath) {
		super(templatePath);
	}

	public AsposeWordTemplate(InputStream inputStream) {
		super(inputStream);
	}

	/**
	 * 创建一个aspose文档对象。
	 * 
	 * @param template
	 * @return Document
	 */
	@Override
	public Document createDocument() {
		Document document = null;
		try {
			document = new Document(getReprotSource());
		} catch (Exception e) {
			throw new ReportException("文档对象创建失败",e);
		}finally{
			if(this.templateStream==null)
				try {
					this.templateStream.close();
				} catch (IOException e) {
				}
			this.templateStream=null;
		}
		return document;
	}

}
