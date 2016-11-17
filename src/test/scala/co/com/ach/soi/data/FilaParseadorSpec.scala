package co.com.ach.soi.data

import org.scalatest.{ Matchers, WordSpecLike }
import co.com.ach.soi.data.Parseadores._

class FilaParseadorSpec extends Matchers with WordSpecLike {

  "A file parser " should {
    "parsear" in {
      val person = LineaParseador[Person]("Amy, 54.2")
      person.get.name should be("Amy")

    }

    "parsear clase grande " in {
      val c = LineaParseador[EncabezadoEntrada2145]("1,2,3,3,3,3,3,3,3,3,3,3,3,1,1.0,A,B")
      c.get.codigoOperador should be("B")
    }
  }
}
