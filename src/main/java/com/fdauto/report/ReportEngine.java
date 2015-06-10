package com.fdauto.report;

import java.io.OutputStream;


/**
 * @author praiseLod
 * @date 2015年6月4日
 *
 */
public interface ReportEngine {
	
	/**
	 * 引擎生成文档
	 * void
	 */
	Object createDocument();
	
	/**
	 * 设置模板引擎的{@code ReportContext}和{@code ReportTemplate}属性并生成文档
	 * @param context		
	 * @param template 
	 */
	Object merge(ReportContext context,ReportTemplate template);
	
	/**
	 * 输出文档.
	 * @param outputStream void
	 */
	void saveTo(OutputStream outputStream);
	
	/**
	 * 设置引擎模板
	 * @param template void
	 */
	void setTeplate(ReportTemplate template);
	
	/**
	 * 调引擎内容
	 * @param context void
	 */
	void setContext(ReportContext context);
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
