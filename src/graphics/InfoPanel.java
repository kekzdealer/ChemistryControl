package graphics;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	
	final JLabel contentLabel = new JLabel();
	
	public InfoPanel(int parentWidth, int parentHeight) {
		super();
				
		super.setLocation(3, parentHeight - 3 - 20 - 30);
		super.setSize(parentWidth - 12, 20);
		super.setLayout(null);
		
		contentLabel.setLocation(3, 1);
		contentLabel.setSize(parentWidth - 14, 18);
		super.add(contentLabel);
	}
	
	public void updateInfo(String content) {
		contentLabel.setForeground(Color.BLACK);
		contentLabel.setText(content);
	}
	
	public void updateError(String content) {
		contentLabel.setForeground(Color.RED);
		contentLabel.setText(content);
	}
}
