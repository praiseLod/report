package com.fdauto.report.word.impl;

import java.io.InputStream;

import com.aspose.words.Document;
import com.fdauto.report.ReportTemplate;
import com.fdauto.report.exception.ReportException;

/**
 * aspose文档模板
 * <p>aspose可支持的文档类型为<em> doc , docx , xhtml , html , txt , mhtml , xml , odt , ooxml , rif </em>
 * 
 * @author PraiseLod
 * @date 2015年6月9日
 * @version 
 */
public class AsposeWordTemplate implements ReportTemplate {

	private String templatePath;        //模板路径
	private InputStream inputStream;	//模板来源
	
	public AsposeWordTemplate() {
		this("");
	}

	public AsposeWordTemplate(String templatePath) {
		super();
		this.templatePath = templatePath;
	}

	public AsposeWordTemplate(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

	@Override
	public void setSource(InputStream inputStream) {
		
	}

	@Override
	public String getReportPath() {
		return this.templatePath;
	}

	@Override
	public void setSource(String path) {
		this.templatePath = path;
	}

	@Override
	public InputStream getReprotSource() {
		return this.inputStream;
	}

	/**
	 * 创建一个aspose文档对象。
	 * <P>创建文档时加载模板资源的顺序为：
	 * <li>ReportTemplate.getReportPath()
	 * <li>ReportTemplate.getReprotSource()
	 * </p>
	 * 
	 * @param template
	 * @return Document
	 */
	@Override
	public Document createDocument() {
		Document document = null;
		try {
			if(getReportPath()==null||getReportPath().trim().isEmpty()){
				if(getReprotSource()==null)
					throw new ReportException("未指定一个文档模板");
				document = new Document(getReprotSource());
			}else{
				document = new Document(getReportPath());
			}
		} catch (Exception e) {
			throw new ReportException("文档对象创建失败",e);
		}
		return document;
	}

}
