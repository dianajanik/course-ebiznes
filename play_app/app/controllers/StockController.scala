package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

/**
  * Created by dianajanik on 09.04.2019
  */

class StockController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {


  def getAll = Action {
    Ok("stock is ready :) ")
  }

  def getById(id: String)  =  Action {
    Ok("stock by id is ready :) ")
  }

  def post =  Action {
    Ok("stock post by id is ready :) ")
  }

  def putById(id: String)  =  Action {
    Ok("stock put by id is ready :) ")
  }

}

