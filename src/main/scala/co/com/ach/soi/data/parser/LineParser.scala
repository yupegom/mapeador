package co.com.ach.soi.data.parser

import shapeless.{ ::, Generic, HList, HNil }

trait LineParser[T] {
  def apply(s: List[String]): Option[T]
}

object LineParser {

  implicit val hnilParser: LineParser[HNil] = new LineParser[HNil] {
    def apply(s: List[String]): Option[HNil] = if (s.isEmpty) Some(HNil) else None
  }

  implicit def hconsLineParser[H: Parser, T <: HList: LineParser]: LineParser[H :: T] = new LineParser[H :: T] {
    def apply(s: List[String]): Option[H :: T] =
      s match {
        case cell +: rest =>
          for {
            head <- implicitly[Parser[H]].apply(cell)
            tail <- implicitly[LineParser[T]].apply(rest)
          } yield head :: tail
      }
  }

  implicit def caseClassParser[A, R <: HList](implicit
    gen: Generic[A] { type Repr = R },
    reprParser: LineParser[R]): LineParser[A] = new LineParser[A] {
    def apply(s: List[String]): Option[A] = reprParser.apply(s).map(gen.from)
  }

  def apply[A](s: List[String])(implicit parser: LineParser[A]): Option[A] = parser(s)

}