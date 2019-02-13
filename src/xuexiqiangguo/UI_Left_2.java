package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class UI_Left_2 extends JPanel{
	JLabel scrm_show;
	UI_Left_2(){
		setLayout(new FlowLayout());
		scrm_show = new JLabel("0 X 0");
		add(new JLabel("屏幕分辨率："));
		add(scrm_show);
	}
}