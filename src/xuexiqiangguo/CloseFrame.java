package xuexiqiangguo;

import java.awt.event.*;
import java.io.*;

public class CloseFrame extends WindowAdapter{
	public void windowClosing(WindowEvent we) {
		Process kill_adb = null;
		try {kill_adb = Runtime.getRuntime().exec("adb kill-server");}
		catch (IOException e) {
			new ProcessErrorReport(kill_adb, "0x2F");
		}
		try {Thread.sleep(200);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x30");}
		if((new File("temp.png")).exists()){
			Process delete_img = null;
			try {
				if(System.getProperty("os.name").toLowerCase().startsWith("linux"))
					delete_img = Runtime.getRuntime().exec("rm temp.png");
				if(System.getProperty("os.name").toLowerCase().startsWith("win"))
					delete_img = Runtime.getRuntime().exec("del /f /q temp.png");
			}
			catch(IOException e) {
				new ProcessErrorReport(delete_img, "0x31");
			}
		}
		try {Thread.sleep(200);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x32");}
		
		System.exit(0);
	}
}