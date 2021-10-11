package com.yaeger.spacesimulator.ui.entities;

import java.text.Format;

public interface IFormattableValue {
	public void setFormat(String format);

	public void setFormatter(Format formatter);
}
