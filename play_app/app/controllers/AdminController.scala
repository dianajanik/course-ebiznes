package controllers

// import play.api.data._
import javax.inject.Inject
import models.AdminRepository
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by dianajanik on 09.04.2019
  */

class AdminController @Inject()(cc: ControllerComponents, adminRepository: AdminRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {


  def getAll = {
    Action.async { implicit request =>
      adminRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int) = {
    Action.async { implicit request =>
      val computerAndOptions = for {
        admin <- adminRepository.findById(id)
      } yield admin

      computerAndOptions.map { case (admin) =>
        admin match {
          case Some(admin) => Ok(Json.toJson(admin))
          case None => NotFound
        }
      }
    }
  }

  val adminForm: Form[PostAdminForm] = Form {
    mapping(
      "adminUsername" -> nonEmptyText,
      "adminPassword" -> nonEmptyText
    )(PostAdminForm.apply)(PostAdminForm.unapply)
  }

  def post =
    Action.async(parse.json) {
      implicit request =>
        adminForm.bindFromRequest.fold(
          errorForm => {
            Future.successful(BadRequest("Not able to post"))
          },
          admin => {
            adminRepository.create(
              admin.adminUsername: String,
              admin.adminPassword: String
            ).map { admin =>
              Created(Json.toJson(admin))
            }
          }
        )
    }

  def putById(id: Int) =
    Action.async(parse.json){
      implicit request =>
        adminForm.bindFromRequest.fold(
          _ => {
            Future.successful(BadRequest("Failed put"))
          },
          admin => {
            adminRepository.update(models.Admin(
              id,
              admin.adminUsername,
              admin.adminPassword
            )).map({ _ =>
              Ok
            })
          }
        )
    }

  def delete(id: Int) = Action {
    adminRepository.delete(id)
    Ok("Successfully removed")
  }

}

case class PostAdminForm(adminUsername: String, adminPassword: String)

