package com.fdauto.report.word.custom.mergehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.DocumentBuilder;
import com.aspose.words.FieldMergingArgs;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.ImageFieldMergingArgs;
import com.aspose.words.Shape;

/**
 * 模板图片域处理器
 * 
 * @author PraiseLod
 * @date 2015年6月9日
 * @version
 */
public class ImageMailMergeHandler implements IFieldMergingCallback {
	private static final Logger log = LoggerFactory
			.getLogger(ImageMailMergeHandler.class);
	
	private double top;       // 图片的头部坐标位置
	private double left;      // 图片左部坐标位置
	private double height;    // 图片高度
	private double width;     // 图片宽度
	private String paramName; //模板中图片域的变量名

	public ImageMailMergeHandler(String paramName, double height, double width) {
		this(paramName, height, width, 0, 0);
	}

	public ImageMailMergeHandler(String paramName, double height, double width,
			double top, double left) {
		super();
		this.paramName = paramName;
		this.height = height;
		this.width = width;
		this.top = top;
		this.left = left;
	}

	@Override
	public void fieldMerging(FieldMergingArgs arg0) throws Exception {
		
	}

	@Override
	public void imageFieldMerging(ImageFieldMergingArgs arg0) throws Exception {
		if (arg0.getDocumentFieldName().equals(paramName)) {
			// 使用DocumentBuilder处理图片的大小
			DocumentBuilder builder = new DocumentBuilder(arg0.getDocument());
			builder.moveToMergeField(arg0.getFieldName());

			Shape shape = builder.insertImage(arg0.getFieldValue().toString());

			// 设置x,y坐标
			shape.setLeft(left);
			shape.setTop(top);
			// 设置高宽.
			shape.setWidth(width);
			shape.setHeight(height);
		}
	}

}
