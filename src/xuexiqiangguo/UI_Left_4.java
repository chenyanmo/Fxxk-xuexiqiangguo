package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class UI_Left_4 extends JPanel{
	JButton run;
	JTextField point_input;
	UI_Left_4(){
		setLayout(new FlowLayout());
		run = new JButton("开始");
		point_input = new JTextField("15", 3);
		add(new JLabel("积分设置："));
		add(point_input);
		add(run);
	}
}