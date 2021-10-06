package com.yaeger.spacesimulator.shared.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.yaeger.spacesimulator.service.ConfigService;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TextValue extends TextEntity {

	public TextValue(Coordinate2D initialLocation, String initialValue) {
		super(initialLocation, initialValue);
		setFill(Color.WHITESMOKE);
		setFont(Font.font(ConfigService.getValue("font-family"), FontWeight.SEMI_BOLD,
				Integer.parseInt(ConfigService.getValue("font-size-m"))));
	}

}
