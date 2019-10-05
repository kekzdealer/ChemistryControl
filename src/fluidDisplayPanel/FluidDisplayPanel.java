package fluidDisplayPanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import data_loader.GSONLoader;
import graphics.Window;
import logic.Item;
import logic.TFFTJSON;

/**
 * This class represents data from one of the KekzTech T.F.F.T fluid tanks. 
 * Each tank has a maximum capacity and holds 25 different fluids. The capacity for each fluid is one 25th of the maximum capacity.
 * 
 * @author kekzdealer
 *
 */
@SuppressWarnings("serial")
public class FluidDisplayPanel extends JPanel implements Runnable {
	
	private final ArrayList<FluidLevelBar> bars = new ArrayList<>();
	
	public FluidDisplayPanel(int x, int y, int w, int h) {
		super();
		
		super.setLocation(x, y);
		super.setSize(w, h);
		super.setBackground(Color.GRAY);
		super.setLayout(null);
		
		final int barHeight = (h - 28) / 25;
		for(int i = 0; i < 25; i ++) {
			final FluidLevelBar bar = new FluidLevelBar(w - 10, barHeight);
			bar.setLocation(3, 1 + (barHeight + 2) * i);
			super.add(bar);
			bars.add(bar);
		}
	}
	
	public void run() {
		
		while(!Thread.interrupted()) {
			// Fetch data and sort it
			final GSONLoader gsonLoader = new GSONLoader();
			final TFFTJSON tfftjson = gsonLoader.parseRemoteJSON(Window.URL_PREFIX + "/tfft_data", TFFTJSON.class);
			if(tfftjson == null) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					
				}
				continue;
			}
			final ArrayList<Item> fluids = new ArrayList<>();
			for(Item item : tfftjson.fluids) {
				fluids.add(item);
			}
			Collections.sort(fluids, Collections.reverseOrder());
			// Clear progress bars
			for(FluidLevelBar bar : bars) {
				bar.setString("empty");
				bar.setValue(0);
			}
			// Update bars
			for(int i = 0; i < fluids.size(); i++) {
				final FluidLevelBar bar = bars.get(i);
				bar.setString(fluids.get(i).name);
				bar.setUsedCapacity(fluids.get(i).amount);
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				
			}
		}
	}
}
