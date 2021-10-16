package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.scenes.SimulationScene;

import javafx.scene.input.MouseButton;

public class ResetButton extends SimpleButton {

	private SimulationScene scene;

	public ResetButton(Coordinate2D initialLocation, Size size, SimulationScene scene) {
		super(initialLocation, size, "Reset");
		this.scene = scene;
	}

	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		scene.resetSimulation();
	}

}
