package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.github.hanyaeger.api.userinput.MouseMovedWhileDraggingListener;
import com.yaeger.spacesimulator.data.ObjectPlacementData;
import com.yaeger.spacesimulator.entities.Planet;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.services.SimulationUpdateService;
import com.yaeger.spacesimulator.ui.entities.panels.ControlPanel;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class SimulationScene extends DynamicScene
		implements UpdateExposer, MouseButtonReleasedListener, MouseMovedWhileDraggingListener {
	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();

	private ArrayList<SimulationObject> objects = new ArrayList<SimulationObject>();
	private ObjectPlacementData data = new ObjectPlacementData();

	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	@Override
	public void setupEntities() {
		objects.forEach(obj -> {
			this.addEntity(obj);
		});
		ControlPanel controlPanel = new ControlPanel(new Coordinate2D(20, getHeight() - 20), new Size(220, 350));
		controlPanel.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		addEntity(controlPanel);
	}

	@Override
	public void explicitUpdate(long timestamp) {
		simulationUpdater.updateSimulation(objects);
	}

	private void placePlanet(ObjectPlacementData data) {
		Planet planet = new Planet(
			data.getStartPosition(), 
			new Coordinate2D(data.getDirection().getX() / 10, data.getDirection().getY() / 10), 
			data.getVolume(), data.getDensity(), 
			data.getColor() 
		);
		
		this.objects.add(planet);
		this.addEntity(planet);
	}

	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D mousePos) {
		if (button.name() == MouseButton.PRIMARY.toString() && data.getPlacing()) {
			placePlanet(data);
			data.reset();
		}
	}

	@Override
	public void onMouseMovedWhileDragging(Coordinate2D mousePos) {
		if (data.getPlacing()) {
			data.setStopPosition(mousePos);
		} else {
			data.setStartPosition(mousePos);
			data.setPlacing(true);
		}
	}
}
