package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.yaeger.spacesimulator.scenes.SimulationScene;
import com.yaeger.spacesimulator.services.ConfigService;
import com.yaeger.spacesimulator.services.SimulationPauseService;

import javafx.scene.input.MouseButton;

/**
 * A {@code PauseButton} is a concrete implementation of {@link SpriteEntity},
 * which is used to create a button that toggles the paused/running state of the
 * {@link SimulationScene} using the {@link SimulationPauseService}.
 *
 */
public class PauseButton extends SpriteEntity implements MouseButtonReleasedListener {

	/**
	 * Create a new instance of {@link PauseButton} for the given location and size.
	 *
	 * @param location the location as a {@link Coordinate2D}.
	 * @param size     the size as a {@link Size}.
	 */
	public PauseButton(Coordinate2D location, Size size) {
		super("PauseButton.png", location, size, 1, 2);
		this.setViewOrder(Double.parseDouble(ConfigService.getValue("ui-vieworder")));
	}

	/**
	 * Used to update the current image from a pause icon to a play icon or
	 * reversed.
	 */
	public void updateSprite() {
		this.setCurrentFrameIndex(SimulationPauseService.getInstance().getPaused() ? 0 : 1);
	}

	/**
	 * Called when the mouse button is being released and toggles the paused/running
	 * state of the {@link SimulationScene} using the
	 * {@link SimulationPauseService}.
	 */
	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		this.updateSprite();
		SimulationPauseService.getInstance().togglePause();
	}
}
