package controllers

import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.http.Status
import play.api.http.Status.OK
import play.api.mvc.BodyParsers.utils.when
import play.api.mvc.ControllerComponents
import play.api.test.Helpers.{GET, contentAsString, contentType, defaultAwaitTimeout, status, stubControllerComponents}
import play.api.test.{FakeRequest, Injecting}
import scala.reflect.ClassManifestFactory.Any



class FormControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  lazy val controllerComponents: ControllerComponents = app.injector.instanceOf[ControllerComponents]


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

  val mockDataRepository: AgeFormModel = mock[AgeFormModel]
  val dataModelAge:AgeFormModel=AgeFormModel(34)

  "FormController .submitAgeForm" should{
    "return OK" when {
      "an age of integer submitted" in{
        when(mockDataRepository(Any[Int])
          .thenReturn(dataModelAge)

          status(result) shouldBe (Status.OK)

      }
    }

//    "return BadRequest" when{
//      "not an integer is submitted" in{
//        when()
//          .thenReturn(dataModelAge)
//
//        status(result) shouldBe Status.BAD_REQUEST
//      }
//    }

  }

}
