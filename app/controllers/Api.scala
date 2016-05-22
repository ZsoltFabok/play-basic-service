package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import services.Calculator


@Singleton
class Api @Inject() (calc:Calculator) extends Controller {

  def sum = Action {
    request =>
      val jsonBody: Option[JsValue] = request.body.asJson

      implicit val numbersReads = (__ \ "numbers").read[Seq[Int]]

      val validationResult = jsonBody.get.validate[Seq[Int]](numbersReads)

      val result = validationResult match {
        case s: JsSuccess[Seq[Int]] => {
          val numbers = s.get
          // I didn't have patience for the ScaalJsonCombinator's Write[T]
          Json.obj("result" -> calc.sum(numbers))
        }
        case e: JsError => {
          Json.obj("error" -> """bad Json for this service; try: {'numbers': [int, int, ...]}""")
        }
      }

      Ok(result)
  }
}
