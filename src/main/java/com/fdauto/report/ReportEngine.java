package com.fdauto.report;

import java.io.OutputStream;


/**
 * @author praiseLod
 * @date 2015年6月4日
 *
 *
 */
public interface ReportEngine {
	
	void merge(ReportContext context,ReportTemplate template);
	
	void save(OutputStream outputStream);
	
	void save(OutputStream outputStream,ReportType type);
	
	public enum ReportType{
		DOC(10),
		DOCX(20),
		HTML(50),
		SWF(43),
		PDF(40),
		JPEG(104),
		BMP(102),
		PNG(101),
		TEXT(70);
		private  int value;

		private ReportType(Integer value) {
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}
		
	}
}
