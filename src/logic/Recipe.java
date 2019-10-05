package logic;

import java.util.Iterator;
import java.util.Set;

public class Recipe {
	
	public int circuit;
	public String result;
	public Set<Item> input_fluid;
	public Set<Item> input_item;
	public Set<Item> output_fluid;
	public Set<Item> output_item;
	
	public int getInputFluidsLength() {
		return (input_fluid == null) ? 0 : input_fluid.size();
	}
	
	public int getInputItemsLength() {
		return (input_item == null) ? 0 : input_item.size();
	}
	
	public int getOutputFluidsLength() {
		return (output_fluid == null) ? 0 : output_fluid.size();
	}
	
	public int getOutputItemsLength() {
		return (output_item == null) ? 0 : output_item.size();
	}
	
	public int getAmountOfResult(String result) {
		return getAmountOfOutputFluid(result) + getAmountOfOutputItem(result);
	}
	
	public int getAmountOfInputFluid(String name) {
		if(input_fluid == null) {
			return 0;
		}
		final Iterator<Item> it = input_fluid.iterator();
		while(it.hasNext()) {
			final Item next = it.next();
			if(next.name.equals(name)) {
				return next.amount;
			}
		}
		return 0;
	}
	
	public int getAmountOfInputItem(String name) {
		if(input_item == null) {
			return 0;
		}
		final Iterator<Item> it = input_item.iterator();
		while(it.hasNext()) {
			final Item next = it.next();
			if(next.name.equals(name)) {
				return next.amount;
			}
		}
		return 0;
	}
	
	public int getAmountOfOutputFluid(String name) {
		if(output_fluid == null) {
			return 0;
		}
		final Iterator<Item> it = output_fluid.iterator();
		while(it.hasNext()) {
			final Item next = it.next();
			if(next.name.equals(name)) {
				return next.amount;
			}
		}
		return 0;
	}
	
	public int getAmountOfOutputItem(String name) {
		if(output_item == null) {
			return 0;
		}
		final Iterator<Item> it = output_item.iterator();
		while(it.hasNext()) {
			final Item next = it.next();
			if(next.name.equals(name)) {
				return next.amount;
			}
		}
		return 0;
	}
}
