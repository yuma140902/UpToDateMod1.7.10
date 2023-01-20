package yuma140902.yumalib.api;

/**
 * レシピを持っていることを表現するインターフェース。BlockまたはItemに実装する
 */
public interface IHasRecipes {
	/** レシピをゲームに登録する */
	void registerRecipes();
}
