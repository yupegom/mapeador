package co.com.ach.soi.data

import scala.util.Try

trait SaferRowParser[A] {
  def apply(s: String): Option[A]
}

object SafeRowParser {
  implicit val personParser: SaferRowParser[Person] = new SaferRowParser[Person] {
    def apply(s: String): Option[Person] = s.split(",").toList match {
      case List(name, age) => Try(age.toDouble).map(Person(name, _)).toOption
      case _ => None
    }
  }

  implicit val bookParser: SaferRowParser[Book] = new SaferRowParser[Book] {
    def apply(s: String): Option[Book] = s.split(",").toList match {
      case List(title, author, year) =>
        Try(year.toInt).map(Book(title, author, _)).toOption
      case _ => None
    }
  }

  def apply[A](s: String)(implicit parser: SaferRowParser[A]): Option[A] = parser(s)
}