package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class InitNoticeWindow extends JWindow{
	InitNoticeWindow(){
		setBounds(300,300,200,100);
		add(new JLabel("初始化中……", JLabel.CENTER), "Center");
		setVisible(true);
	}
}
