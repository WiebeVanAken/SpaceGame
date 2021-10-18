package com.yaeger.spacesimulator;

import com.github.hanyaeger.api.Timer;
import com.yaeger.spacesimulator.entities.SimulationObject;
import com.yaeger.spacesimulator.scenes.SimulationScene;
import com.yaeger.spacesimulator.services.SimulationPauseService;

/**
 * <p>
 * An {@code OutOfBoundsTimer} is used to detect if a {@link SimulationObject}
 * should be deleted. When this is detected it notifies the
 * {@link SimulationObject} this {@code OutOfBoundsTimer} belongs to.
 * </p>
 * <p>
 * When a {@link SimulationObject} crosses from inside to outside the boundary
 * of the {@link SimulationScene}, {@link #start()} should be called to start
 * the timer.
 * </p>
 * <p>
 * When a {@link SimulationObject} crosses from outside to inside the boundary
 * of the {@link SimulationScene}, {@link #stop()} should be called to stop the
 * timer.
 * </p>
 */
public class OutOfBoundsTimer extends Timer {
	private SimulationObject simulationObject;
	private int counter;
	boolean running;

	/**
	 * Create a new instance of {@link OutOfBoundsTimer} for the given
	 * {@link SimulationObject} and interval in milliseconds.
	 *
	 * @param simulationObject the {@link SimulationObject} this
	 *                         {@code OutOfBoundsTimer} belongs to.
	 * @param intervalInMs     the interval in milliseconds.
	 */
	public OutOfBoundsTimer(SimulationObject simulationObject, long intervalInMs) {
		super(intervalInMs);
		this.simulationObject = simulationObject;
		this.counter = 0;
		this.running = false;
	}

	/**
	 * <p>
	 * Start the {@code OutOfboundTimer} so it will start updating on each animation
	 * again.
	 * </p>
	 * <p>
	 * Call this method when the {@link SimulationObject} crosses from inside to
	 * outside the boundary of the {@link SimulationScene}.
	 * </p>
	 */
	public void start() {
		if (!this.running) {
			this.running = true;
			this.counter = 0;
			this.resume();
		}
	}

	/**
	 * <p>
	 * Stop the {@code OutOfboundTimer} so it will no longer update with each
	 * animation.
	 * </p>
	 * <p>
	 * Call this method when the {@link SimulationObject} crosses from outside to
	 * inside the boundary of the {@link SimulationScene}.
	 * </p>
	 */
	public void stop() {
		this.running = false;
		this.counter = 0;
		this.pause();
	}

	/**
	 * <p>
	 * This method will be called each animation update with the given interval.
	 * </p>
	 * <p>
	 * It updates the time the {@link SimulationObject} this {@code OutOfboundTimer}
	 * belongs to spends outside of the boundary of the {@link SimulationScene}.
	 * When detecting that this {@link SimulationObject} should be deleted since it
	 * is outside of the boundary for too long, it will notify the
	 * {@link SimulationObject}.
	 * </p>
	 */
	@Override
	public void onAnimationUpdate(long timestamp) {
		if (!SimulationPauseService.getInstance().getPaused()) {
			this.counter++;

			if (counter >= 60)
				this.simulationObject.setShouldBeDeleted(true);
		}
	}
}
