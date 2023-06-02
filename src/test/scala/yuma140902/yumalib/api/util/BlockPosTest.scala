package yuma140902.yumalib.api.util

import org.scalatest.funsuite.AnyFunSuite

import net.minecraftforge.common.util.ForgeDirection

class BlockPosTest extends AnyFunSuite {

  test("testOffset") {
    assert(BlockPos(1, 2, 3).offset(ForgeDirection.UP) == BlockPos(1, 3, 3))
    assert(BlockPos(1, 2, 3).offset(ForgeDirection.NORTH, 4) == BlockPos(1, 2, -1))
    assert(BlockPos(1, 2, 3).offset(ForgeDirection.SOUTH, -4) == BlockPos(1, 2, -1))
    fail
  }

}
