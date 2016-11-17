package co.com.ach.soi.data.parser

// Convierte un string a un tipo T
import scala.util.{ Success, Try }

trait Parser[T] {
  def apply(s: String): Either[String, T]
}

trait TryParser[T] extends Parser[T] {

  def parse(s: String): T

  def apply(s: String): Either[String, T] = Try(parse(s)).transform(
    s => Success(Right(s)),
    f => Success(Left(s"No logré realizar el parseo para el campo $s"))
  ).get

}
object Parsers {

  implicit val stringParser: Parser[String] = new TryParser[String] {
    def parse(s: String): String = s
  }

  implicit val intParser: Parser[Int] = new TryParser[Int] {
    def parse(s: String): Int = s.toInt
  }

  implicit val doubleParser: Parser[Double] = new TryParser[Double] {
    def parse(s: String): Double = s.toDouble
  }

}
