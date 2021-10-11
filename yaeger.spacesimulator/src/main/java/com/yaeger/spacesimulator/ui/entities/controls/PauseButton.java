package com.yaeger.spacesimulator.ui.entities.controls;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.yaeger.spacesimulator.services.SimulationPauseService;

import javafx.scene.input.MouseButton;

public class PauseButton extends SpriteEntity implements MouseButtonReleasedListener {

	/**
	 * @param location
	 * @param size
	 */
	public PauseButton(Coordinate2D location, Size size) {
		super("PauseButton.png", location, size, 1, 2);
	}
	
	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D coordinate2d) {
		SimulationPauseService instance = SimulationPauseService.getInstance();
		this.setCurrentFrameIndex(instance.getPaused() ? 0 : 1);
		instance.togglePause();
	}
}
