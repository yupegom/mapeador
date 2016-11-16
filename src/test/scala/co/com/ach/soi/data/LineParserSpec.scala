
package co.com.ach.soi.data.mapeador

import org.scalatest.{ EitherValues, Matchers, WordSpecLike }
import co.com.ach.soi.data.mapper.Parsers._
import co.com.ach.soi.data.mapper.LineParser
import org.joda.time.DateTime

class StringParserSpec extends Matchers with WordSpecLike with EitherValues {

  case class Record(value: Int, name: String, ov: Double, isPerson: Boolean)
  case class Person(name: String, isPerson: Boolean)
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

  "A String parser" should {
    /* "parsear un string" in {
      val demo = List("Hola", "false")
      val result = LineParser[Record](demo)
      result.right.value should be(Record("Hola", false))
    }
*/
    /* "pars string" in {
      val demo = List("Hola", "true")
      val result = LineParser[Person](demo)
      result.name should be("Hola")
      result.isPerson should be(true)
    }

    "pars string 1" in {
      val demo = List("1", "Hola", "1", "false")
      val result = LineParser[Record](demo)
      result.isPerson should be(false)
    }*/

    /*"pars string 2" in {
      val demo = List("1", "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "1", "1", "A", "B")
      val result = LineParser[EncabezadoEntrada2145](demo)
      result.codigoOperador should be("B")
    }*/

  }

}
