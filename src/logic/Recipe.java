package logic;

public class Recipe {
	
	public int circuit;
	public String result;
	public Item[] input_fluid;
	public Item[] input_item;
	public Item[] output_fluid;
	public Item[] output_item;
	
	public int getInputFluidsLength() {
		return (input_fluid == null) ? 0 : input_fluid.length;
	}
	
	public int getInputItemsLength() {
		return (input_item == null) ? 0 : input_item.length;
	}
	
	public int getOutputFluidsLength() {
		return (output_fluid == null) ? 0 : output_fluid.length;
	}
	
	public int getOutputItemsLength() {
		return (output_item == null) ? 0 : output_item.length;
	}
	
	public int getAmountOfResult(String result) {
		return getAmountOfOutputFluid(result) + getAmountOfOutputItem(result);
	}
	
	public int getAmountOfInputFluid(String name) {
		if(input_fluid == null) {
			return 0;
		}
		for(int i = 0; i < input_fluid.length; i++) {
			if(input_fluid[i].name.equals(name)) {
				return input_fluid[i].amount;
			}
		}
		return 0;
	}
	
	public int getAmountOfInputItem(String name) {
		if(input_item == null) {
			return 0;
		}
		for(int i = 0; i < input_item.length; i++) {
			if(input_item[i].name.equals(name)) {
				return input_item[i].amount;
			}
		}
		return 0;
	}
	
	public int getAmountOfOutputFluid(String name) {
		if(output_fluid == null) {
			return 0;
		}
		for(int i = 0; i < output_fluid.length; i++) {
			if(output_fluid[i].name.equals(name)) {
				return output_fluid[i].amount;
			}
		}
		return 0;
	}
	
	public int getAmountOfOutputItem(String name) {
		if(output_item == null) {
			return 0;
		}
		for(int i = 0; i < output_item.length; i++) {
			if(output_item[i].name.equals(name)) {
				return output_item[i].amount;
			}
		}
		return 0;
	}
}
