package yuma140902.uptodatemod.util

import net.minecraft.block.material.MapColor

object ColorUtil {
  val META_WHITE = 0
  val META_ORANGE = 1
  val META_MAGENTA = 2
  val META_LIGHT_BLUE = 3
  val META_YELLOW = 4
  val META_LIME = 5
  val META_PINK = 6
  val META_GRAY = 7
  val META_LIGHT_GRAY = 8
  val META_CYAN = 9
  val META_PURPLE = 10
  val META_BLUE = 11
  val META_BROWN = 12
  val META_GREEN = 13
  val META_RED = 14
  val META_BLACK = 15

  def metaToString(meta: Int): String = meta match {
    case META_BLACK => "black"
    case META_BLUE => "blue"
    case META_BROWN => "brown"
    case META_CYAN => "cyan"
    case META_GRAY => "gray"
    case META_GREEN => "green"
    case META_LIGHT_BLUE => "light_blue"
    case META_LIME => "lime"
    case META_MAGENTA => "magenta"
    case META_ORANGE => "orange"
    case META_PINK => "pink"
    case META_PURPLE => "purple"
    case META_RED => "red"
    case META_LIGHT_GRAY => "light_gray"
    case META_WHITE => "white"
    case META_YELLOW => "yellow"
    case _ => "unknown_color"
  }

  def metaToDyeName(meta: Int): String = meta match {
    case META_BLACK => "Black"
    case META_BLUE => "Blue"
    case META_BROWN => "Brown"
    case META_CYAN => "Cyan"
    case META_GRAY => "Gray"
    case META_GREEN => "Green"
    case META_LIGHT_BLUE => "LightBlue"
    case META_LIME => "Lime"
    case META_MAGENTA => "Magenta"
    case META_ORANGE => "Orange"
    case META_PINK => "Pink"
    case META_PURPLE => "Purple"
    case META_RED => "Red"
    case META_LIGHT_GRAY => "LightGray"
    case META_WHITE => "White"
    case META_YELLOW => "Yellow"
    case _ => "UnknownColor"
  }

  def metaToMapColor(meta: Int): MapColor = meta match {
    case META_BLACK => MapColor.blackColor
    case META_BLUE => MapColor.blueColor
    case META_BROWN => MapColor.brownColor
    case META_CYAN => MapColor.cyanColor
    case META_GRAY => MapColor.grayColor
    case META_GREEN => MapColor.greenColor
    case META_LIGHT_BLUE => MapColor.lightBlueColor
    case META_LIME => MapColor.limeColor
    case META_MAGENTA => MapColor.magentaColor
    case META_ORANGE => MapColor.adobeColor
    case META_PINK => MapColor.pinkColor
    case META_PURPLE => MapColor.purpleColor
    case META_RED => MapColor.redColor
    case META_LIGHT_GRAY => MapColor.ironColor
    case META_WHITE => MapColor.snowColor
    case META_YELLOW => MapColor.yellowColor
    case _ => MapColor.airColor
  }
}
