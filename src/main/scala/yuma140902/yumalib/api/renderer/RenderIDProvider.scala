package yuma140902.yumalib.api.renderer

import yuma140902.yumalib.ModYumaLib

object RenderIDProvider {
  def newUniqueRenderId: Int =
    ModYumaLib.proxy.newUniqueRenderId()
}
