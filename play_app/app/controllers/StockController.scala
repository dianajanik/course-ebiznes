package controllers

import javax.inject.Inject
import models.StockRepository
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by dianajanik on 09.04.2019
  */

class StockController @Inject()(cc: ControllerComponents, stockRepository: StockRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {


  def getAll = {
    Action.async { implicit request =>
      stockRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int)  =
    Action.async { implicit request =>
      val computerAndOptions = for {
        stock <- stockRepository.findById(id)
      } yield stock

      computerAndOptions.map { case (stock) =>
        stock match {
          case Some(stock) => Ok(Json.toJson(stock))
          case None => NotFound
        }
      }
    }

  def post =  Action {
    Ok("stock post by id is ready :) ")
  }

  def putById(id: Int)  =  Action {
    Ok("stock put by id is ready :) ")
  }

  def delete(id: Int) = Action{
    stockRepository.delete(id)
    Ok("Successfully removed")
  }
}

