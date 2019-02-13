package xuexiqiangguo;

import java.io.*;

public class ProcessErrorReport {
	ProcessErrorReport(Process p, String c){
		String readL = "";
		System.out.println("[ERROR] "+c);
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
				p.getInputStream()));
		System.out.println("[InputStream]");
		try {while((readL=in.readLine())!=null) System.out.println(readL);}
		catch (IOException e) {
			System.out.println("reading InputStream has error");
		}
		
		readL = "";
		BufferedReader err = new BufferedReader(
				new InputStreamReader(
				p.getErrorStream()));
		System.out.println("[ErrorStream]");
		try {while((readL=err.readLine())!=null) System.out.println(readL);}
		catch (IOException e) {
			System.out.println("reading ErrorStream has error");
		}
	}
}
