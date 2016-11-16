package co.com.ach.soi.data

case class Person(name: String, age: Double)
case class Book(title: String, author: String, year: Int)
case class Country(name: String, population: Int, area: Double)
case class EncabezadoEntrada2145(
  tipoRegistro: String,
  secuencia: String,
  nombrePagador: String,
  tipoDocumentoPagador: String,
  identificacionPagador: String,
  digitoVerificacion: String,
  formaPresentacion: String,
  codigoSucursal: String,
  nombreSucursal: String,
  periodoPagoGeneral: String,
  periodoPagoSalud: String,
  numeroRadicacion: String,
  fechaPago: String,
  totalPensionados: Int,
  totalNomina: Double,
  tipoPagador: String,
  codigoOperador: String
)

trait RowParser[A] {
  def apply(s: String): A
}

object RowParser {
  val personParser: RowParser[Person] = new RowParser[Person] {
    def apply(s: String): Person = s.split(",").toList match {
      case List(name, age) => Person(name, age.toDouble)
    }
  }

  val bookParser: RowParser[Book] = new RowParser[Book] {
    def apply(s: String): Book = s.split(",").toList match {
      case List(title, author, year) => Book(title, author, year.toInt)
    }
  }
}