package models

/**
  * Created by dianajanik on 27.04.2019
  */

import java.sql.Timestamp

import play.api.libs.json._

case class Transaction(idTransaction: Int, idUser: Int, transactionDate: Timestamp )

object Transaction {
  implicit val transactionFormat = Json.format[Transaction]
}
