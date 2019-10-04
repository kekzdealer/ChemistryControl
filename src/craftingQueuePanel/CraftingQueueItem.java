package craftingQueuePanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CraftingQueueItem extends JPanel {
	
	private final JLabel resultNameLabel;
	private final JLabel amountLabel;
	
	public CraftingQueueItem(int w, int h) {
		super();
		
		super.setSize(w, h);
		super.setBackground(Color.LIGHT_GRAY);
		super.setLayout(null);
		
		resultNameLabel = new JLabel("empty");
		resultNameLabel.setLocation(2, 0);
		resultNameLabel.setSize((int) (super.getWidth() * 0.6f), super.getHeight());
		super.add(resultNameLabel);
		
		amountLabel = new JLabel("000");
		amountLabel.setLocation(resultNameLabel.getWidth(), 0);
		amountLabel.setSize(super.getWidth() - resultNameLabel.getWidth(), super.getHeight());
		super.add(amountLabel);
	}
	
	public void setName(String name) {
		resultNameLabel.setText(name);
	}
	
	public void setAmount(int amount) {
		amountLabel.setText(Integer.toString(amount));
	}
}
