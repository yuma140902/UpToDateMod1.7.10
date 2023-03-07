package yuma140902.uptodatemod.util

import yuma140902.yumalib.api.McConst._
import yuma140902.yumalib.api.util.BlockPos;

object DirectionUtil {
  def getBack(side: Int): Int = side match {
    case SIDE_NORTH => SIDE_NORTH
    case SIDE_SOUTH => SIDE_NORTH
    case SIDE_WEST => SIDE_EAST
    case SIDE_EAST => SIDE_WEST
    case SIDE_TOP => SIDE_BOTTOM
    case SIDE_BOTTOM => SIDE_TOP
    case _ => 0
  }

  def getRightSide(side: Int): Int = side match {
    case SIDE_NORTH => SIDE_EAST
    case SIDE_EAST => SIDE_SOUTH
    case SIDE_SOUTH => SIDE_WEST
    case SIDE_WEST => SIDE_NORTH
    case _ => 0
  }

  def getLeftSide(side: Int): Int = side match {
    case SIDE_NORTH => return SIDE_WEST
    case SIDE_WEST => return SIDE_SOUTH
    case SIDE_SOUTH => return SIDE_EAST
    case SIDE_EAST => return SIDE_NORTH
    case _ => return 0
  }

  val rightSides: Array[Int] = Array[Int](SIDE_NORTH, SIDE_SOUTH, SIDE_EAST, SIDE_WEST, SIDE_NORTH, SIDE_SOUTH)

  val leftSides: Array[Int] = Array[Int](SIDE_SOUTH, SIDE_NORTH, SIDE_WEST, SIDE_EAST, SIDE_SOUTH, SIDE_NORTH)

  //座標(x, y, z)のブロックのside側の隣の座標を返します
  def getCoordBySide(side: Int, x: Int, y: Int, z: Int): BlockPos = side match {
    case SIDE_BOTTOM => new BlockPos(x, y - 1, z)
    case SIDE_TOP => new BlockPos(x, y + 1, z)
    case SIDE_NORTH => new BlockPos(x, y, z - 1)
    case SIDE_SOUTH => new BlockPos(x, y, z + 1)
    case SIDE_WEST => new BlockPos(x - 1, y, z)
    case SIDE_EAST => new BlockPos(x + 1, y, z)
    case _ => new BlockPos(0, 0, 0)
  }
}
