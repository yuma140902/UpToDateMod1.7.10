package yuma140902.yumalib.api.util

import org.scalatest.funsuite.AnyFunSuite

class StringUtilsTest extends AnyFunSuite {

  test("testPrefix") {
    assert(StringUtils.prefix("foo", "_") == "foo_")
    assert(StringUtils.prefix("foo_bar", "_") == "foo_bar_")
    assert(StringUtils.prefix("", "_") == "")
  }

  test("testSuffix") {
    assert(StringUtils.suffix("::", "foo") == "::foo")
    assert(StringUtils.suffix("::", "foo::bar") == "::foo::bar")
    assert(StringUtils.suffix("::", "") == "")
  }

}
