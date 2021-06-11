package controllers



import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.Play.materializer
import play.api.data.Forms.{list, text}
import play.api.http.Status
import play.api.http.Status.OK
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.ControllerComponents
import play.api.test.Helpers.{GET, POST, contentAsString, contentType, defaultAwaitTimeout, status, stubControllerComponents}
import play.api.test.{FakeRequest, Injecting}


class FormControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]

  object TestFormController extends FormController(
    controllerComponents
  )

  "FormController GET" should {

    "render the ageForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val age = controller.ageForm().apply(FakeRequest(GET, "/age"))

      status(age) mustBe OK
      contentType(age) mustBe Some("text/html")
      contentAsString(age) must include ("Age")
    }

    "render the ageForm page from the application" in {
      val controller = inject[FormController]
      val age = controller.ageForm().apply(FakeRequest(GET, "/age"))

      status(age) mustBe OK
      contentType(age) mustBe Some("text/html")
      contentAsString(age) must include ("Age")
    }

    "render the colourForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val colour = controller.colourForm().apply(FakeRequest(GET, "/colour"))

      status(colour) mustBe OK
      contentType(colour) mustBe Some("text/html")
      contentAsString(colour) must include ("Colour")
    }

    "render the colourForm page from the application" in {
      val controller = inject[FormController]
      val colour = controller.colourForm().apply(FakeRequest(GET, "/colour"))

      status(colour) mustBe OK
      contentType(colour) mustBe Some("text/html")
      contentAsString(colour) must include ("Colour")
    }

    "render the genderForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val gender = controller.genderForm().apply(FakeRequest(GET, "/gender"))

      status(gender) mustBe OK
      contentType(gender) mustBe Some("text/html")
      contentAsString(gender) must include ("Gender")
    }

    "render the genderForm page from the application" in {
      val controller = inject[FormController]
      val gender = controller.genderForm().apply(FakeRequest(GET, "/gender"))

      status(gender) mustBe OK
      contentType(gender) mustBe Some("text/html")
      contentAsString(gender) must include ("Gender")
    }

    "render the jobForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val gender = controller.jobForm().apply(FakeRequest(GET, "/job"))

      status(gender) mustBe OK
      contentType(gender) mustBe Some("text/html")
      contentAsString(gender) must include ("Job")
    }

    "render the jobForm page from the application" in {
      val controller = inject[FormController]
      val job = controller.jobForm().apply(FakeRequest(GET, "/job"))

      status(job) mustBe OK
      contentType(job) mustBe Some("text/html")
      contentAsString(job) must include ("Job")
    }

    "render the nameForm page from a new instance of controller" in {
      val controller = new FormController(stubControllerComponents())
      val name = controller.nameForm().apply(FakeRequest(GET, "/name"))

      status(name) mustBe OK
      contentType(name) mustBe Some("text/html")
      contentAsString(name) must include ("Name")
    }

    "render the nameForm page from the application" in {
      val controller = inject[FormController]
      val name = controller.nameForm().apply(FakeRequest(GET, "/name"))

      status(name) mustBe OK
      contentType(name) mustBe Some("text/html")
      contentAsString(name) must include ("Name")
    }

  }

  "FormController POST" should {
    ///submitAgeForm
    "the json body in submitAgeForm  is valid" when {
      val jsonBody: JsObject = Json.obj(
        "age" -> 24
      )
      "return the status code Redirect (See_Other)" in{
        val result =TestFormController.submitAgeForm().apply(FakeRequest(POST,"/age").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER
      }
    }

    "the json body in submitAgeForm  is invalid" when {
      val jsonBody: JsObject = Json.obj(
        "unexpected field" -> "foo"
      )
      "return the status code Bad Request" in{
        val result =TestFormController.submitAgeForm().apply(FakeRequest(POST,"/age").withJsonBody(jsonBody))
        status(result) mustBe Status.BAD_REQUEST
      }
    }
    /////submitColorForm
    "the json body in submitColorForm  is valid" when{
      val jsonBody: JsObject = Json.obj(
        "colour"    -> "green"
      )
      "return the status code Redirect (See_Other)" in{
        val result = TestFormController.submitColourForm().apply(FakeRequest(POST,"/colour").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER
      }
    }
//
//    "the json body in submitColorForm  is invalid" when{
//      "return the status code Bad Request" in{
//        val result = TestFormController.submitColourForm().apply(FakeRequest(POST,"/colour"))
//        status(result) mustBe Status.BAD_REQUEST
//      }
//    }

    ////submitJobForm
    "the json body in submitJobForm  is valid" when {
      val jsonBody: JsObject = Json.obj(
        "job" -> "astronaut"
      )
      "return the status code Redirect (See_Other)" in{
        val result =TestFormController.submitJobForm().apply(FakeRequest(POST,"/job").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER
      }
    }

    "the json body in submitJobForm  is invalid" when {
      "return the status code Bad Request" in{
        val result =TestFormController.submitJobForm().apply(FakeRequest(POST,"/job"))
        status(result) mustBe Status.BAD_REQUEST
      }
    }

    ////submitNameForm
    "the json body in submitNameForm  is valid" when {
      val jsonBody: JsObject = Json.obj(
        "name" -> "ekip"
      )
      "return the status code Redirect (See_Other)" in{
        val result =TestFormController.submitNameForm().apply(FakeRequest(POST,"/name").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER
      }
    }

    "the json body in submitNameForm  is invalid" when {
      "return the status code Bad Request" in{
        val result =TestFormController.submitNameForm().apply(FakeRequest(POST,"/name"))
        status(result) mustBe Status.BAD_REQUEST
      }
    }

    ////submitGenderForm
    "the json body in submitGenderForm  is valid" when {
      val jsonBody: JsObject = Json.obj(
        "gender" -> "male"
      )
      "return the status code Redirect (See_Other)" in{
        val result =TestFormController.submitGenderForm().apply(FakeRequest(POST,"/gender").withJsonBody(jsonBody))
        status(result) mustBe Status.SEE_OTHER
      }
    }

    "the json body in submitGenderForm  is invalid" when {
      "return the status code Bad Request" in{
        val result =TestFormController.submitGenderForm().apply(FakeRequest(POST,"/gender"))
        status(result) mustBe Status.BAD_REQUEST
      }
    }

  }




}
