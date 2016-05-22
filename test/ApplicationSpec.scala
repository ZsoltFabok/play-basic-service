import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }
  }

  "WebController" should {
    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Hi!")
    }
  }

  "api" should {
    "sum works fine" in {
      val jsonString =
        """{
           "numbers": [1,3,5]
           }
        """.stripMargin.replaceAll("\n", " ")

      val result = route(app, FakeRequest(POST, "/api/sum").withJsonBody(Json.parse(jsonString))).get
      contentAsString(result) must equal("""{"result":9}""")
    }

    "sum fails for bad input json" in {
      val jsonString =
        """{
           "numbers": [1.1,3,5]
           }
        """.stripMargin.replaceAll("\n", " ")

      val result = route(app, FakeRequest(POST, "/api/sum").withJsonBody(Json.parse(jsonString))).get
      contentAsString(result) must equal("""{"error":"bad Json for this service; try: {'numbers': [int, int, ...]}"}""")
    }
  }
}
