package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.yaeger.spacesimulator.entities.Planet;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.services.SimulationUpdateService;

import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene implements UpdateExposer {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();
	
	private ArrayList<SimulationObject> objects = new ArrayList<SimulationObject>();
	
	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() { 
		objects.add(new Planet(new Coordinate2D(10, 10), new Coordinate2D(1, 0), 1, 100, 10, new Color(0.70D, 0.70D, 0.70D, 1.0D)));
		
		objects.forEach(obj -> { this.addEntity(obj); });
	}
	
	@Override
	public void explicitUpdate(long timestamp) {
		// TODO Auto-generated method stub
		simulationUpdater.updateSimulation(objects);
	}
}