package com.yaeger.spacesimulator.ui.entities;

/**
 * Being a {@code IObserver} enables the implementing class to be updated when a
 * {@link ISubject} this {@code IObserver} is subscribed to notifies its
 * observers.
 *
 * @param <T> the type this {@code IObserver} observes.
 */
public interface IObserver<T> {

	/**
	 * Used to update this {@code IObserver} for the given {@link ISubject} and
	 * {@code data}.
	 * <p>
	 * When implementing this method the given {@code data} should be used to update
	 * this {@link IObserver}.
	 * </p>
	 * <p>
	 * This method should be called by {@link ISubject.notifyObservers()}
	 * </p>
	 *
	 * @param subject the subject as a {@link ISubject} this observer observes.
	 * @param data    the data as the type this {@code IObserver} observes.
	 */
	void update(ISubject<T> subject, T data);
}
