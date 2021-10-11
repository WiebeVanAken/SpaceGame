package com.yaeger.spacesimulator.services;

import com.github.hanyaeger.api.scenes.DynamicScene;

public class SimulationPauseService {
	private static SimulationPauseService instance;
	private boolean paused = false;
	private DynamicScene scene;
	
	private SimulationPauseService(DynamicScene scene) { 
		this.scene = scene;
	}
	
	public void togglePause() {
		this.setPaused(!this.paused);
	}
	
	public void setPaused(boolean isPaused) {
		this.paused = isPaused;
		
		if(this.paused) 
			this.scene.pause();
		 else 
			this.scene.resume();
	}
	
	public boolean getPaused() {
		return this.paused;
	}
	
	/**
	 * Retrieve an instance of this service class
	 * 
	 * @return an instance of this singleton service class
	 */
	public static SimulationPauseService getInstance(DynamicScene scene) {
		if (instance == null) 
			initializeInstance(scene);

		return instance;
	}
	
	/**
	 * Retrieve an instance of this service class
	 * @return an instance of this service class
	 * @throws IllegalStateException when an instance does not exist yet. Use getInstance(DynamicScene scene) instead
	 */
	public static SimulationPauseService getInstance() throws IllegalStateException {
		if(instance == null)
			throw new IllegalStateException();
		
		return instance;
	}
	
	/**
	 * Initialize this service
	 * @param scene to pause/unpause
	 */
	public static void initializeInstance(DynamicScene scene) {
		instance = new SimulationPauseService(scene);
	}
}
