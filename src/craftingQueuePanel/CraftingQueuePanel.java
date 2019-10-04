package craftingQueuePanel;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

import data_loader.GSONLoader;
import graphics.Window;
import logic.Recipe;
import logic.RecipesJSON;

@SuppressWarnings("serial")
public class CraftingQueuePanel extends JPanel implements Runnable {
	
	private final ArrayList<CraftingQueueItem> items = new ArrayList<>();
	
	public CraftingQueuePanel(int x, int y, int w, int h) {
		super();
		
		super.setLocation(x, y);
		super.setSize(w, h);
		super.setBackground(Color.GRAY);
		super.setLayout(null);
		
		final int itemHeight = (h - 28) / 10;
		for(int i = 0; i < 10; i ++) {
			final CraftingQueueItem item = new CraftingQueueItem(w - 10, itemHeight);
			item.setLocation(3, 1 + (itemHeight + 2) * i);
			super.add(item);
			items.add(item);
		}
	}
	
	public void run() {
		
		while(!Thread.interrupted()) {
			// Fetch data
			final GSONLoader gsonLoader = new GSONLoader();
			final RecipesJSON recipesJSON = gsonLoader.parseRemoteJSON(Window.URL_PREFIX + "/crafting_queue", RecipesJSON.class);
			if(recipesJSON == null) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					
				}
				continue;
			}
			final ArrayList<Recipe> recipes = new ArrayList<>();
			for(Recipe item : recipesJSON.recipes) {
				recipes.add(item);
			}
			// Clear items
			for(CraftingQueueItem item : items) {
				item.setName("empty");
				item.setAmount(0);
			}
			// Update items
			for(int i = 0; i < recipes.size(); i++) {
				final CraftingQueueItem item = items.get(i);
				item.setName(recipes.get(i).result);
				item.setAmount(recipes.get(i).getAmountOfResult(recipes.get(i).result));
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				
			}
		}
	}
}
