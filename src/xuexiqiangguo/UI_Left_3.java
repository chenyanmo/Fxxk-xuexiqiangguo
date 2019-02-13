package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class UI_Left_3 extends JPanel{
	JTextField w_input, h_input;
	JButton changed;
	UI_Left_3(){
		setLayout(new FlowLayout());
		w_input = new JTextField("0", 5);
		h_input = new JTextField("0", 5);
		changed = new JButton("修改");
		add(w_input);
		add(new JLabel("X"));
		add(h_input);
		add(changed);
	}
}