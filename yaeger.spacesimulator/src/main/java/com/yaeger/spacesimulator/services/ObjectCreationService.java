package com.yaeger.spacesimulator.services;

import com.github.hanyaeger.api.Coordinate2D;
import com.yaeger.spacesimulator.dto.ObjectPlacementDTO;
import com.yaeger.spacesimulator.entities.CentrePlanet;
import com.yaeger.spacesimulator.entities.Planet;
import com.yaeger.spacesimulator.scenes.SimulationScene;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class ObjectCreationService {
	private static ObjectCreationService instance;
	private SimulationScene simulationScene;
	
	private ObjectCreationService(SimulationScene scene) {
		this.simulationScene = scene;
	}
	
	public void addPlanet(ObjectPlacementDTO data) {
		System.out.println(String.format("Deze waarde moet niet 0.0 zijn, zonder de slider te bewegen.\n%f", data.getDensity()));
		Planet planet = new Planet(
			new Coordinate2D(data.getStartPosition()),
			new Coordinate2D(data.getDirection().getX(), data.getDirection().getY()), 
			data.getVolume(),
			data.getDensity(), 
			Color.web("0xFFFFFF")
		);
		
		this.simulationScene.addSimulationObject(planet);
	}
	
	public void addCentrePlanet(ObjectPlacementDTO data) {
		CentrePlanet planet = new CentrePlanet(
			new Coordinate2D(data.getStartPosition()),
			new Coordinate2D(Point2D.ZERO),
			1000,
			50,
			data.getColor()
		);
		
		this.simulationScene.addSimulationObject(planet);
	}
	
	/**
	 * Retrieve an instance of this service class
	 * 
	 * @return an instance of this singleton service class
	 */
	public static ObjectCreationService getInstance(SimulationScene scene) {
		if (instance == null) 
			initializeInstance(scene);

		return instance;
	}
	
	/**
	 * Retrieve an instance of this service class
	 * @return an instance of this service class
	 * @throws IllegalStateException when an instance does not exist yet. Use getInstance(DynamicScene scene) instead
	 */
	public static ObjectCreationService getInstance() throws IllegalStateException {
		if(instance == null)
			throw new IllegalStateException();
		
		return instance;
	}
	
	/**
	 * Initialize this service
	 * @param scene to pause/unpause
	 */
	public static void initializeInstance(SimulationScene scene) {
		instance = new ObjectCreationService(scene);
	}
}
