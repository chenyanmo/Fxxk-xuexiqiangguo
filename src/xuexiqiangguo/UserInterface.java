package xuexiqiangguo;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class UserInterface extends JFrame implements ActionListener, ItemListener{
	Container cp;
	JButton renew_btn, run_btn, change_btn;
	JTextArea shower;
	JComboBox list;
	JTextField w_input, h_input, point_input;
	JLabel scrm_show;
	UI_Left left;
	UI_Right right;
	
	String target = "";
	int scrm_w = 0, scrm_h = 0, point = 15;
	
	UserInterface(){
		InitNoticeWindow inw = new InitNoticeWindow();
		initFrame();
		initUI();
		initVar();
		setVisible(true);
		inw.setVisible(false);
	}
	
	void initFrame() {
		cp = getContentPane();
		setBounds(200,200,500,300);
		setResizable(false);
		setTitle("Fxxk XXQG v19.2.12");
		addWindowListener(new CloseFrame());
		cp.setLayout(new GridLayout(1,2));
	}
	
	void initUI(){
		left = new UI_Left();
		right = new UI_Right();
		cp.add(left);
		cp.add(right);
	}
	
	void initVar() {
		renew_btn = left.left1.renew_dvc;
		renew_btn.addActionListener(this);
		run_btn = left.left4.run;
		run_btn.addActionListener(this);
		shower = right.shower;
		list = left.left1.list_dvc;
		list.addItemListener(this);
		h_input = left.left3.h_input;
		w_input = left.left3.w_input;
		point_input = left.left4.point_input;
		change_btn = left.left3.changed;
		change_btn.addActionListener(this);
		scrm_show = left.left2.scrm_show;
		renew_list();
		if(list.getItemCount()==0) return;
		target = list.getSelectedItem().toString();
		getScrmSize();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==renew_btn) renew_list();
		else if(e.getSource()==run_btn) {
			point = Integer.valueOf(point_input.getText());
			if(target == "") {
				shower.append("[提示] 找不到设备，请刷新。\n");
				return;
			}
			if(scrm_w==0 || scrm_h==0) {
				shower.append("[提示] 请输入屏幕分辨率。\n");
				return;
			}
			if(point > 17 || point < 1) {
				shower.append("[提示] 积分必须为正整数且不能大于17。\n");
				return;
			}
			new Running(target, point, shower, scrm_w, scrm_h);
		}
		else if(e.getSource()==change_btn) {
			try {
				scrm_w = Integer.valueOf(w_input.getText());
				scrm_h = Integer.valueOf(h_input.getText());
			}
			catch(Exception ecpt) {return;}
			scrm_show.setText(String.valueOf(scrm_w)+" X "+String.valueOf(scrm_h));
		}
		else ;
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==list) {
			target = "";
			scrm_w = 0;
			scrm_h = 0;
			w_input.setText("0");
			h_input.setText("0");
			if(list.getItemCount()==0) return;
			target = list.getSelectedItem().toString();
			getScrmSize();
		}
	}
	
	void renew_list() {
		list.removeAllItems();
		Process get_dvc = null;
		try {get_dvc = Runtime.getRuntime().exec("adb devices");}
		catch (IOException e) {
			new ProcessErrorReport(get_dvc, "0x01");
			return;
		}
		BufferedReader info_dvc = new BufferedReader(
				new InputStreamReader(get_dvc.getInputStream()));
		String temp;
		try {
			while((temp=info_dvc.readLine())!=null)
				if(temp.endsWith("device"))
					list.addItem((new StringTokenizer(temp)).nextToken());
		}
		catch (IOException e) {
			System.out.println("[ERROR] 0x02");
			return;
		}
	}
	
	void getScrmSize() {
		Process get_size = null;
		try {get_size = Runtime.getRuntime().exec("adb -s "+target+" shell wm size");}
		catch (IOException e) {
			new ProcessErrorReport(get_size, "0x03");
			return;
		}
		BufferedReader info_size = new BufferedReader(
				new InputStreamReader(get_size.getInputStream()));
		StringTokenizer info_string = null;
		try {info_string = new StringTokenizer(info_size.readLine());}
		catch (IOException e) {
			System.out.println("[ERROR] 0x04");
			return;
			}
		if(info_string.countTokens()!=3) return;
		String size = "";
		while(info_string.hasMoreTokens()) size = info_string.nextToken();
		String[] size_split = size.split("x");
		scrm_w = Integer.valueOf(size_split[0]);
		scrm_h = Integer.valueOf(size_split[1]);
		w_input.setText(size_split[0]);
		h_input.setText(size_split[1]);
		scrm_show.setText(String.valueOf(scrm_w)+" X "+String.valueOf(scrm_h));
	}
}
