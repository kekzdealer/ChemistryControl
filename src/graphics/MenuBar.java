package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private final JMenuItem requestCraft = new JMenuItem("Request Craft");
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
		fileMenu.addSeparator();
		fileMenu.add(requestCraft);
		
		addRecipe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.switchView(Window.RECIPE_CREATOR_VIEW);
			}
		});
		editRecipe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.switchView(Window.RECIPE_EDITOR_VIEW);
			}
		});
		requestCraft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.switchView(Window.CRAFTING_REQUEST_VIEW);
			}
		});
		// Settings
		settingsMenu.add(autoConnect);
		// Help
		helpMenu.add(openGithub);
	}
}
