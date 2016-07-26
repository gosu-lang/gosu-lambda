package org.gosu_lang.demo

class SpaceMarine {

  private static final var PURGE_IT_IN_FLAME = "Purge it in gosu flame...\n";

  function purgeItInFlame( depthToPurge: int ): String{
    return (depthToPurge == 0 ? "" : purgeItInFlame( depthToPurge - 1 )) + indent(depthToPurge) + PURGE_IT_IN_FLAME;
  }

  function indent( level : int ) : String {
    var sb = new StringBuilder()
    while( level > 0) {
      sb.append( "  " )
      level--
    }
    return sb.toString()
  }
}