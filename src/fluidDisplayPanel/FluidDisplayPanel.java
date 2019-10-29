package fluidDisplayPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

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
public class FluidDisplayPanel extends JScrollPane implements Runnable {
	
	private final ArrayList<FluidLevelBar> bars = new ArrayList<>();
	private final JPanel contentPanel = new JPanel();
	
	public FluidDisplayPanel(int maxFluidTypes) {
		super();
		
		super.setBackground(Color.GRAY);
		super.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY, 1, false), 
				"T.F.F.T Data Feed", 
				TitledBorder.LEFT, 
				TitledBorder.TOP));
		super.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		super.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		contentPanel.setLayout(new GridLayout(maxFluidTypes, 1));
		
		for(int i = 0; i < maxFluidTypes; i ++) {
			final FluidLevelBar bar = new FluidLevelBar();
			//bar.setSize(250, 30);
			contentPanel.add(bar);
			bars.add(bar);
		}
		
		super.setViewportView(contentPanel);
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
					// ?
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
