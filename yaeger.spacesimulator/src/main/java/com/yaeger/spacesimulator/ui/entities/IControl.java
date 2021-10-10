package com.yaeger.spacesimulator.ui.entities;

public interface IControl {
	public void updateValue();

	public void setControlValue(IUpdatableValue<Double> value);
}
