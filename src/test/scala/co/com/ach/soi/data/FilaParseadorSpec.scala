package co.com.ach.soi.data

import co.com.ach.soi.data.parser.LineParser
import org.scalatest.{ Matchers, WordSpecLike }
import co.com.ach.soi.data.parser.Parsers._

class FilaParseadorSpec extends Matchers with WordSpecLike {

  "A file parser " should {
    "parsear" in {
      val person = LineParser[Person](List("Amy", "54.2"))
      person.get.name should be("Amy")

    }

    "parsear clase grande " in {
      val c = LineParser[EncabezadoEntrada2145](List("1", "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "1", "1.0", "A", "B"))
      c.get.codigoOperador should be("B")
    }
  }
}
