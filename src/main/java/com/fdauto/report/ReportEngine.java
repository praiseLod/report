package com.fdauto.report;

import java.io.OutputStream;


/**
 * @author praiseLod
 * @date 2015年6月4日
 *
 */
public interface ReportEngine {
	
	/**
	 * 引擎生成文档，完成模板与内容的整合
	 * @param context
	 * @param template void
	 */
	void merge(ReportContext context,ReportTemplate template);
	
	/**
	 * 输出文档，调用此操作前要先让引擎生成文档才能完成文档的输出。
	 * @param outputStream void
	 */
	void save(OutputStream outputStream);
	
	/**
	 * 文档类型
	 * 
	 * @author PraiseLod
	 * @date 2015年6月9日
	 * @version 
	 */
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
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
		
	}
}
