package yuma140902.yumalib

import yuma140902.yumalib.blocks.BlockTest

object YLBlocks {
  def registerAll(): Unit = {
    BlockTest.register()
  }
}
