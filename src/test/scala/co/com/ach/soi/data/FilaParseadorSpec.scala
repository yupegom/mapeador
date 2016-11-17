package co.com.ach.soi.data

import co.com.ach.soi.data.parser.{ LineParser, ThingToParse }
import org.scalatest.{ Matchers, WordSpecLike }
import co.com.ach.soi.data.parser.Parsers._
import co.com.ach.soi.data.parser.ThingToParse.{ Descripcion, Value }

class FilaParseadorSpec extends Matchers with WordSpecLike {

  "A file parser " should {
    "parsear" in {
      val person = LineParser[Person](List(ThingToParse(Descripcion("nombre"), Value("Amy")), ThingToParse(Descripcion("edad"), Value("54.0"))))
      person.right.get.name should be("Amy")

    }

    "parsear con error debido a que un campo no es del tipo esperado" in {
      val person = LineParser[Person](List(ThingToParse(Descripcion("nombre"), Value("Amy")), ThingToParse(Descripcion("edad"), Value("hola"))))
      person match {
        case Left(l) =>
          l(0) contains ("No logré realizar el parseo")
        case Right(r) => assert(false)
      }
    }

    "parsear con error, faltan campos" in {
      val person = LineParser[Person](List(ThingToParse(Descripcion("nombre"), Value("Amy"))))
      person match {
        case Left(l) =>
          assert(true)
        case Right(r) => assert(false)
      }
    }

    "parsear clase grande " in {
      val thingsToParse = List(ThingToParse(Descripcion("nombre"), Value("2")), ThingToParse(
        Descripcion("nombre"),
        Value("2")
      ), ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("1")), ThingToParse(Descripcion("nombre"), Value("1.0")), ThingToParse(Descripcion("nombre"), Value("A")),
        ThingToParse(Descripcion("nombre"), Value("B")))
      val c = LineParser[EncabezadoEntrada2145](thingsToParse)
      c.right.get.codigoOperador should be("B")
    }

    "error al parsear valor inesperado" in {
      val thingsToParse = List(ThingToParse(Descripcion("nombre"), Value("2")), ThingToParse(
        Descripcion("nombre"),
        Value("2")
      ), ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")), ThingToParse(Descripcion("nombre"), Value("3")),
        ThingToParse(Descripcion("nombre"), Value("1")), ThingToParse(Descripcion("nombre"), Value("hola")), ThingToParse(Descripcion("nombre"), Value("A")),
        ThingToParse(Descripcion("nombre"), Value("B")))
      val c = LineParser[EncabezadoEntrada2145](thingsToParse)
      c match {
        case Left(l) =>
          l(0) contains ("No logré realizar el parseo")
        case Right(r) => assert(false)
      }
    }
  }
}
