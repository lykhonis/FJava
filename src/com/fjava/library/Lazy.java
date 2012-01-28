package com.fjava.library;

public class Lazy<T> {

	private T value;
	private F1<T> f;
	private boolean called;

	public Lazy(F1<T> f) {
		this.f = f;
		this.called = false;
	}

	public T invoke() {
		if (called)
			return value;

		try {
			value = f.invoke();
		} finally {
			f = null;
			called = true;
		}

		return value;
	}
}
