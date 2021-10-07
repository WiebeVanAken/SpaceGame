package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.yaeger.spacesimulator.entities.Planet;
import com.yaeger.spacesimulator.entities.Simulatable;
import com.yaeger.spacesimulator.services.SimulationUpdateService;

import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene implements UpdateExposer {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();
	
	private ArrayList<Simulatable> objects = new ArrayList<Simulatable>();
	
	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() {
		objects.add(new Planet(new Coordinate2D(10, 10)));
		objects.add(new Planet(new Coordinate2D(10, 20)));
		objects.add(new Planet(new Coordinate2D(10, 30)));
		objects.add(new Planet(new Coordinate2D(10, 40)));
		objects.add(new Planet(new Coordinate2D(10, 50)));
		objects.add(new Planet(new Coordinate2D(10, 60)));
		
		objects.forEach(obj -> { this.addEntity((Planet)obj); });
	}
	
	@Override
	public void explicitUpdate(long timestamp) {
		// TODO Auto-generated method stub
		simulationUpdater.simulate(objects);
	}
}