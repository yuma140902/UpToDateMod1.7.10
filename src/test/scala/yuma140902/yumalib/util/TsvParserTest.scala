package yuma140902.yumalib.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import scala.collection.JavaConverters.mapAsScalaMapConverter

class TsvParserTest extends AnyFlatSpec {
  "getVersionTable" should "parse 1st and 2nd columns" in {
    val table = TsvParser.getVersionsTable("aaa\tbbb\nccc\tddd\n")
    table.asScala should contain theSameElementsAs Map("aaa" -> "bbb", "ccc" -> "ddd")
  }

  it should "preserve leading empty cell" in {
    val table = TsvParser.getVersionsTable("\tbbb\nccc\tddd\n")
    table.asScala should contain theSameElementsAs Map("" -> "bbb", "ccc" -> "ddd")
  }

  it should "ignore succeeding empty cells" in {
    val table1 = TsvParser.getVersionsTable("aaa\t\n")
    table1.asScala should contain theSameElementsAs Map()

    val table2 = TsvParser.getVersionsTable("\t\t\n")
    table2.asScala should contain theSameElementsAs Map()
  }

  it should "ignore 3rd and after columns" in {
    val table = TsvParser.getVersionsTable("1st\t2nd\t3rd\t4th")
    table.asScala should contain theSameElementsAs Map("1st" -> "2nd")
  }

  it should "support CR-LF" in {
    val table = TsvParser.getVersionsTable("aaa\tbbb\r\nccc\tddd\r\n")
    table.asScala should contain theSameElementsAs Map("aaa" -> "bbb", "ccc" -> "ddd")
  }

  it should "ignore rows with <2 cells" in {
    val table = TsvParser.getVersionsTable("aaa\tbbb\nthis row will be ignored\nccc\tddd\n")
    table.asScala should contain theSameElementsAs Map("aaa" -> "bbb", "ccc" -> "ddd")

    val table2 = TsvParser.getVersionsTable("aaa\tbbb\n\nccc\tddd")
    table2.asScala should contain theSameElementsAs Map("aaa" -> "bbb", "ccc" -> "ddd")
  }
}
