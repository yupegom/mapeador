package co.com.ach.soi.data

import shapeless.{ ::, Generic, HList, HNil }

trait LineaParseador[T] {
  def apply(s: String): Option[T]
}

object LineaParseador {

  implicit val hnilParser: LineaParseador[HNil] = new LineaParseador[HNil] {
    def apply(s: String): Option[HNil] = if (s.isEmpty) Some(HNil) else None
  }

  implicit def hconsLineaParseador[H: Parseador, T <: HList: LineaParseador]: LineaParseador[H :: T] = new LineaParseador[H :: T] {
    def apply(s: String): Option[H :: T] = s.split(",").toList match {
      case cell +: rest => for {
        head <- implicitly[Parseador[H]].apply(cell)
        tail <- implicitly[LineaParseador[T]].apply(rest.mkString(","))
      } yield head :: tail
    }
  }

  implicit def caseClassParser[A, R <: HList](implicit
    gen: Generic[A] { type Repr = R },
    reprParser: LineaParseador[R]): LineaParseador[A] = new LineaParseador[A] {
    def apply(s: String): Option[A] = reprParser.apply(s).map(gen.from)
  }

  def apply[A](s: String)(implicit parser: LineaParseador[A]): Option[A] = parser(s)

}
