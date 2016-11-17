package co.com.ach.soi.data

import co.com.ach.soi.data.parser.LineParser
import org.scalatest.{ Matchers, WordSpecLike }
import co.com.ach.soi.data.parser.Parsers._

class FilaParseadorSpec extends Matchers with WordSpecLike {

  "A file parser " should {
    "parsear" in {
      val person = LineParser[Person](List("nombreCampoA,Amy", "nombreCampoB,54.2"))
      person.right.get.name should be("Amy")

    }

    "parsear con error debido a que un campo no es del tipo esperado" in {
      val person = LineParser[Person](List("nombreCampoA,Amy", "nombreCampoB,B"))
      person match {
        case Left(l) =>
          assert(true)
        case Right(r) => assert(false)
      }
    }

    "parsear con error, faltan campos" in {
      val person = LineParser[Person](List("nombreCampoA"))
      person match {
        case Left(l) =>
          println(l); assert(true)
        case Right(r) => assert(false)
      }
    }
    /*
    "parsear clase grande " in {
      val c = LineParser[EncabezadoEntrada2145](List("1", "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "1", "1.0", "A", "B"))
      c.right.get.codigoOperador should be("B")
    }

    "error al parsear valor inesperado" in {
      val c = LineParser[EncabezadoEntrada2145](List("1", "", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "b", "a", "A", "B"))
      c match {
        case Left(l) =>
          println(l); assert(true)
        case Right(r) => assert(false)
      }
    }*/
  }
}
