package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * Created by dianajanik on 09.04.2019
  */

class CategoryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getAll = Action {
    Ok("cat is ready")
  }

  def getById(id: String) = Action {
    Ok("cat by id is ready")
  }

  def post = Action {
    Ok("cat post by id is ready")
  }

  def putById(id: String) = Action {
    Ok("cat put by id is ready")
  }

}

