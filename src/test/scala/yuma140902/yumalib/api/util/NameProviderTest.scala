package yuma140902.yumalib.api.util

import org.scalatest.funsuite.AnyFunSuite

class NameProviderTest extends AnyFunSuite {

  test("testDomainedTextures") {
    val name = NameProvider("tex", "lang")
    assert(name.domainedTextures(Name("foo"), Name("bar_123"), Name("baz456")) ==
      Seq(NameWithModID("tex:foo"), NameWithModID("tex:bar_123"), NameWithModID("tex:baz456")))
  }

  test("testDomainedTextures2") {
    val name = NameProvider("tex", "lang")
    assert(name.domainedTextures(Name("")) == Seq(NameWithModID("tex:")))
  }

  test("testDomainedUnlocalized") {
    val name = NameProvider("tex", "lang")
    assert(name.domainedUnlocalized("message") == "lang.message")
    assert(name.domainedUnlocalized("foo.bar") == "lang.foo.bar")
  }

  test("testDomainedUnlocalized2") {
    val name = NameProvider("tex", "lang")
    assert(name.domainedUnlocalized("") == "lang.")
  }

  test("testDomainedTexture") {
    val name = NameProvider("tex", "lang")
    assert(name.domainedTexture(Name("foo")) == NameWithModID("tex:foo"))
  }

}
