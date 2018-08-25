package yuma140902.uptodatemod.util;

/**
 * ただのタプルです
 * @author yuma1
 *
 * @param <T>
 * @param <S>
 */
public class Pair<T, S> {
	public Pair() {
		
	}
	
	public Pair(T t, S s){
		_left = t;
		_right = s;
	}
	
	private T _left;
	private S _right;
	
	public T getLeft() {
		return _left;
	}
	public S getRight() {
		return _right;
	}
	
	public void setLeft(T t) {
		_left = t;
	}
	
	public void setRight(S s) {
		_right = s;
	}
}
