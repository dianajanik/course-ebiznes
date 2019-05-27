package models

/**
  * Created by dianajanik on 27.04.2019
  */

import play.api.libs.json._

case class Category(idCategory: Int, categoryName: String, categoryUpper: Option[Int])

object Category {
  implicit val categoryFormat = Json.format[Category]
}

