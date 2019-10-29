package addRecipePanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.google.gson.Gson;

import graphics.Window;
import logic.Item;
import logic.Recipe;

@SuppressWarnings("serial")
public class RecipeEditorPanel extends JPanel {
	
	public static final int ADD_RECIPE = 0;
	public static final int EDIT_RECIPE = 1;
	
	private final JComboBox<String> resultPicker = new JComboBox<String>();
	
	private final JPanel lcrRadioPanel = new JPanel();
	private final ButtonGroup lcrRadioGroup = new ButtonGroup();
	private final JRadioButton lcr1Radio = new JRadioButton();
	private final JRadioButton lcr24Radio = new JRadioButton();
	
	private final JButton commitButton = new JButton();
	
	private final RecipeTextArea inputFluids = new RecipeTextArea("Input Fluids");
	private final RecipeTextArea inputItems = new RecipeTextArea("Input Items");
	private final RecipeTextArea outputFluids = new RecipeTextArea("Output Fluids");
	private final RecipeTextArea outputItems = new RecipeTextArea("Output Items");
	
	private final int textAreaWidth = 400;
	private final int textAreaHeight = 120;
	
	public RecipeEditorPanel(int mode) {
		super();
		
		String panelTitle;
		String commitButtonTitle;
		if(mode == 0) {
			panelTitle = "Recipe Creator";
			commitButtonTitle = "Create Recipe";
		} else {
			panelTitle = "Recipe Editor";
			commitButtonTitle = "Save Recipe";
		}
		
		super.setLayout(null);
		super.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY, 1, false), 
				panelTitle, 
				TitledBorder.RIGHT, 
				TitledBorder.BELOW_BOTTOM));
		super.setBackground(Color.GRAY);
		
		inputFluids.setLocation(30, 30);
		inputFluids.setSize(textAreaWidth, textAreaHeight);
		super.add(inputFluids);
		
		inputItems.setLocation(30, 30 + (textAreaHeight * 1) + 10);
		inputItems.setSize(textAreaWidth, textAreaHeight);
		super.add(inputItems);
		
		outputFluids.setLocation(30, 30 + ((textAreaHeight + 10) * 2));
		outputFluids.setSize(textAreaWidth, textAreaHeight);
		super.add(outputFluids);
		
		outputItems.setLocation(30, 30 + ((textAreaHeight + 10) * 3));
		outputItems.setSize(textAreaWidth, textAreaHeight);
		super.add(outputItems);
		
		resultPicker.setLocation(30, 30 + ((textAreaHeight + 10) * 4));
		resultPicker.setSize(textAreaWidth - 60, 30);
		super.add(resultPicker);
		
		final KeyListener refreshListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					final Set<Item> items = outputFluids.getItems();
					items.addAll(outputItems.getItems());
					
					final String[] names = new String[items.size()];
					final Iterator<Item> itemsI = items.iterator();
					int i = 0;
					while(itemsI.hasNext()) {
						names[i] = itemsI.next().name;
						i++;
					}
					resultPicker.setModel(
							new DefaultComboBoxModel<String>(names));
				}	
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
		};

		outputFluids.addKeyListener(refreshListener);
		outputItems.addKeyListener(refreshListener);
		
		lcr1Radio.setSelected(true);
		lcr1Radio.setText("LCR 1");
		lcr1Radio.setBackground(Color.GRAY);
		
		lcr24Radio.setSelected(false);
		lcr24Radio.setText("LCR 24");
		lcr24Radio.setBackground(Color.GRAY);
		
		lcrRadioGroup.add(lcr1Radio);
		lcrRadioGroup.add(lcr24Radio);
		
		lcrRadioPanel.setLayout(new GridLayout(1, 2));
		lcrRadioPanel.setLocation(500, 420);
		lcrRadioPanel.setSize(160, 50);
		lcrRadioPanel.setBackground(Color.GRAY);
		lcrRadioPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), 
				"Integrated Circuit"));
		lcrRadioPanel.add(lcr1Radio);
		lcrRadioPanel.add(lcr24Radio);
		super.add(lcrRadioPanel);
		
		commitButton.setLocation(500, 480);
		commitButton.setSize(160,  30);
		commitButton.setText(commitButtonTitle);
		super.add(commitButton);
		
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commitRecipe();
			}
		});
		
	}
	
	private Recipe generateRecipe() {
		final Recipe recipe = new Recipe();
		recipe.input_fluid = inputFluids.getItems();
		recipe.input_item = inputItems.getItems();
		recipe.output_fluid = outputFluids.getItems();
		recipe.output_fluid = outputFluids.getItems();
		return recipe;
	}
	
	private void commitRecipe() {
		HttpURLConnection con = null;
		try {
			final URL url = new URL(Window.URL_PREFIX + "/commit_recipe");
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			con.setDoOutput(true);
			final DataOutputStream dos = new DataOutputStream(con.getOutputStream());
			
			final Recipe recipe = generateRecipe();
			final Gson gson = new Gson();
			final String jsonString = gson.toJson(recipe, Recipe.class);
			dos.write(jsonString.getBytes());
			dos.flush();
			dos.close();
			
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (ProtocolException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
