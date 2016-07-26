package org.gosu_lang.demo

uses org.junit.Test

class SpaceMarineUnitTest {

  final var captainTitus = new SpaceMarine()

  @Test
  function sayHi() {
    print(captainTitus.purgeItInFlame(5))
  }

}