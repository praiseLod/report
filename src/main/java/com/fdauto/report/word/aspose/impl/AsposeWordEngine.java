package com.fdauto.report.word.aspose.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.Bookmark;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.License;
import com.aspose.words.MailMerge;
import com.fdauto.report.core.type.ReportType;
import com.fdauto.report.exception.ReportException;
import com.fdauto.report.util.AsposeUtil;
import com.fdauto.report.util.ReportUitl;
import com.fdauto.report.word.aspose.WordContext;
import com.fdauto.report.word.aspose.WordEngine;
import com.fdauto.report.word.aspose.WordTemplate;
import com.fdauto.report.word.aspose.custom.MapMailMergeDataSource;
import com.fdauto.report.word.aspose.util.AsposeWordUitl;

/**
 *  基于apsoeWord模板工作引擎
 * @author PraiseLod
 * @date 2015年6月6日
 * @version
 * 
 */
public class AsposeWordEngine implements WordEngine {

	private static final Logger log = LoggerFactory.getLogger(AsposeWordEngine.class);

	private String license; 			//asposeWord 使用证书
	private Document document;          //asposeWord 文档对象
	private WordContext context;        //内容类
	private WordTemplate template;      //模板变量
	/**
	 * 初始证书路径
	 * @param license
	 */
	public AsposeWordEngine() {
		AsposeUtil.AsposeLicence(null);
	}
	
	public AsposeWordEngine(Document document) {
		this();
		this.document = document;
	}

	@Override
	public Document merge(WordContext context) {
		this.context = context;
		try {
			this.document = template.createDocument();
			
			if(this.context==null)return this.document;
			
			WordContext wordContext = context;
			//添加合并时模板变量域处理器
			this.document.getMailMerge().setFieldMergingCallback(wordContext.getMailMergeHandler());
			
			MailMerge merge = document.getMailMerge();
			String[] namefiled = context.getNames().toArray(new String[] {});
			Object[] valuefiled = context.getValues().toArray(new Object[] {});
			log.info("\n模板变量值:{}\n模板变量名:{} ", Arrays.toString(namefiled),
					Arrays.toString(valuefiled));
			// 设置基本参数
			merge.execute(namefiled, valuefiled);
			
			if (!wordContext.getTableParam().isEmpty()) {
				for (String paramName : wordContext.getTableParam().keySet()) {
					List<Map<String, Object>> tableParam = wordContext
							.getTableParam().get(paramName);
					log.info("\n表格变量为 {} ", tableParam);
					// 设置表格参数
					merge.executeWithRegions(new MapMailMergeDataSource(
							paramName, tableParam));
				}
			}
			return this.document;
		} catch (Exception e) {
			throw new ReportException(e);
		}
	}

	//默认生成的模式为.doc
	@Override
	public void save(OutputStream outputStream) {
		save(outputStream, ReportType.DOC);
	}

	@Override
	public void save(OutputStream outputStream, ReportType type) {
		if (this.document == null) this.document = createDocument();
		try {
			document.save(outputStream, type.getValue());
		} catch (Exception e) {
			throw new ReportException(e);
		}finally{
		}
	}
	
	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) throws FileNotFoundException {
		this.license = license;
		AsposeUtil.AsposeLicence(license);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Override
	public void insertDocument(String bookmarkName, Document nestDoc) {
		if(nestDoc==null) return;
		if(this.document==null) this.document = createDocument();
		
		try {
			Bookmark bookmark = this.document.getRange().getBookmarks().get(bookmarkName);
			if(bookmark == null){
				log.warn("{}未嵌入模板中，因为在模板中未找到名为{}书签，请再确认操作",nestDoc.getOriginalFileName(),bookmarkName);
				return;
			}
			
			bookmark.setText("");	//将标签内容置空
			DocumentBuilder builder = new DocumentBuilder(this.document);
			builder.moveToBookmark(bookmarkName); //移至标签处
			AsposeWordUitl.insertDocument(bookmark.getBookmarkStart().getParentNode(),nestDoc);
		} catch (Exception e) {
			throw new ReportException(e);
		}
	}
	
	@Override
	public Document createDocument() {
		return merge(this.context, this.template);
	}

	@Override
	public void setContext(WordContext context) {
		this.context =  context;
	}

	@Override
	public WordTemplate setTemplate(String path) {
		if(this.template==null)
			this.template = new AsposeWordTemplate();
		this.template.setSource(path);
		return this.template;
	}

	@Override
	public WordTemplate setTemplate(InputStream stream) {
		if(this.template==null)
			this.template = new AsposeWordTemplate();
		this.template.setSource(stream);
		return this.template;
	}

	@Override
	public Document merge(WordContext context, WordTemplate template) {
		this.template= template;
		return merge(context);
	}

	@Override
	public void setTemplate(WordTemplate template) {
		this.template = template;
	}

	@Override
	public void clear() {
		this.template=null;
		this.document=null;
		this.context = null;
	}
	
	
}
