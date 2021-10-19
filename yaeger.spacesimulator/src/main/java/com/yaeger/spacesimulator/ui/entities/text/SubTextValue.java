package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * A {@code SubTextValue} is a {@link TextEntity}, which is used to create a
 * text entity that is specifically styled as a 'sub' text.
 *
 */
public class SubTextValue extends TextEntity {

	/**
	 * Create a new instance of this {@link SubTextValue} for the given initial
	 * location and initial value.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param initialValue    the initial value as a {@code String}.
	 */
	public SubTextValue(Coordinate2D initialLocation, String initialValue) {
		super(initialLocation, initialValue);
		setFill(Color.LIGHTGREY);
		setFont(Font.font(ConfigService.getValue("font-family"), FontWeight.NORMAL,
				Integer.parseInt(ConfigService.getValue("font-size-s"))));
	}

}
