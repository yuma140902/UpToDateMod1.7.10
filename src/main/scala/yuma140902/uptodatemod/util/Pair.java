package yuma140902.uptodatemod.util;

/**
 * ただのタプルです
 *
 * @param <T>
 * @param <S>
 * @author yuma1
 * @deprecated use scala instead
 */
@Deprecated
public class Pair<T, S> {
	private T _left;
	private S _right;

	public Pair() {

	}
	public Pair(T t, S s) {
		_left = t;
		_right = s;
	}

	public T getLeft() {
		return _left;
	}

	public void setLeft(T t) {
		_left = t;
	}

	public S getRight() {
		return _right;
	}

	public void setRight(S s) {
		_right = s;
	}
}
