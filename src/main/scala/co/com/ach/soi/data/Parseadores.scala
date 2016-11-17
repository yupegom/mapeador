package co.com.ach.soi.data

import scala.util.Try

trait Parseador[A] {
  def apply(s: String): Option[A]
}

object Parseadores {

  implicit val stringParser: Parseador[String] = new Parseador[String] {
    def apply(s: String): Option[String] = Some(s)
  }

  implicit val intParseador: Parseador[Int] = new Parseador[Int] {
    def apply(s: String): Option[Int] = Try(s.toInt).toOption
  }

  implicit val doubleParseador: Parseador[Double] = new Parseador[Double] {
    def apply(s: String): Option[Double] = Try(s.toDouble).toOption
  }

}