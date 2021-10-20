package com.yaeger.spacesimulator.ui.entities;

/**
 * Being a {@code ISubject} enables the implementing class to be observed by a
 * {@link IObserver} and notify its observers.
 *
 * @param <T> the type data this {@code ISubject} provides for its observers.
 */
public interface ISubject<T> {

	/**
	 * Used to start observing this {@link ISubject}.
	 * <p>
	 * In the concrete implementation of this method the given {@link IObserver}
	 * should be added to the observers of this {@link ISubject}.
	 * </p>
	 *
	 * @param observer the observer as a {@link IObserver} that should observe this
	 *                 {@link ISubject}.
	 */
	void observe(IObserver<T> observer);

	/**
	 * Used to stop observing this {@link ISubject}.
	 *
	 * <p>
	 * In the concrete implementation of this method the given {@link IObserver}
	 * should be removed from the observers of this {@link ISubject}.
	 * </p>
	 *
	 * @param observer the observer as a {@link IObserver} that should stop
	 *                 observing this {@link ISubject}.
	 */
	void unobserve(IObserver<T> observer);

	/**
	 * Used to notify the observers of this {@link ISubject}.
	 * <p>
	 * In the concrete implementation of this method
	 * {@code IObserver.update(ISubject<T> subject, T data)} should be called.
	 * </p>
	 */
	void notifyObservers();
}
