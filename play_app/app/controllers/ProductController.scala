package controllers

import javax.inject.Inject
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

import scala.concurrent.{ExecutionContext, Future}
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

  val productForm: Form[PostProductForm] = Form {
    mapping(
      "productCategory" -> number,
      "productPrice"-> number,
      "productName" -> nonEmptyText,
      "productDescription" -> nonEmptyText,
      "productPhoto"-> optional(nonEmptyText),
      "productNotSaled" -> boolean
    )(PostProductForm.apply)(PostProductForm.unapply)
  }

  def post = Action.async { implicit request =>
      productForm.bindFromRequest.fold(
        errorForm => {
          Future.successful(BadRequest("Failed post"))
        },
        product => {
          productRepository.create(
            product.productCategory,
            product.productPrice,
            product.productName,
            product.productDescription,
            product.productPhoto,
            product.productNotSaled
          ).map { product =>
            Created(Json.toJson((product)))
          }
        }
      )
    }

  def putById(id: Int) =
    Action.async(parse.json){
      implicit request =>
        productForm.bindFromRequest.fold(
          _ => {
            Future.successful(BadRequest("Failed put"))
          },
          product => {
            productRepository.update(models.Product(
              id,
              product.productCategory,
              product.productPrice,
              product.productName,
              product.productDescription,
              product.productPhoto,
              product.productNotSaled
            )).map({ _ =>
              Ok
            })
          }
        )
    }
  def delete(id: Int) = Action{
    productRepository.delete(id)
    Ok("Successfully removed")
  }
}

case class PostProductForm(productCategory: Int, productPrice: Int, productName: String, productDescription: String, productPhoto: Option[String], productNotSaled: Boolean)