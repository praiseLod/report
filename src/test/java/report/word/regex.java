package report.word;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class regex {
	
	public static void main(String[] args) throws FileNotFoundException {
		String s = "pic{50,10}";
		Pattern pattern = Pattern.compile("\\{\\d+,\\d+\\}");
		
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		System.out.println(Pattern.matches("NestDoc_.*", "NestDoc_doc2"));
		//System.out.println(ReportType.DOC.toString());
		
		FileOutputStream outputStream = new FileOutputStream("adsf");
	}
}
