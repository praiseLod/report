package report.domain.text;

import java.io.ByteArrayInputStream;

import org.apache.velocity.VelocityContext;

import com.fdauto.report.tools.TextTools;

public class TextTest {
	
	public static void main(String[] args) {
		String content = "${name}和。。。";
		VelocityContext context = new VelocityContext();
		context.put("name", "张三");
		String s =TextTools.mergeString(content,context);
		
		TextTools.merge(new ByteArrayInputStream(content.getBytes()), context);
		System.out.println(s);
	}
}
