package com.yaeger.spacesimulator.scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseButtonReleasedListener;
import com.github.hanyaeger.api.userinput.MouseMovedWhileDraggingListener;
import com.yaeger.spacesimulator.dto.ObjectPlacementDTO;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.services.ObjectCreationService;
import com.yaeger.spacesimulator.services.SimulationPauseService;
import com.yaeger.spacesimulator.services.SimulationUpdateService;
import com.yaeger.spacesimulator.ui.entities.PreviewObject;
import com.yaeger.spacesimulator.ui.entities.controls.PauseButton;
import com.yaeger.spacesimulator.ui.entities.controls.ResetButton;
import com.yaeger.spacesimulator.ui.entities.panels.ControlPanel;
import com.yaeger.spacesimulator.ui.entities.panels.KeyControlsPanelWrapper;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

/**
 * A {@code SimulationScene} is a concrete implementation of
 * {@link DynamicScene} and is used as the main scene of this simulation game.
 *
 */
public class SimulationScene extends DynamicScene
		implements UpdateExposer, MouseButtonReleasedListener, MouseMovedWhileDraggingListener, KeyListener {

	private SimulationUpdateService simulationUpdater = SimulationUpdateService.getInstance();
	private ArrayList<SimulationObject> simulationObjects;
	private ObjectPlacementDTO objectPlacementDto;
	private PreviewObject previewPlanet;
	private ControlPanel controlPanel;

	/**
	 * Create a new instance of {@link SimulationScene}.
	 */
	public SimulationScene() {
		this.simulationObjects = new ArrayList<SimulationObject>();
		this.objectPlacementDto = new ObjectPlacementDTO();
	}

	/**
	 * Returns the simulationObjects of this {@link SimulationScene}.
	 *
	 * @return the simulationObjects as a {@code ArrayList<T> where T instanceof}
	 *         {@link SimulationObject}.
	 */
	public ArrayList<SimulationObject> getSimulationObjects() {
		return this.simulationObjects;
	}

	/**
	 * Add a simulationObject to this {@link SimulationScene}.
	 *
	 * @param object the object as a {@link SimulationObject}.
	 */
	public void addSimulationObject(SimulationObject object) {
		this.simulationObjects.add(object);
		this.addEntity(object);
	}

	/**
	 * Used to call the methods responsible for setting up the Yaeger Scene. The
	 * following methods are available:
	 * <ul>
	 * <li>setBackgroundImage(String)</li>
	 * <li>setBackgroundImage(String, boolean)</li>
	 * <li>setBackgroundAudio(String)</li>
	 * <li>setBackgroundAudioVolume(double)</li>
	 * <li>getBackgroundAudioVolume()</li>
	 * </ul>
	 *
	 */
	@Override
	public void setupScene() {
		setBackgroundColor(Color.BLACK);
	}

	/**
	 * Used to setup all instances of YaegerEntity that should be added to this
	 * {@link SimulationScene} before activation.
	 */
	@Override
	public void setupEntities() {
		controlPanel = new ControlPanel(new Coordinate2D(20, getHeight() - 20), new Size(220, 350));
		controlPanel.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
		controlPanel.observeDensityValueControl(objectPlacementDto);
		controlPanel.observeVolumeValueControl(objectPlacementDto);
		addEntity(controlPanel);

		PauseButton pauseButton = new PauseButton(new Coordinate2D(20, 20), new Size(32, 32));
		addEntity(pauseButton);

		SimulationPauseService.initializeInstance(this, pauseButton);
		ObjectCreationService.initializeInstance(this);

		KeyControlsPanelWrapper keyControlsPanelWrapper = new KeyControlsPanelWrapper(
				new Coordinate2D(getWidth() - 20, getHeight() - 20));
		addEntity(keyControlsPanelWrapper);

		ResetButton resetButton = new ResetButton(new Coordinate2D(getWidth() - 210, getHeight() - 20),
				new Size(100, 30), this);
		resetButton.setAnchorPoint(AnchorPoint.BOTTOM_RIGHT);
		addEntity(resetButton);
	}

	@Override
	public void postActivate() {
		super.postActivate();
		SimulationPauseService.getInstance().setPaused(true);
	}

	/**
	 * Used to trigger behaviour that should be set after the
	 * {@link SimulationScene} has been completely set up.
	 */
	@Override
	public void explicitUpdate(long timestamp) {
		simulationUpdater.updateSimulation(simulationObjects);
	}

	@Override
	public void onMouseButtonReleased(MouseButton button, Coordinate2D mousePos) {
		if (!locationIsInControlPanel(mousePos))
			if (button.name() == MouseButton.PRIMARY.toString() && this.objectPlacementDto.getPlacing()) {
				if (this.simulationObjects.size() == 0)
					ObjectCreationService.getInstance().addCentrePlanet(this.objectPlacementDto);
				else
					ObjectCreationService.getInstance().addPlanet(this.objectPlacementDto);
				this.objectPlacementDto.setPlacing(false);
				previewPlanet.remove();
				objectPlacementDto.reset();
			}
	}

	@Override
	public void onMouseMovedWhileDragging(Coordinate2D mousePos) {
		if (!locationIsInControlPanel(mousePos))
			if (objectPlacementDto.getPlacing()) {
				objectPlacementDto.setStopPosition(mousePos);
			} else {
				objectPlacementDto.setStartPosition(mousePos);
				objectPlacementDto.setPlacing(true);
				previewPlanet = new PreviewObject(new Coordinate2D(),
						objectPlacementDto.getVolume() / objectPlacementDto.getDensity(),
						objectPlacementDto.getColor());
				this.addEntity(previewPlanet);
				previewPlanet.setAnchorLocation(mousePos);
			}
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.P))
			SimulationPauseService.getInstance().togglePause();
		else if (pressedKeys.contains(KeyCode.R))
			resetSimulation();
	}

	/**
	 * Used to reset this {@link SimulationScene}, which will remove all
	 * simulationObjects.
	 */
	public void resetSimulation() {
		for (Iterator<SimulationObject> iterator = simulationObjects.iterator(); iterator.hasNext();) {
			SimulationObject o = iterator.next();
			o.remove();
			iterator.remove();
		}
	}

	/**
	 * Used to check if the location of this {@link Coordinate2D} is inside the
	 * boundaries of the controlPanel that belongs to this {@link SimulationScene}.
	 *
	 * @param pos the position as a {@link Coordinate2D}
	 * @return the result as a {@code boolean}.
	 */
	private boolean locationIsInControlPanel(Coordinate2D pos) {
		if (pos.getX() > controlPanel.getAnchorLocation().getX()
				&& pos.getX() < controlPanel.getAnchorLocation().getX() + controlPanel.getWidth()
				&& pos.getY() > controlPanel.getAnchorLocation().getY() - controlPanel.getHeight()
				&& pos.getY() < controlPanel.getAnchorLocation().getY())
			return true;
		return false;
	}

}
