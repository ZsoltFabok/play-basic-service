import org.scalatestplus.play._

/**
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
class IntegrationSpec extends PlaySpec with OneServerPerTest with OneBrowserPerTest with HtmlUnitFactory {

  "Home page" should {
    "shows greeting message" in {
      go to ("http://localhost:" + port)
      pageSource must include ("Hi!")
    }
  }
}
