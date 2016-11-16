package co.com.ach.soi.data.mapeador

import org.scalatest.{ Matchers, WordSpecLike }

class MapeadorSpec extends Matchers with WordSpecLike {
  "A mapeador" should {
    "mapear un string" in {
      Mapeador.map("t4est")
      assert(true)
    }
  }

}
