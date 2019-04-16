package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * Created by dianajanik on 09.04.2019
  */

class OrderController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getAll = Action {
    Ok("order is ready")
  }

  def getById(id: String) = Action {
    Ok("order by id is ready")
  }

  def post = Action {
    Ok("order post by id is ready")
  }

  def putById(id: String) = Action {
    Ok("order put by id is ready")
  }

}


