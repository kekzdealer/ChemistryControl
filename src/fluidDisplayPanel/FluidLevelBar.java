package fluidDisplayPanel;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class FluidLevelBar extends JProgressBar {
	
	private String name = "empty";
	private int maxCapacity = 4000000;
	private int usedCapacity = 2000000;
	
	public FluidLevelBar() {
		super();		
		
		super.setMinimum(0);
		super.setOrientation(SwingConstants.HORIZONTAL);
		super.setStringPainted(true);
		
		super.setString(name);
		super.setMaximum(maxCapacity);
		super.setValue(usedCapacity);
	}
	
	public void setName(String name) {
		this.name = name;
		super.setString(name);
	}
	
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		super.setMaximum(maxCapacity);
	}
	
	public void setUsedCapacity(int usedCapacity) {
		this.usedCapacity = usedCapacity;
		super.setValue(usedCapacity);
	}
	
	public String getName() {
		return name;
	}
}
