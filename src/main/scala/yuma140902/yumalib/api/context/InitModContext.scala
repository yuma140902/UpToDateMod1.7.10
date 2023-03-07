package yuma140902.yumalib.api.context

import yuma140902.yumalib.api.util.NameProvider

/**
 * Mod初期化時の文脈(YumaLibのAPIを呼び出しているのはどのModかなどの情報)を表す。<br>
 * これにより、いちいちメソッドにMod名などを渡す手間が省ける。
 */
case class InitModContext(
                           /**
                            * YumaLibのAPIを呼び出しているModのID。YumaLibに依存するModのことをAddonModと呼んでいる
                            */
                           addonModName: String,
                           nameProvider: NameProvider)
