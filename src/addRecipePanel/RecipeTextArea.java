package addRecipePanel;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import logic.Item;

@SuppressWarnings("serial")
public class RecipeTextArea extends JTextArea {
	
	public RecipeTextArea(String title)	{
		super();
		super.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), title));
		super.setBackground(Color.LIGHT_GRAY);
		
	}
	
	public Set<Item> getItems() throws NumberFormatException {
		if(super.getText().equals("")) {
			return new HashSet<Item>();
		}
		final HashSet<Item> items = new HashSet<Item>();
		final String[] lines = super.getText().split("\n");
		System.out.println(lines.length);
		for(String line : lines) {
			final String[] pair = line.split("=");
			if(pair.length == 2) {
				final Item item = new Item(
						pair[0].trim(),
						Integer.parseInt(pair[1].trim())
						);
				items.add(item);
			} else {
				items.add(new Item(pair[0].trim(), 0));
			}
		}
		return items;
	}
	
}
