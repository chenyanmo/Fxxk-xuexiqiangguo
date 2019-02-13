package xuexiqiangguo;

import javax.swing.*;

public class UI_Right_TextArea extends JTextArea{
	JScrollPane sp;
	void setScrollPane(JScrollPane s) {
		sp = s;
	}
	public void append(String str) {
		super.append(str);
		sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
	}
}
