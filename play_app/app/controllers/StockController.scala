package controllers

import javax.inject.Inject
import models.StockRepository
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import models.{ProductRepository, UserRepository}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

// import play.api.data._
import javax.inject.Inject
import models.CategoryRepository
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}


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

  val stockForm: Form[PostStockForm] = Form {
    mapping(
      "idProduct" -> number,
      "quantity"-> number
    )(PostStockForm.apply)(PostStockForm.unapply)
  }

  def post = Action.async { implicit request =>
    stockForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest("Failed to post stock"))
      },
      stock => {
        stockRepository.create(
          stock.idProduct,
          stock.quantity
        ).map { stock =>
          Created(Json.toJson(stock))
        }
      }
    )
  }

  def putById(id: Int) =
    Action.async(parse.json){
      implicit request =>
        stockForm.bindFromRequest.fold(
          _ => {
            Future.successful(BadRequest("Failed put"))
          },
          stock => {
            stockRepository.update(models.Stock(
              id,
              stock.idProduct,
              stock.quantity
            )).map({ _ =>
              Ok
            })
          }
        )
    }

  def delete(id: Int) = Action{
    stockRepository.delete(id)
    Ok("Successfully removed")
  }
}

case class PostStockForm(idProduct: Int, quantity: Int)
