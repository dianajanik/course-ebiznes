package controllers

import javax.inject.Inject
import models.CategoryRepository
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

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
  def getById(id: Int) ={
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
  def post = Action {
    Ok("cat post by id is ready")
  }

  def putById(id: Int) = Action {
    Ok("cat put by id is ready")
  }

  def delete(id: Int) = Action{
    categoryRepository.delete(id)
    Ok("Successfully removed")
  }

}

