package com.fdauto.report.word.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;










import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.MailMerge;
import com.aspose.words.SaveFormat;
import com.fdauto.report.ReportContext;
import com.fdauto.report.ReportEngine;
import com.fdauto.report.ReportTemplate;
import com.fdauto.report.custom.MapMailMergeDataSource;
import com.fdauto.report.util.ReportUitl;
import com.fdauto.report.word.WordContext;


/**
 * @author praiseLod
 * @date 2015年6月6日
 * @version
 * 
 * 基于apsoe word的word模板工作引擎
 */
public class AsposeWordEngine implements ReportEngine {
	
	private static final Logger log = LoggerFactory.getLogger(AsposeWordEngine.class);
	
	private Document document;
	private String license;
	
	
	public AsposeWordEngine() {
		this(null);
	}

	public AsposeWordEngine(String license) {
		super();
		showLicense(ReportUitl.getClassPathResource("aspose_license.xml"));  //展示证书，可使用aspose所有功能
	}

	@Override
	public void merge(ReportContext context, ReportTemplate template) {
		if(!(context instanceof WordContext)){
			throw new InvalidParameterException("参数 【context】 不是 WordContext 类型");
		}
		WordContext wordContext = (WordContext) context;
		try {
			this.document = new Document(template.getPath());
			MailMerge merge = document.getMailMerge();
			
			String[] namefiled = context.getNames().toArray(new String[]{});
			Object[] valuefiled = context.getValues().toArray(new Object[]{});
			log.debug("基本参数值: "+Arrays.toString(namefiled));
			log.debug("基本参数名: "+Arrays.toString(valuefiled));
			//基本参数
			merge.execute(namefiled, valuefiled);
			//循环参数s
			if(!wordContext.getRangeParam().isEmpty()){
				for(String paramName:wordContext.getRangeParam().keySet()){
					merge.executeWithRegions(new MapMailMergeDataSource(paramName, wordContext.getRangeParam().get(paramName)));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save(OutputStream outputStream) {
		save(outputStream,ReportType.DOC);
	}
	
	@Override
	public void save(OutputStream outputStream, ReportType type) {
		if(this.document==null){
			throw new IllegalStateException("wordEngine Document is null");
		}
		try {
			document.save(outputStream, type.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void  showLicense(InputStream is){
		 try {
			 License aposeLic = new License();
			 aposeLic.setLicense(is);
		} catch (Exception e) {
			System.err.println("你没有合法的aspose使用权限，你需要为引擎提供aspose的使用证书!!!!");
			System.err.println("当前证书的路径是： "+this.license);
			log.warn("你没有合法的aspose使用权限，你需要为引擎提供aspose的使用证书");
		}
	}
	
	 public  String getLicense() {
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

}