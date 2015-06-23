package report.word;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class regex {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println(new File("template/收入证明.doc").exists());
	}
}
