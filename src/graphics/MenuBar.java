package graphics;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	private final JMenu fileMenu = new JMenu("File");
	private final JMenu settingsMenu = new JMenu("Settings");
	private final JMenu helpMenu = new JMenu("Help");
	// File
	private final JMenuItem connectTo = new JMenuItem("Connect to");
	private final JMenuItem addRecipe = new JMenuItem("Add Recipe");
	private final JMenuItem editRecipe = new JMenuItem("Edit Recipe");
	// Settings
	private final JCheckBoxMenuItem autoConnect = new JCheckBoxMenuItem("Auto-Connect");
	// Help
	private final JMenuItem openGithub = new JMenuItem("Open Github");
	
	public MenuBar() {
		super();
		
		super.add(fileMenu);
		super.add(settingsMenu);
		super.add(helpMenu);
		
		// File
		fileMenu.add(connectTo);
		fileMenu.addSeparator();
		fileMenu.add(addRecipe);
		fileMenu.add(editRecipe);
		// Settings
		settingsMenu.add(autoConnect);
		// Help
		helpMenu.add(openGithub);
	}
}
