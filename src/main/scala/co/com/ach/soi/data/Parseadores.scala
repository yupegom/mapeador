package co.com.ach.soi.data

import shapeless.{ ::, Generic, HList, HNil }

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

  implicit val hnilParser: Parseador[HNil] = new Parseador[HNil] {
    def apply(s: String): Option[HNil] = if (s.isEmpty) Some(HNil) else None
  }

  implicit def hconsParseador[H: Parseador, T <: HList: Parseador]: Parseador[H :: T] = new Parseador[H :: T] {
    def apply(s: String): Option[H :: T] = s.split(",").toList match {
      case cell +: rest => for {
        head <- implicitly[Parseador[H]].apply(cell)
        tail <- implicitly[Parseador[T]].apply(rest.mkString(","))
      } yield head :: tail
    }
  }

  implicit def caseClassParser[A, R <: HList](implicit
    gen: Generic[A] { type Repr = R },
    reprParser: Parseador[R]): Parseador[A] = new Parseador[A] {
    def apply(s: String): Option[A] = reprParser.apply(s).map(gen.from)
  }

  def apply[A](s: String)(implicit parser: Parseador[A]): Option[A] = parser(s)

}