package com.yaeger.spacesimulator.ui.entities.text;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.yaeger.spacesimulator.services.ConfigService;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SubTextValue extends TextEntity {

	public SubTextValue(Coordinate2D initialLocation, String initialValue) {
		super(initialLocation, initialValue);
		setFill(Color.LIGHTGREY);
		setFont(Font.font(ConfigService.getValue("font-family"), FontWeight.NORMAL,
				Integer.parseInt(ConfigService.getValue("font-size-s"))));
	}

}
