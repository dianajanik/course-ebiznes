package controllers

import java.sql.Timestamp
import javax.inject.Inject
import models.{ProductRepository, TransactionRepository}
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import javax.inject._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.libs.json.Json

import play.api.data.Forms._


/**
  * Created by dianajanik on 09.04.2019
  */

class TransactionController @Inject()(cc: ControllerComponents, transactionRepository: TransactionRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getAll = {
    Action.async { implicit request =>
      transactionRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int) ={
    Action.async { implicit request =>
      val computerAndOptions = for {
        transaction <- transactionRepository.findById(id)
      } yield transaction

      computerAndOptions.map { case (transaction) =>
        transaction match {
          case Some(transaction) => Ok(Json.toJson(transaction))
          case None => NotFound
        }
      }
    }
  }

  val transactionForm: Form[PostTransactionForm] = Form {
    mapping(
      "idUser" -> number,
      "transactionDate" -> sqlTimestamp
    )(PostTransactionForm.apply)(PostTransactionForm.unapply)
  }

  def post = Action.async { implicit request =>
    transactionForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest("Failed to post stock"))
      },
      transaction => {
        transactionRepository.create(
          transaction.idUser,
          transaction.transactionDate
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
    transactionRepository.delete(id)
    Ok("Successfully removed")
  }

}

case class PostTransactionForm(idUser: Int, transactionDate: Timestamp )


