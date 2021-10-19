package com.yaeger.spacesimulator.services;

import com.yaeger.spacesimulator.scenes.SimulationScene;
import com.yaeger.spacesimulator.ui.entities.controls.PauseButton;

/**
 * A {@code SimulationPauseService} is a singleton service, which is used to
 * toggle the paused/running state of the {@link SimulationScene}.
 *
 */
public class SimulationPauseService {
	private static SimulationPauseService instance;
	private boolean paused = false;
	private SimulationScene simulationScene;
	private PauseButton pauseButton;

	private SimulationPauseService(SimulationScene simulationScene, PauseButton pauseButton) {
		this.simulationScene = simulationScene;
		this.pauseButton = pauseButton;
	}

	private void pause() {
		simulationScene.getSimulationObjects().forEach(obj -> {
			obj.freezeVelocity();
			obj.updateMovement();
		});
	}

	private void unpause() {
		simulationScene.getSimulationObjects().forEach(obj -> {
			obj.unfreezeVelocity();
			obj.updateMovement();
		});
	}

	/**
	 * Used to toggle the paused/running state of the {@link SimulationScene}.
	 */
	public void togglePause() {
		this.setPaused(!this.paused);
	}

	/**
	 * Used to set the paused/running state of the {@link SimulationScene}.
	 *
	 * @param isPaused the pause state as a {@code boolean}.
	 */
	public void setPaused(boolean isPaused) {
		this.paused = isPaused;
		pauseButton.updateSprite();

		if (this.paused)
			this.pause();
		else
			this.unpause();
	}

	/**
	 * Used to get the pause state of the {@link SimulationScene}.
	 *
	 * @return the paused state as a {@code boolean}.
	 */
	public boolean getPaused() {
		return this.paused;
	}

	/**
	 * Retrieve an instance of this service class
	 *
	 * @return an instance of this singleton service class
	 */
	public static SimulationPauseService getInstance(SimulationScene scene, PauseButton button) {
		if (instance == null)
			initializeInstance(scene, button);

		return instance;
	}

	/**
	 * Retrieve an instance of this service class
	 *
	 * @return an instance of this service class
	 * @throws IllegalStateException when an instance does not exist yet. Use
	 *                               getInstance(DynamicScene scene) instead
	 */
	public static SimulationPauseService getInstance() throws IllegalStateException {
		if (instance == null)
			throw new IllegalStateException();

		return instance;
	}

	/**
	 * Initialize this service
	 *
	 * @param scene to pause/unpause
	 */
	public static void initializeInstance(SimulationScene scene, PauseButton button) {
		instance = new SimulationPauseService(scene, button);
	}
}
