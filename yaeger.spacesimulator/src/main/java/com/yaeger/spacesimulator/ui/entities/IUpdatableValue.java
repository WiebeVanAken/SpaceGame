package com.yaeger.spacesimulator.ui.entities;

import java.text.Format;

public interface IUpdatableValue<T> {
	public void setValue(T value);

	public void setFormat(String format);

	public void setFormatter(Format formatter);
}
