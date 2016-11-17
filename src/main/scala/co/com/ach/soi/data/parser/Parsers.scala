package co.com.ach.soi.data.parser

// Convierte un string a un tipo T
import scala.util.Try

trait Parser[A] {
  def apply(s: String): Option[A]
}

object Parsers {

  implicit val stringParser: Parser[String] = new Parser[String] {
    def apply(s: String): Option[String] = Some(s)
  }

  implicit val intParser: Parser[Int] = new Parser[Int] {
    def apply(s: String): Option[Int] = Try(s.toInt).toOption
  }

  implicit val doubleParser: Parser[Double] = new Parser[Double] {
    def apply(s: String): Option[Double] = Try(s.toDouble).toOption
  }

}