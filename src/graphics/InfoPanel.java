package graphics;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel {
	
	final JLabel contentLabel = new JLabel();
	
	public InfoPanel(int parentWidth, int parentHeight) {
		super();
		super.setBackground(Color.LIGHT_GRAY);
		super.setLayout(null);
		super.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		super.setVisible(true);
		
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
