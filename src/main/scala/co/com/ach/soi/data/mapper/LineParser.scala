package co.com.ach.soi.data.mapper

import shapeless.{ ::, Generic, HList, HNil }

trait LineParser[T] {
  def apply(l: List[String]): T
}

object LineParser {

  implicit val hnilParser: LineParser[HNil] = instance[HNil] { l => HNil }

  implicit def hconsParser[H, T <: HList: LineParser](implicit hParser: Parser[H], tParser: LineParser[T]): LineParser[H :: T] = {
    instance[H :: T] {
      case h +: t => hParser(h) :: tParser(t)

    }
  }

  implicit def caseClassParser[T, R <: HList](implicit gen: Generic.Aux[T, R], parsers: LineParser[R]): LineParser[T] =
    instance {
      l => gen.from(parsers(l))
    }

  def instance[T](fun: List[String] => T): LineParser[T] = {
    new LineParser[T] {
      def apply(l: List[String]): T = fun(l)
    }
  }

  def apply[T](l: List[String])(implicit parser: LineParser[T]): T = {
    parser(l)
  }
}
