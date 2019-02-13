package xuexiqiangguo;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Running implements Runnable{
	String tgt;
	int pnt, pnt_own, w, h;
	JTextArea sh;
	Thread run_thread;
	Running(String target, int point, JTextArea shower, int scrm_w, int scrm_h) {
		tgt = target;
		pnt = point;
		pnt_own = 0;
		w = scrm_w;
		h = scrm_h;
		sh = shower;
		run_thread = new Thread(this);
		run_thread.start();
	}

	public void run() {
		sh.append("[开始]\n");
		if(pnt-pnt_own!=0) {
			restart_app();
			pnt_own++;
			sh.append("登陆 "+pnt_own+"/"+pnt+"\n");
		}
		if(pnt-pnt_own!=0) reading(pnt-pnt_own>6 ? 6 : pnt-pnt_own);
		if(pnt-pnt_own!=0) watching(pnt-pnt_own>6 ? 6 : pnt-pnt_own);
		if(pnt-pnt_own!=0) {
			collecting(2);
			pnt_own++;
			sh.append("收藏文章 "+pnt_own+"/"+pnt+"\n");
		}
		if(pnt-pnt_own!=0) sharing(pnt-pnt_own>3 ? 3 : pnt-pnt_own);
			sh.append("[结束]\n");
	}
	
	void back() {
		Process bk = null;
		try {
			bk = Runtime.getRuntime().exec("adb -s "+tgt+" shell input keyevent 4");
			}
		catch (IOException e) {
			new ProcessErrorReport(bk, "0x05");
		}
	}
	
	void double_tap(int x, int y) {
		Process first_tap = null, second_tap = null;
		try {first_tap = Runtime.getRuntime()
				.exec("adb -s "+tgt+" shell input tap "+x+" "+y);}
		catch (IOException e) {
			new ProcessErrorReport(first_tap, "0x06");
		}
		try {Thread.sleep(200);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x07");}
		try {
			second_tap = Runtime.getRuntime()
					.exec("adb -s "+tgt+" shell input tap "+x+" "+y);
		} catch (IOException e) {
			new ProcessErrorReport(second_tap, "0x08");
		}
	}
	
	void tap(int x, int y) {
		Process tp = null;
		try {
			tp = Runtime.getRuntime()
					.exec("adb -s "+tgt+" shell input tap "+x+" "+y);
		}
		catch (IOException e) {new ProcessErrorReport(tp, "0x09");}
	}
	
	void swipe(int x1, int y1, int x2, int y2, int tm) {
		Process sw = null;
		try {
			sw = Runtime.getRuntime()
			.exec("adb -s "+tgt+" shell input swipe "+x1+" "+y1+" "+x2+" "+y2+" "+tm);
		}
		catch (IOException e) {new ProcessErrorReport(sw, "0x0A");}
	}
	
	void get_screen() {
		Process sc = null, pl = null, rm = null;
		try {
			sc = Runtime.getRuntime()
				.exec("adb -s "+tgt+" shell screencap -p /sdcard/temp.png");
		} catch (IOException e) {new ProcessErrorReport(sc, "0x0B");}
		try {Thread.sleep(2000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x0C");}
		try {
			pl = Runtime.getRuntime()
				.exec("adb -s "+tgt+" pull /sdcard/temp.png temp.png");
		} catch (IOException e) {new ProcessErrorReport(pl, "0x0D");}
		try {Thread.sleep(2000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x0E");}
		try {
			rm = Runtime.getRuntime()
				.exec("adb -s "+tgt+" shell rm /sdcard/temp.png");
		} catch (IOException e) {new ProcessErrorReport(rm, "0x0F");}
		try {Thread.sleep(1000);
		}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x10");}
	}
	
	void restart_app() {
		Process cl = null, st = null;
		try {
			cl = Runtime.getRuntime()
				.exec("adb -s "+tgt+" shell am force-stop cn.xuexi.android");
		} catch (IOException e) {new ProcessErrorReport(cl, "0x11");}
		try {Thread.sleep(500);}
		catch (InterruptedException e1) {System.out.println("[ERROR] 0x12");}
		try {
			st = Runtime.getRuntime()
				.exec("adb -s "+tgt+" shell am start cn.xuexi.android/com.alibaba.android.rimet.biz.SplashActivity");
		} catch (IOException e) {new ProcessErrorReport(st, "0x13");}
		try {Thread.sleep(10000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x14");}
	}
	
	void renew_page(int pg) {
		double_tap(w/10*(2*pg-1), h-90);
	}
	
	void right_switch(int i) {
		for(int j=0; j<i; j++) {
			swipe((w/4)*3, (h/5)*4, w/4, (h/5)*4, 200);
			try {Thread.sleep(1000);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x15");}
		}
	}
	
	void reading(int t) {
		renew_page(3);
		try {Thread.sleep(2000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x16");}
		right_switch(3);
		try {Thread.sleep(1000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x17");}
		for(int i=0; i<t; i++) {
			swipe(w/2, (h/3)*2, w/2, h/3, 200);
			try {Thread.sleep(1500);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x18");}
			tap(w/2, h/2);	//打开文章
			try {Thread.sleep(1000);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x19");}
			for(int a=0; a<10; a++) {
				swipe(w/2, (h/3)*2, w/2, h/3, 500); //阅读文章
				try {Thread.sleep(1500);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x1A");}
			}
			pnt_own++;
			sh.append("阅读文章 "+pnt_own+"/"+pnt+"\n");
			back();
			try {Thread.sleep(2000);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x1B");}
		}
	}
	
	void watching(int t) {
		renew_page(4);
		try {Thread.sleep(2000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x1C");}
		swipe(w/2, (h/3)*2, w/2, h/3, 200);
		try {Thread.sleep(1500);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x1D");}
		for(int i=0; i<t; i++) {
			int[] xy = {(w/3)*2, h/3};
			while(true) {
				swipe(w/2, (h/3)*2, w/2, h/3, 200);
				try {Thread.sleep(2500);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x1E");}
				xy[0] = (w/3)*2;
				xy[1] = h/3;
				get_screen();
				if(findVideo(xy)) break;
			}
			tap(xy[0], xy[1]);	//打开视频
			try {Thread.sleep(20000);}	//观看视频
			catch (InterruptedException e) {System.out.println("[ERROR] 0x1F");}
			pnt_own++;
			sh.append("观看视频 "+pnt_own+"/"+pnt+"\n");
		}
		renew_page(3);
	}
	
	boolean findVideo(int[] xy) {
		int x = xy[0], y = xy[1];
		BufferedImage img = null;
		try {img = ImageIO.read(new File("temp.png"));}
		catch (IOException e) {System.out.println("[ERROR] 0x20");}
		while(isBlackGrayWhite(img.getRGB(x, y))) {
			x--;
			if(x < 0) {
				x = xy[0];
				y++;
			}
			if(y > h-160) return false;
		}
		xy[0] = x;
		xy[1] = y;
		return true;
	}
	
	boolean isBlackGrayWhite(int rgb) {
		int R, G, B, er = 0x10;
		B = rgb % 0x100;
		G = rgb % 0x10000 / 0x100;
		R = rgb % 0x1000000 / 0x10000;
		if(Math.abs(R-G) > er) return false;
		if(Math.abs(B-G) > er) return false;
		if(Math.abs(R-B) > er) return false;
		return true;
	}
	
	void collecting(int t) {
		renew_page(3);
		try {Thread.sleep(2000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x21");}
		swipe(w/2, (h/3)*2, w/2, h/3, 200);
		try {Thread.sleep(1500);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x22");}
		for(int i=0; i<t; i++) {
			swipe(w/2, (h/3)*2, w/2, h/3, 200);
			try {Thread.sleep(1500);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x23");}
			tap(w/2, h/2);	//打开文章
			try {Thread.sleep(1000);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x24");}
			tap(w-220, h-65);
			try {Thread.sleep(1000);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x25");}
			back();
			try {Thread.sleep(2000);}
			catch (InterruptedException e) {System.out.println("[ERROR] 0x26");}
		}
	}
	
	void sharing(int t) {
		renew_page(3);
		try {Thread.sleep(2000);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x27");}
		swipe(w/2, (h/3)*2, w/2, h/3, 200);
		try {Thread.sleep(1500);}
		catch (InterruptedException e) {System.out.println("[ERROR] 0x28");}
		for(int i=0; i<t; i++) {
			for(int j=0; j<2; j++) {
				swipe(w/2, (h/3)*2, w/2, h/3, 200);
				try {Thread.sleep(1500);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x29");}
				tap(w/2, h/2);	//打开文章
				try {Thread.sleep(1000);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x2A");}
				tap(w-80, h-65);
				try {Thread.sleep(1000);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x2B");}
				tap((w/8)*5, h-780);
				try {Thread.sleep(4000);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x2C");}
				back();
				try {Thread.sleep(2000);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x2D");}
				back();
				try {Thread.sleep(2000);}
				catch (InterruptedException e) {System.out.println("[ERROR] 0x2E");}
			}
			pnt_own++;
			sh.append("分享文章 "+pnt_own+"/"+pnt+"\n");
		}
	}
}
