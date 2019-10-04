package graphics;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import addRecipePanel.AddRecipePanel;
import craftingQueuePanel.CraftingQueuePanel;
import fluidDisplayPanel.FluidDisplayPanel;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public static final String URL_PREFIX = "http://localhost:8000";
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			System.err.println("Failed to set Look and Feel to System specific");
		}
		
		final Window gui = new Window();
		gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		gui.setTitle("Crafter " + "V0.1");
		gui.setSize(1400, 900);
		gui.setResizable(false);
		gui.setLayout(null);
		gui.getContentPane().setBackground(Color.GRAY);
		
		final InfoPanel infoPanel = new InfoPanel(gui.getWidth(), gui.getHeight());
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		infoPanel.setVisible(true);
		gui.add(infoPanel);
		
		final MenuBar menuBar = new MenuBar();
		gui.setJMenuBar(menuBar);
		
		final CraftingRequestPanel craftingRequestPanel = new CraftingRequestPanel();
		craftingRequestPanel.setVisible(false);
		craftingRequestPanel.setLocation(0, 0);
		craftingRequestPanel.setSize(750, 700);
		gui.add(craftingRequestPanel);
		
		final AddRecipePanel addRecipePanel = new AddRecipePanel();
		addRecipePanel.setVisible(true);
		addRecipePanel.setLocation(0, 0);
		addRecipePanel.setSize(750, 700);
		gui.add(addRecipePanel);
		
		final FluidDisplayPanel fluidDisplayPanel = 
				new FluidDisplayPanel(1030, 11, 300, 600);
		fluidDisplayPanel.setVisible(true);
		gui.add(fluidDisplayPanel);
		
		final Thread fluidDisplayThread = new Thread(fluidDisplayPanel);
		fluidDisplayThread.setName("Fluid Display Panel Updater");
		fluidDisplayThread.start();
		
		final CraftingQueuePanel craftingQueuePanel =
				new CraftingQueuePanel(800, 11, 200, 300);
		craftingQueuePanel.setVisible(true);
		gui.add(craftingQueuePanel);
		
		final Thread craftingQueueThread = new Thread(craftingQueuePanel);
		craftingQueueThread.setName("Crafting Queue Display Panel Updater");
		craftingQueueThread.start();
				
		gui.setVisible(true);
		
	}

}
