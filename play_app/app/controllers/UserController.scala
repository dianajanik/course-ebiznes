package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * Created by dianajanik on 09.04.2019
  */

class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getAll = Action {
    Ok("User is ready")
  }

  def getById(id: String)  =  Action {
    Ok("USer by id is ready")
  }

  def post  =  Action {
    Ok("User post by id is ready")
  }

  def putById(id: String)  =  Action {
    Ok("USER put by id is ready")
  }

}

