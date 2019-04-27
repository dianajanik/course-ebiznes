package models

/**
  * Created by dianajanik on 27.04.2019
  */

import play.api.libs.json._

case class Admin(idAdmin: Int, adminUsername: String, adminPassword: String)

object Admin {
  implicit val adminFormat = Json.format[Admin]
}

