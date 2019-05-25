package controllers

import javax.inject.Inject
import models.{ProductRepository, SubtransactionRepository}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by dianajanik on 25.05.2019
  */

class SubtransactionController @Inject()(cc: ControllerComponents, subtransactionRepository: SubtransactionRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getAll = {
    Action.async { implicit request =>
      subtransactionRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int) ={
    Action.async { implicit request =>
      val computerAndOptions = for {
        transaction <- subtransactionRepository.findById(id)
      } yield transaction

      computerAndOptions.map { case (transaction) =>
        transaction match {
          case Some(transaction) => Ok(Json.toJson(transaction))
          case None => NotFound
        }
      }
    }
  }

  def post = Action {
    Ok("order post by id is ready")
  }

  def putById(id: Int) = Action {
    Ok("order put by id is ready")
  }

  def delete(id: Int) = Action{
    subtransactionRepository.delete(id)
    Ok("Successfully removed")
  }
}


