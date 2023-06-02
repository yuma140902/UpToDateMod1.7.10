package yuma140902.yumalib.api.update

import org.scalatest.funsuite.AnyFunSuite

class Version3Test extends AnyFunSuite {
  test("major version") {
    assert(new Version3(2, 0, 0).isLaterThan(new Version3(1, 0, 0)))
    assert(Version3.isLaterThan("2.0.0", "1.0.0"))
    assert(new Version3(2, 0, 0).isLaterThan(new Version3(1, 3, 0)))
    assert(Version3.isLaterThan("2.0.0", "1.3.0"))
    assert(new Version3(2, 0, 0).isLaterThan(new Version3(1, 0, 3)))
    assert(Version3.isLaterThan("2.0.0", "1.0.3"))
    assert(new Version3(2, 0, 0).isLaterThan(new Version3(1, 99999, 99999)))
    assert(Version3.isLaterThan("2.0.0", "1.99999.99999"))
  }

  test("minor version") {
    assert(new Version3(1, 3, 0).isLaterThan(new Version3(1, 2, 0)))
    assert(Version3.isLaterThan("1.3.0", "1.2.0"))
    assert(new Version3(1, 3, 0).isLaterThan(new Version3(1, 2, 99999)))
    assert(Version3.isLaterThan("1.3.0", "1.2.99999"))
    assert(new Version3(1, 3, 0).isLaterThan(new Version3(0, 99999, 0)))
    assert(Version3.isLaterThan("1.3.0", "0.99999.0"))
    assert(new Version3(1, 3, 0).isLaterThan(new Version3(0, 99999, 99999)))
    assert(Version3.isLaterThan("1.3.0", "0.99999.99999"))
  }

  test("patch version") {
    assert(new Version3(1, 3, 5).isLaterThan(new Version3(1, 3, 4)))
    assert(Version3.isLaterThan("1.3.5", "1.3.4"))
    assert(new Version3(1, 3, 5).isLaterThan(new Version3(1, 2, 6)))
    assert(Version3.isLaterThan("1.3.5", "1.2.6"))
    assert(new Version3(1, 3, 5).isLaterThan(new Version3(1, 2, 99999)))
    assert(Version3.isLaterThan("1.3.5", "1.2.99999"))
  }

  test("same version") {
    assert(!new Version3(1, 2, 3).isLaterThan(new Version3(1, 2, 3)))
    assert(!Version3.isLaterThan("1.2.3", "1.2.3"))
  }

  test("fromString") {
    val v = Version3.fromString("1.2.3")
    assert(v.a == 1)
    assert(v.b == 2)
    assert(v.c == 3)
  }

  test("fromString not number") {
    val v = Version3.fromString("1.2.foo")
    assert(v.a == 0)
    assert(v.b == 0)
    assert(v.c == 0)
  }

  test("fromString lack") {
    val v = Version3.fromString("1.2")
    assert(v.a == 0)
    assert(v.b == 0)
    assert(v.c == 0)
  }
}
