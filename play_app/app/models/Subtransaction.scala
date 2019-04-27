package models

/**
  * Created by dianajanik on 27.04.2019
  */

import play.api.libs.json._

case class Subtransaction(idSubtransaction: Int, idTransaction: Int, idProduct: Int, subtransactionQuantity: Int)

object Subtransaction {
  implicit val subtransactionFormat = Json.format[Subtransaction]
}

