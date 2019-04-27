package models

/**
  * Created by dianajanik on 16.04.2019
  */

import play.api.libs.json._

case class User(idUser: Int, userEmail: String, userPassword: String, userName: String, userSurname: String,
                userStreet: String, userHomeNumber: String, userCity: String, userCountry: String, userPostalCode: String)

object User {
  implicit val userFormat = Json.format[User]
}
