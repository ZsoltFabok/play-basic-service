package controllers

import javax.inject.{Singleton, Inject}
import play.api.mvc.{Action, Controller}


@Singleton
class Web @Inject() extends Controller {
  def index = Action {
    Ok(views.html.index("Hi!"))
  }
}
