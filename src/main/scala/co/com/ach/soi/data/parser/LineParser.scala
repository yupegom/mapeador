package co.com.ach.soi.data.parser

import shapeless.{ ::, Generic, HList, HNil }

trait LineParser[T] {
  def apply(s: List[String]): Either[List[String], T]
}

object LineParser {

  implicit val hnilParser: LineParser[HNil] = new LineParser[HNil] {
    def apply(s: List[String]): Either[List[String], HNil] = Right(HNil)
  }

  implicit def hconsLineParser[H: Parser, T <: HList: LineParser]: LineParser[H :: T] = new LineParser[H :: T] {
    def apply(s: List[String]): Either[List[String], H :: T] =
      s match {
        case cell +: rest =>
          val head = implicitly[Parser[H]].apply(cell)
          val tail = implicitly[LineParser[T]].apply(rest)
          (head, tail) match {
            case (Left(error), Left(errors)) => Left(error :: errors)
            case (Left(error), Right(_)) => Left(error :: Nil)
            case (Right(_), Left(errors)) => Left(errors)
            case (Right(h), Right(t)) => Right(h :: t)
          }
        case Nil => Left(List("Se esperaban más campos para parsear el objeto"))
      }
  }

  implicit def caseClassParser[A, R <: HList](implicit
    gen: Generic[A] { type Repr = R },
    reprParser: LineParser[R]): LineParser[A] = new LineParser[A] {
    def apply(s: List[String]): Either[List[String], A] = reprParser.apply(s).right.map(gen.from)
  }

  def apply[A](s: List[String])(implicit parser: LineParser[A]): Either[List[String], A] = parser(s)

}
