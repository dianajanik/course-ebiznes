package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * Created by dianajanik on 09.04.2019
  */

class AuthController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok("Auth is ready :) ")
  }

}

