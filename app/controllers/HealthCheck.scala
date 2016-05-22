package controllers

import javax.inject.Singleton
import play.api.mvc.{Action, Controller}


@Singleton
class HealthCheck extends Controller {
  def index() = Action {
    Ok("Ok")
  }
}

