package report.word;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import report.domain.JobTestimonal;
import report.domain.Salary;

import com.aspose.words.Document;
import com.fdauto.report.ReportEngine.ReportType;
import com.fdauto.report.ReportTemplate;
import com.fdauto.report.word.WordContext;
import com.fdauto.report.word.WordEngine;
import com.fdauto.report.word.custom.mergehandler.ImageMailMergeHandler;
import com.fdauto.report.word.custom.mergehandler.InsertDocumentAtMailMergeBlobHandler;
import com.fdauto.report.word.impl.AsposeWordContext;
import com.fdauto.report.word.impl.AsposeWordEngine;
import com.fdauto.report.word.impl.AsposeWordTemplate;

public class AsposeWordTest {
	
	public static void main(String[] args) throws Exception {
		normal();
		//insertDocument();
	}
	
	/**
	 * overView
	 * @throws IOException 
	 */
	private static void normal() throws IOException {
		//定义模板引擎
		WordEngine engine = new AsposeWordEngine();
		
		//设置内容
		WordContext context = new AsposeWordContext();
		context.put(Data.getParam(), JobTestimonal.class);	//基础对象
		
		context.put("pic", "WordConvert.png");			//图片插入-方式1
		//context.put("pic2", Data.getBytes());         //图片插入-方式2
		
		context.putTableParam("user", Data.getRangeParam(), JobTestimonal.class);//表格
		
		//引入外部文档以 格式 NestDoc_*
		context.put("NestDoc_doc2", "nestDoc.doc");
		
		//处理图片大小  --模板变量域处理器
		context.putMailMergeHandler(new ImageMailMergeHandler("pic",100, 50));
		context.putMailMergeHandler(new InsertDocumentAtMailMergeBlobHandler());
		
		//定义模板
		ReportTemplate template = new AsposeWordTemplate("收入证明.doc");
		
		//整合数据
		engine.merge(context, template);
		
		//输出
		FileOutputStream stream = new FileOutputStream("e://1.doc");
		engine.saveTo(stream,ReportType.DOC);
		
	}
	
	//利用引擎进行文档合并测试
		private static void insertDocument() throws FileNotFoundException{
			WordEngine engine = new AsposeWordEngine();
			engine.setTemplate(new AsposeWordTemplate("收入证明.doc"));
			Document nestDoc = new AsposeWordTemplate("nestDoc.doc").createDocument();
			engine.insertDocument("otherDoc", nestDoc);
			engine.saveTo(new FileOutputStream("e://nest.doc"), ReportType.DOC);
		}
	
	
}
