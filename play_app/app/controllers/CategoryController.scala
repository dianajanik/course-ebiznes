package controllers

// import play.api.data._
import javax.inject.Inject
import models.CategoryRepository
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by dianajanik on 09.04.2019
  */

class CategoryController @Inject()(cc: ControllerComponents, categoryRepository: CategoryRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {


  def getAll = {
    Action.async { implicit request =>
      categoryRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int) = {
    Action.async { implicit request =>
      val computerAndOptions = for {
        category <- categoryRepository.findById(id)
      } yield category

      computerAndOptions.map { case (category) =>
        category match {
          case Some(category) => Ok(Json.toJson(category))
          case None => NotFound
        }
      }
    }
  }

  val categoryForm: Form[PostCategoryForm] = Form {
    mapping(
      "categoryName" -> nonEmptyText,
      "categoryUpper" -> optional(number)
    )(PostCategoryForm.apply)(PostCategoryForm.unapply)
  }

  def post =
    Action.async(parse.json) {
      implicit request =>
        categoryForm.bindFromRequest.fold(
          errorForm => {
            Future.successful(BadRequest("Not able to post"))
          },
          category => {
            categoryRepository.create(
              category.categoryName: String,
              category.categoryUpper: Option[Int]
            ).map { category =>
              Created(Json.toJson(category))
            }
          }
        )
    }

  def putById(id: Int) = Action {
    Ok("cat put by id is ready")
  }

  def delete(id: Int) = Action {
    categoryRepository.delete(id)
    Ok("Successfully removed")
  }

}

case class PostCategoryForm(categoryName: String, categoryUpper: Option[Int])

