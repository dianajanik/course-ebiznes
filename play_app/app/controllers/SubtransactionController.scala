package controllers
import play.api.data._
import javax.inject.Inject
import models.{ProductRepository, SubtransactionRepository}
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.Inject
import models.{ProductRepository, UserRepository}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

import play.api.data._
import javax.inject.Inject
import models.CategoryRepository
import play.api.data.Form
import play.api.data.Forms.{mapping, _}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

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
  val subtransactionForm: Form[PostSubtransactionForm] = Form {
    mapping(
      "idTransation" -> number,
      "idProduct" -> number,
      "subtransactionQuantity"-> number
    )(PostSubtransactionForm.apply)(PostSubtransactionForm.unapply)
  }

  def post = Action.async { implicit request =>
    subtransactionForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest("Failed to post stock"))
      },
      subtransaction => {
        subtransactionRepository.create(
          subtransaction.idTransaction,
          subtransaction.idProduct,
          subtransaction.subtransactionQuantity
        ).map { inventory =>
          Created(Json.toJson(inventory))
        }
      }
    )
  }

  def putById(id: Int) = Action {
    Ok("order put by id is ready")
  }

  def delete(id: Int) = Action{
    subtransactionRepository.delete(id)
    Ok("Successfully removed")
  }
}

case class PostSubtransactionForm(idTransaction: Int, idProduct: Int, subtransactionQuantity: Int)
