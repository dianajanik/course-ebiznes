package models

/**
  * Created by dianajanik on 27.04.2019
  */

import java.sql.Timestamp

import play.api.libs.json._
import java.text.SimpleDateFormat

case class Transaction(idTransaction: Int, idUser: Int, transactionDate: Timestamp )

object Transaction extends ((Int, Int, Timestamp)=>Transaction){

  implicit object tsFormat extends Format[Timestamp]{
    val format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    def reads(json:JsValue) = {
      val str = json.as[String]
      JsSuccess(new Timestamp(format.parse(str).getTime))
    }
    val fWrite = new SimpleDateFormat("HH:mm:ss")
    def writes(ts: Timestamp) = JsString(fWrite.format(ts))
  }
  implicit val transactionFormat = Json.format[Transaction]
}
