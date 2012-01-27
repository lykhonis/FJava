package com.fjava.library;

public class Lazy<T> {

	private T value;
	private F1<T> f;

	public Lazy(F1<T> f) {
		this.f = f;
	}

	public T invoke() {
		if (value == null) {
			value = f.invoke();
			f = null;
		}
		return value;
	}
}
