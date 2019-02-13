package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class UI_Left_1 extends JPanel{
	JButton renew_dvc;
	JComboBox list_dvc;
	UI_Left_1(){
		setLayout(new FlowLayout());
		renew_dvc = new JButton("刷新");
		list_dvc = new JComboBox();
		list_dvc.setEditable(false);
		add(new JLabel("设备选择："));
		add(list_dvc);
		add(renew_dvc);
	}
}