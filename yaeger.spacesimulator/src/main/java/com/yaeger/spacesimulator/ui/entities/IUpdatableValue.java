package com.yaeger.spacesimulator.ui.entities;

/**
 * Being a {@code IUpdatableValue<T>} enables the implementing class to set a
 * value of the specified type.
 *
 * @param <T> the type this {@code IUpdatableValue} should be able to update.
 */
public interface IUpdatableValue<T> {

	/**
	 * Used to set a value for the given value.
	 *
	 * @param value the value as a type this {@code IUpdatableValue} is able to
	 *              update.
	 */
	public void setValue(T value);
}
