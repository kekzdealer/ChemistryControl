package graphics;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import addRecipePanel.RecipeEditorPanel;
import craftingQueuePanel.CraftingQueuePanel;
import fluidDisplayPanel.FluidDisplayPanel;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public static final int CRAFTING_REQUEST_VIEW = 0;
	public static final int RECIPE_CREATOR_VIEW = 1;
	public static final int RECIPE_EDITOR_VIEW = 2;
	public static final HashMap<Integer, JPanel> views = new HashMap<>();
	
	public static final String URL_PREFIX = "http://localhost:8000";
	
	public static void switchView(int viewID) {
		final JPanel v = views.get(viewID);
		if(v != null) {
			for(JPanel p : views.values()) {
				if(p.equals(v)) {
					p.setVisible(true);
				} else {
					p.setVisible(false);
				}
			}
		}
	}
	
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
		
		try {
			final Image windowIcon = 
					ImageIO.read(Class.class.getResource("/graphics/OVERLAY_FRONT_ACTIVE.png"));
			gui.setIconImage(windowIcon);
		} catch (IOException e) {
			System.err.println("Failed to load window icon");
		}
		
		final MenuBar menuBar = new MenuBar();
		gui.setJMenuBar(menuBar);
		
		final InfoPanel infoPanel = new InfoPanel(gui.getWidth(), gui.getHeight());
		infoPanel.setSize(gui.getWidth() - 15, 20);
		infoPanel.setLocation(3, gui.getHeight() - 79);
		gui.add(infoPanel);
		
		// Initialize Views
		final CraftingRequestPanel craftingRequestPanel = new CraftingRequestPanel();
		craftingRequestPanel.setVisible(true);
		craftingRequestPanel.setLocation(0, 0);
		craftingRequestPanel.setSize(750, 700);
		gui.add(craftingRequestPanel);
		views.put(CRAFTING_REQUEST_VIEW, craftingRequestPanel);
		
		final RecipeEditorPanel addRecipePanel = new RecipeEditorPanel(RecipeEditorPanel.ADD_RECIPE);
		addRecipePanel.setVisible(false);
		addRecipePanel.setLocation(0, 0);
		addRecipePanel.setSize(750, 700);
		gui.add(addRecipePanel);
		views.put(RECIPE_CREATOR_VIEW, addRecipePanel);
		
		final RecipeEditorPanel editRecipePanel = new RecipeEditorPanel(RecipeEditorPanel.EDIT_RECIPE);
		editRecipePanel.setVisible(false);
		editRecipePanel.setLocation(0, 0);
		editRecipePanel.setSize(750, 700);
		gui.add(editRecipePanel);
		views.put(RECIPE_EDITOR_VIEW, editRecipePanel);
		
		// Initialize T.F.F.T Display
		final FluidDisplayPanel fluidDisplayPanel = 
				new FluidDisplayPanel(1030, 11, 300, 600);
		fluidDisplayPanel.setVisible(true);
		gui.add(fluidDisplayPanel);
		
		final Thread fluidDisplayThread = new Thread(fluidDisplayPanel);
		fluidDisplayThread.setName("Fluid Display Panel Updater");
		fluidDisplayThread.start();
		
		// Initialize Crafting Queue Display
		final CraftingQueuePanel craftingQueuePanel =
				new CraftingQueuePanel(800, 11, 200, 300);
		craftingQueuePanel.setVisible(true);
		gui.add(craftingQueuePanel);
		
		final Thread craftingQueueThread = new Thread(craftingQueuePanel);
		craftingQueueThread.setName("Crafting Queue Display Panel Updater");
		craftingQueueThread.start();
		
		// Finish	
		gui.setVisible(true);
	}

}
