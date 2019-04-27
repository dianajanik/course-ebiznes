package models

/**
  * Created by dianajanik on 27.04.2019
  */

import play.api.libs.json._

case class Product(idProduct: Int, productCategory: Int, productPrice: Int, productName: String, productDescription: String, productPhoto: String, productNotSaled: Boolean)

object Product {
  implicit val productFormat = Json.format[Product]
}

