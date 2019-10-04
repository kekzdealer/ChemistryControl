package addRecipePanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class AddRecipePanel extends JPanel {
	
	private final JComboBox<Integer> lcrType = new JComboBox<Integer>();
	private final JComboBox<String> result = new JComboBox<String>();
	
	private final JTextArea inputFluids = new JTextArea();
	private final JTextArea inputItems = new JTextArea();
	private final JTextArea outputFluids = new JTextArea();
	private final JTextArea outputItems = new JTextArea();
	
	private final int textAreaWidth = 400;
	private final int textAreaHeight = 200;
	
	public AddRecipePanel() {
		super();
		
		super.setLayout(null);
		super.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY, 1, false), 
				"Add a new Recipe:", 
				TitledBorder.LEFT, 
				TitledBorder.ABOVE_TOP));
		
		
		
		inputFluids.setLocation(30, 30);
		inputFluids.setSize(textAreaWidth, textAreaHeight);
		inputFluids.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Input Fluids"));
		
		inputItems.setLocation(30, 30 + (textAreaHeight * 2) + 10);
		inputItems.setSize(textAreaWidth, textAreaHeight);
		inputItems.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Input Items"));
		
		outputFluids.setLocation(30, 30 + (textAreaHeight * 3) + 10);
		outputFluids.setSize(textAreaWidth, textAreaHeight);
		outputFluids.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Output Fluids"));
		
		outputItems.setLocation(30, 30 + (textAreaHeight * 4) + 10);
		outputItems.setSize(textAreaWidth, textAreaHeight);
		outputItems.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Output Items"));
		
	}
	
}
