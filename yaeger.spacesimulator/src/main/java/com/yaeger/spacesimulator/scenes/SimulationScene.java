package com.yaeger.spacesimulator.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.yaeger.spacesimulator.ui.entities.panels.ControlPanel;

import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene {

	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() {
		ControlPanel controlPanel = new ControlPanel(new Coordinate2D(20, getHeight() - 20), new Size(220, 350));
		controlPanel.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		addEntity(controlPanel);
	}

}
