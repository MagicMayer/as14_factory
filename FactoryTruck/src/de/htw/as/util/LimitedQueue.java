package de.htw.as.util;

import java.util.ArrayList;

public class LimitedQueue<E> extends ArrayList<E> {

	private int capacity;

	public LimitedQueue(int capacity) {
		super(capacity);
		this.capacity = capacity;
	}

	@Override
	public void add(int index, E element) {
		removeFirstIfNecessary();
		super.add(index, element);
	}

	@Override
	public boolean add(E element) {
		removeFirstIfNecessary();
		return super.add(element);
	};


	public int getCapacity() {
		return capacity;
	}

	private void removeFirstIfNecessary() {
		if (!(size() < capacity) && !isEmpty()) {
			remove(0);
		}
	}
}
