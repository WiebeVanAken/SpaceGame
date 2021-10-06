package com.yaeger.spacesimulator.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.yaeger.spacesimulator.shared.entities.TextValueField;

import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene {

	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() {
		var massInputControl = new TextValueField(new Coordinate2D(10, getHeight() - 40), "12345");
		addEntity(massInputControl);
	}

}
