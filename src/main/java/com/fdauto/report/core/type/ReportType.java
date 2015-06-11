package com.fdauto.report.core.type;

public enum ReportType {
	UNKNOWN(0),
	DOC(10),
	DOCX(20),
	DOT(11),
	DOTX(22),
	DOCM(21),
	DOTM(23),
	HTML(50),
	MHTML(51),
	HTML_FIXED(45),
	SWF(43),
	PDF(40),
	PS(47),
	JPEG(104),
	BMP(102),
	PNG(101),
	TEXT(70),
	EPUB(52),
	ODT(60),
	OTT(61),
	TIFF(100),
	FLAT_OPC(24),
	FLAT_OPC_MACRO_ENABLED(25),
	FLAT_OPC_TEMPLATE(26),
	FLAT_OPC_TEMPLATE_MACRO_ENABLED(27),
	RTF(30),
	WORD_ML(31),
	XPS(41),
	OPEN_XPS(46),
	XAML_FIXED(42),
	SVG(44);
	
	private  int value;

	private ReportType(Integer value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	@Override
	public String toString(){
		if (this.value==0) return "";		//unknow
		
		return this.name().toLowerCase();
	}
}
