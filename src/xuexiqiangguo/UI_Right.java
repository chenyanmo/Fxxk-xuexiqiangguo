package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class UI_Right extends JPanel{
	UI_Right_TextArea shower;
	JScrollPane sp;
	String notice[] = {"[使用前看清楚]\n",
			"1.  打开手机“开发者选项->调试模式/USB调试”；\n",
			"2.  用数据线连接，最好是原厂数据线；\n",
			"3.  必须先登录好APP内账号；\n",
			"4.  如果是第一次收藏文章会有弹窗，请以最快的速度关掉它；\n",
			"5.  请保持网络通畅；\n",
			"6.  使用手机流量时，需打开“设置->通用->非Wi-Fi下视频自动播放”\n",
			"7.  屏幕有虚拟按键，分辨率需减去虚拟按键高度；\n",
			"8.  解锁手机屏幕，点击开始。\n",
			"=====分===割===线=====\n"};
	UI_Right(){
		setLayout(new BorderLayout());
		shower = new UI_Right_TextArea();
		shower.setBackground(Color.BLACK);
		shower.setForeground(Color.GREEN);
		shower.setLineWrap(true);
		shower.setEditable(false);
		sp = new JScrollPane(shower);
		shower.setScrollPane(sp);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(sp);
		show_notice();
	}
	
	void show_notice() {
		for(String s: notice) shower.append(s);
	}
}
