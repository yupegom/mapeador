package co.com.ach.soi.data.mapper

// Convierte un string a un tipo T
trait Parser[T] {
  def apply(s: String): T
}

object Parsers {

  implicit val stringParser: Parser[String] = new Parser[String] {
    def apply(s: String): String = s
  }

  implicit val booleanParser: Parser[Boolean] = new Parser[Boolean] {
    def apply(s: String): Boolean = s.toBoolean
  }

  implicit val intParser: Parser[Int] = new Parser[Int] {
    def apply(s: String): Int = s.toInt
  }

  implicit val doubleParser: Parser[Double] = new Parser[Double] {
    def apply(s: String): Double = s.toDouble
  }

}
