package models

/**
  * Created by dianajanik on 27.04.2019
  */

import play.api.libs.json._

case class Stock(idStock: Int, idProduct: Int, quantity: Int)

object Stock {
  implicit val stockFormat = Json.format[Stock]
}
