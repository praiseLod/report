package report.word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fdauto.report.ReportEngine.ReportType;

public class regex {
	
	public static void main(String[] args) {
		String s = "pic{50,10}";
		Pattern pattern = Pattern.compile("\\{\\d+,\\d+\\}");
		
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		System.out.println(Pattern.matches("NestDoc_.*", "NestDoc_doc2"));
		//System.out.println(ReportType.DOC.toString());
	}
}
