package com.fdauto.report.util;

import java.io.InputStream;

import com.aspose.words.License;

public class AsposeUtil {
	
	/**
	 * aspose许可证
	 */
	public static final String LICENCE = "aspose_license.xml";
	
	private AsposeUtil() {
		super();
	}
	
	public static void  AsposeLicence(String file){
		try {
			InputStream is = ReportUitl.getClassPathResource(file==null?LICENCE:file);
			License aposeLic = new License();
			aposeLic.setLicense(is);
		} catch (Exception e) {
			System.err.println("你没有合法的aspose使用权限，你需要为引擎提供aspose的使用证书!!!!");
			System.err.println("当前证书的路径是： " + file);
		}
	}
}
