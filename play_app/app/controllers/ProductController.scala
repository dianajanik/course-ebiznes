package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * Created by dianajanik on 09.04.2019
  */

class ProductController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getAll = Action {
    Ok("Products is ready :) ")
  }

  def getById(id: String)  =  Action {
    Ok("Product by id is ready :) ")
  }

  def post =  Action {
    Ok("Product post by id is ready :) ")
  }

  def putById(id: String)  =  Action {
    Ok("Product put by id is ready :) ")
  }

}

