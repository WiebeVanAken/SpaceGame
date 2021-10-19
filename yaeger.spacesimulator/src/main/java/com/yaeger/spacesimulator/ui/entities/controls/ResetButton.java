package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.yaeger.spacesimulator.scenes.SimulationScene;

import javafx.scene.input.MouseButton;

/**
 * A {@code ResetButton} is a concrete implementation of a {@link SimpleButton},
 * which is used to reset the {@link SimulationScene}.
 *
 */
public class ResetButton extends SimpleButton {

	private SimulationScene scene;

	/**
	 * Create an instance of this {@link ResetButton} for the given initial
	 * location, size and scene.
	 *
	 * @param initialLocation the initial location as a {@link Coordinate2D}.
	 * @param size            the size as a {@link Size}.
	 * @param scene           the scene as a {@link SimulationScene}.
	 */
	public ResetButton(Coordinate2D initialLocation, Size size, SimulationScene scene) {
		super(initialLocation, size, "Reset");
		this.scene = scene;
	}

	/**
	 * Called when the mouse button is released and will reset the simulation by
	 * calling {@link SimulationScene.resetSimulation()}.
	 */
	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		scene.resetSimulation();
	}

}
