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

	private String license;  //asposeWord 使用证书
	private Document document;          //asposeWord 文档对象
	private WordTemplate template;    //模板类
	private WordContext context;        //内容类
	
	/**
	 * 初始证书路径
	 * @param license
	 */
	public AsposeWordEngine() {
		this("aspose_license.xml");
	}
	
	public AsposeWordEngine(String license) {
		super();
		this.license = license;
		showLicense(ReportUitl.getClassPathResource(this.license)); // 展示证书，可使用aspose所有功能
	}
	
	public AsposeWordEngine(WordTemplate template) {
		this();
		this.template = template;
	}

	public AsposeWordEngine(Document document) {
		this();
		this.document = document;
	}

	@Override
	public Document merge(WordContext context, WordTemplate template) {
		this.context = context;
		this.template = template;
		
		try {
			this.document = (Document) template.createDocument();
			
			if(this.context==null)return this.document;
			
			WordContext wordContext = (WordContext) context;
			//添加合并时模板变量域处理器
			this.document.getMailMerge().setFieldMergingCallback(wordContext.getMailMergeHandler());
			
			MailMerge merge = document.getMailMerge();
			String[] namefiled = context.getNames().toArray(new String[] {});
			Object[] valuefiled = context.getValues().toArray(new Object[] {});
			log.info("\n模板变量值:{}\n模板变量名:{} ", Arrays.toString(namefiled),
					Arrays.toString(valuefiled));
			
			merge.execute(namefiled, valuefiled);// 基本参数
			// 表格参数
			if (!wordContext.getTableParam().isEmpty()) {
				for (String paramName : wordContext.getTableParam().keySet()) {
					List<Map<String, Object>> tableParam = wordContext
							.getTableParam().get(paramName);
					log.info("\n表格变量为 {} ", tableParam);
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
	public void saveTo(OutputStream outputStream) {
		saveTo(outputStream, ReportType.DOC);
	}

	@Override
	public void saveTo(OutputStream outputStream, ReportType type) {
		if (this.document == null) this.document = createDocument();
		try {
			document.save(outputStream, type.getValue());
		} catch (Exception e) {
			throw new ReportException(e);
		}
	}

	
	/**
	 * asposeWord注册
	 * @param is
	 */
	private void showLicense(InputStream is) {
		try {
			License aposeLic = new License();
			aposeLic.setLicense(is);
		} catch (Exception e) {
			System.err.println("你没有合法的aspose使用权限，你需要为引擎提供aspose的使用证书!!!!");
			System.err.println("当前证书的路径是： " + this.license);
			log.warn("你没有合法的aspose使用权限，你需要为引擎提供aspose的使用证书");
		}
	}

	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) throws FileNotFoundException {
		this.license = license;
		showLicense(new FileInputStream(new File(license)));
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
	public WordTemplate getTemplate() {
		return template;
	}

	@Override
	public void setTemplate(WordTemplate template){
		this.template = template;
	}

	@Override
	public WordContext getContext() {
		return context;
	}

	@Override
	public void setContext(WordContext context) {
		this.context =  context;
	}

}
