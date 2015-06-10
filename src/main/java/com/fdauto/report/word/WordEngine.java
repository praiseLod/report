package com.fdauto.report.word;

import java.io.OutputStream;

import com.aspose.words.Document;
import com.fdauto.report.ReportContext;
import com.fdauto.report.ReportEngine;
import com.fdauto.report.ReportTemplate;

public interface WordEngine extends ReportEngine {

	/**
	 * 获取模板内容
	 * @return ReportContext
	 */
	WordContext getContext();
	
	/**
	 * 获取模板
	 * @return ReportTemplate
	 */
	ReportTemplate getTemplate();
	
	/**
	 * 获取文档
	 * @return Object
	 */
	Document getDocument();
	
	/**
	 * 引擎生成文档
	 * void
	 */
	Document createDocument();
	
	/**
	 * 设置模板引擎的{@code ReportContext}和{@code ReportTemplate}属性并生成文档
	 * @param context		
	 * @param template 
	 */
	Document merge(ReportContext context,ReportTemplate template);
	
	/**
	 * 根据提供的文档类型输出文档,调用此操作前如果引擎未生成文档将自动根据提供的模板与内容生成文档。
	 * <p>aspose可输出的文档类型为：<pre>doc , docx , pdf , txt , xml , mhtml , html , xhtml 
	 *bmp , png , jpg , swf , rtf </pre>
	 * 
	 * @param outputStream   
	 * @param type           生成的类型
	 */
	void saveTo(OutputStream outputStream, ReportType type);
	
	/**
	 * 向当前文档的指定的书签位置插入一个文档。
	 * <p>在插入之前，如果当前引擎未生成文档，将先根据给定的模板对象生成文档后进行插入操作。
	 * 如果书签没有找到也将不进行<br>插入操作
	 * 
	 * @param bookmarkName   模板书签名
	 * @param document       被插入的对象。如果该对象为{@code null}将不进行插入操作
	 */
	void insertDocument(String bookmarkName,Document nestDoc);
	
}
