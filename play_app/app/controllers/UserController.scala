package controllers
import play.api.data.Form
import play.api.data.Forms.{mapping, of}
import play.api.data.Forms._
import javax.inject.Inject
import models.UserRepository
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by dianajanik on 09.04.2019
  */

class UserController @Inject()(cc: ControllerComponents, userRepository: UserRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getAll = {
    Action.async { implicit request =>
      userRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int) ={
    Action.async { implicit request =>
      val computerAndOptions = for {
        user <- userRepository.findById(id)
      } yield user

      computerAndOptions.map { case (user) =>
        user match {
          case Some(user) => Ok(Json.toJson(user))
          case None => NotFound
          }
        }
      }
  }

  val userForm: Form[CreateUserForm] = Form {
    mapping(
      "userEmail" -> nonEmptyText,
      "userPassword:"-> nonEmptyText,
      "userName" -> nonEmptyText,
      "userSurname" -> nonEmptyText,
      "userStreet"-> nonEmptyText,
      "userHomeNumber" -> nonEmptyText,
      "userCity" -> nonEmptyText,
      "userCountry"-> nonEmptyText,
      "userPostalCode"-> nonEmptyText
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  }

  def post = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest("Failed to post user"))
      },
      user => {
        userRepository.create(
         user.userEmail,
          user.userPassword,
          user.userName,
          user.userSurname,
          user.userStreet,
          user.userHomeNumber,
          user.userCity,
          user.userCountry,
          user.userPostalCode
        ).map { user =>
          Created(Json.toJson(user))
        }
      }
    )
  }

  def putById(id: Int) = Action {
    Ok("USER put by id is ready")
  }

  def delete(id: Int) = Action{
    userRepository.delete(id)
    Ok("user removed")
  }
}

case class CreateUserForm(userEmail: String, userPassword: String, userName: String, userSurname: String,
                          userStreet: String, userHomeNumber: String, userCity: String, userCountry: String, userPostalCode: String)
