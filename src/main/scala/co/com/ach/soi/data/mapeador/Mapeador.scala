package co.com.ach.soi.data.mapeador

object Mapeador {

  def map(value: String) {
    println("is this working??")
  }
}

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
