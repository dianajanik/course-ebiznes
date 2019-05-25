package controllers

import javax.inject.Inject
import models.{ProductRepository, UserRepository}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by dianajanik on 09.04.2019
  */

class ProductController @Inject()(cc: ControllerComponents, productRepository: ProductRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getAll = {
    Action.async { implicit request =>
      productRepository.list().map {
        user => Ok(Json.toJson(user))
      }
    }
  }

  def getById(id: Int) ={
    Action.async { implicit request =>
      val computerAndOptions = for {
        product <- productRepository.findById(id)
      } yield product

      computerAndOptions.map { case (product) =>
        product match {
          case Some(product) => Ok(Json.toJson(product))
          case None => NotFound
        }
      }
    }
  }

  def post =  Action {
    Ok("Product post by id is ready :) ")
  }

  def putById(id: Int)  =  Action {
    Ok("Product put by id is ready :) ")
  }
}

