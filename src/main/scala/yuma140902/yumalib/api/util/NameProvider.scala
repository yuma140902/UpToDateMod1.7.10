package yuma140902.yumalib.api.util

/**
 * ドメインなしの文字列を受け取りドメイン付きの文字列を返す
 */
case class NameProvider(textureDomain: String, unlocalizedDomain: String) {
  def domainedUnlocalized(name: String): String = unlocalizedDomain + "." + name;

  def domainedTextures(names: Name*): Seq[NameWithModID] = names.map(domainedTexture)

  def domainedTexture(name: Name): NameWithModID = NameWithModID(textureDomain + ":" + name.str)

  @deprecated("", "2.4.1")
  def domainedTextures_(names: String*): Seq[String] = names.map(domainedTexture)

  @deprecated("", "2.4.1")
  def domainedTexture(name: String): String = textureDomain + ":" + name

}
