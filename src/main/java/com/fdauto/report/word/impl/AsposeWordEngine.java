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

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.MailMerge;
import com.fdauto.report.ReportContext;
import com.fdauto.report.ReportTemplate;
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

	private static final Logger log = LoggerFactory
			.getLogger(AsposeWordEngine.class);

	private Document document; //asposeWord 文档对象
	private String license;    //asposeWord 使用证书

	public AsposeWordEngine() {
		this(null);
	}

	public AsposeWordEngine(String license) {
		super();
		showLicense(ReportUitl.getClassPathResource("aspose_license.xml")); // 展示证书，可使用aspose所有功能
	}

	@Override
	public void merge(ReportContext context, ReportTemplate template) {
		try {
			if (!(context instanceof WordContext)) {
				if (context == null) {
					this.document = (Document) template.createDocument();
					return;
				}

				throw new InvalidParameterException(
						"WordEngine模板引擎的ReportContext只能是 WordContext 类型");
			}
			WordContext wordContext = (WordContext) context;

			this.document = (Document) template.createDocument();
			this.document.getMailMerge().setFieldMergingCallback(wordContext.getParamHandler());
			
			MailMerge merge = document.getMailMerge();

			String[] namefiled = context.getNames().toArray(new String[] {});
			Object[] valuefiled = context.getValues().toArray(new Object[] {});
			log.info("\n模板变量值:{}\n模板变量名:{} ", Arrays.toString(namefiled),
					Arrays.toString(valuefiled));
			
			// 基本参数
			merge.execute(namefiled, valuefiled);
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
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//默认生成的模式为.doc
	@Override
	public void save(OutputStream outputStream) {
		save(outputStream, ReportType.DOC);
	}

	@Override
	public void save(OutputStream outputStream, ReportType type) {
		if (this.document == null) {
			throw new IllegalStateException("wordEngine Document is null");
		}
		try {
			document.save(outputStream, type.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void InsertDocument(String bookmarkName, Document document) {
		// TODO Auto-generated method stub
		
	}

}
