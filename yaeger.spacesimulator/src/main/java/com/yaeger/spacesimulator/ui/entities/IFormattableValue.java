package com.yaeger.spacesimulator.ui.entities;

import java.text.Format;

/**
 * Being a {@code IFormattableValue} enables the implementing class to set the
 * format as a {@code String} and/or formatter as a {@link Format}.
 *
 */
public interface IFormattableValue {

	/**
	 * Used to set the format to be used.
	 *
	 * @param format the format as a {@code String}.
	 */
	public void setFormat(String format);

	/**
	 * Used to set the formatter to be used.
	 *
	 * @param formatter the formatter as a {@link Format}.
	 */
	public void setFormatter(Format formatter);
}
