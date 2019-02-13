package xuexiqiangguo;

import java.awt.*;
import javax.swing.*;

public class UI_Left extends JPanel{
	UI_Left_1 left1;
	UI_Left_2 left2;
	UI_Left_3 left3;
	UI_Left_4 left4;
	UI_Left(){
		setLayout(new GridLayout(4,1));
		left1 = new UI_Left_1();
		left2 = new UI_Left_2();
		left3 = new UI_Left_3();
		left4 = new UI_Left_4();
		add(left1);
		add(left2);
		add(left3);
		add(left4);
	}
}
