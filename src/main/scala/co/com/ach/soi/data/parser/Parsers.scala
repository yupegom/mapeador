package co.com.ach.soi.data.parser

// Convierte un string a un tipo T
import scala.util.{ Success, Try }

case class ThingToParse(descripcion: ThingToParse.Descripcion, value: ThingToParse.Value)

object ThingToParse {
  case class Descripcion(value: String)
  case class Value(value: String)
}

trait Parser[T] {
  def apply(s: ThingToParse): Either[String, T]
}

trait TryParser[T] extends Parser[T] {

  def parse(s: ThingToParse): T

  def apply(s: ThingToParse): Either[String, T] = {
    Try(parse(s)).transform(
      s => Success(Right(s)),
      f => { Success(Left(s"No logré realizar el parseo para el campo: ${s.descripcion.value}")) }
    ).get
  }

}
object Parsers {

  implicit val stringParser: Parser[String] = new TryParser[String] {
    def parse(s: ThingToParse): String = s.value.value
  }

  implicit val intParser: Parser[Int] = new TryParser[Int] {
    def parse(s: ThingToParse): Int = s.value.value.toInt
  }

  implicit val doubleParser: Parser[Double] = new TryParser[Double] {
    def parse(s: ThingToParse): Double = s.value.value.toDouble
  }

}
