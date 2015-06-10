package com.fdauto.report.word.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.Bookmark;
import com.aspose.words.CompositeNode;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.License;
import com.aspose.words.MailMerge;
import com.aspose.words.Node;
import com.aspose.words.NodeImporter;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.Section;
import com.fdauto.report.ReportContext;
import com.fdauto.report.ReportTemplate;
import com.fdauto.report.exception.ReportException;
import com.fdauto.report.util.ReportUitl;
import com.fdauto.report.word.WordContext;
import com.fdauto.report.word.WordEngine;
import com.fdauto.report.word.custom.MapMailMergeDataSource;

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
	private ReportTemplate template;    //模板类
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
	
	public AsposeWordEngine(ReportTemplate template) {
		this();
		this.template = template;
	}

	public AsposeWordEngine(Document document) {
		this();
		this.document = document;
	}

	@Override
	public Document merge(ReportContext context, ReportTemplate template) {
		this.context = (WordContext) context;
		this.template = template;
		
		try {
			this.document = (Document) template.createDocument();
			
			if(this.context==null)return this.document;
			
			WordContext wordContext = (WordContext) context;
			//添加模板变量处理器
			this.document.getMailMerge().setFieldMergingCallback(wordContext.getParamHandler());
			
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
	 * @param is void
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
			insertDocument(bookmark.getBookmarkStart().getParentNode(),nestDoc);
		} catch (Exception e) {
			throw new ReportException(e);
		}
	}
	
	@Override
	public Document createDocument() {
		return merge(this.context, this.template);
	}
	
	//文档合并实现
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void insertDocument(Node insertAfterNode, Document srcDoc) throws Exception
	{
	    // 确保节点是一个段落或是表格
	    if ((insertAfterNode.getNodeType() != NodeType.PARAGRAPH) &
	      (insertAfterNode.getNodeType() != NodeType.TABLE))
	        throw new IllegalArgumentException("The destination node should be either a paragraph or table.");

	    // 将插入以目标段落的父容器中
	    CompositeNode dstStory = insertAfterNode.getParentNode();

	    // 为导入对象添加风格
	    NodeImporter importer = new NodeImporter(srcDoc, insertAfterNode.getDocument(), ImportFormatMode.KEEP_SOURCE_FORMATTING);

	    // 遍历源文档中的所有部分。
	    for (Section srcSection : srcDoc.getSections())
	    {
	        //遍历所有的块级别节点(段落和表)的主体部分。
	        for (Node srcNode : ((Iterable<Node>) srcSection.getBody()))
	        {
	            // 跳过节点如果是最后一个空段部分。
	            if (srcNode.getNodeType() == (NodeType.PARAGRAPH))
	            {
	                Paragraph para = (Paragraph)srcNode;
	                if (para.isEndOfSection() && !para.hasChildNodes())
	                    continue;
	            }

	            // 这将创建一个克隆节点的,插入到目标文档。
	            Node newNode = importer.importNode(srcNode, true);

	            // 在引用的节点后插入新了节点
	            dstStory.insertAfter(newNode, insertAfterNode);
	            insertAfterNode = newNode;
	        }
	    }
	}

	public ReportTemplate getTemplate() {
		return template;
	}

	public void setTeplate(ReportTemplate template){
		this.template = template;
	}

	public WordContext getContext() {
		return context;
	}

	@Override
	public void setContext(ReportContext context) {
		this.context = (WordContext) context;
	}

}
