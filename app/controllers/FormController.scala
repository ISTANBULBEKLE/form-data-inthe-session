package controllers

import play.api.data.Form
import play.api.data.Forms.{list, mapping, number, text}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.Inject


class FormController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def ageForm()= Action{ implicit request: Request[AnyContent] =>
    println(" age request.session " + request.session)
    Ok(views.html.ageform(AgeForm.form.fill(AgeFormModel())))
  }

  def submitAgeForm () = Action{implicit request =>
    println()
    AgeForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.ageform(formWithErrors)),
      success        => Redirect(controllers.routes.FormController.colourForm()).withSession(request.session + ("age" -> s"${success.age}"))
      )
  }

  def colourForm()= Action{ implicit request: Request[AnyContent] =>
    println("colour request.session " + request.session)
    Ok(views.html.colourform(ColourForm.form.fill(ColourFormModel(List()))))
  }

  def submitColourForm()= Action {implicit request =>
    ColourForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.colourform(formWithErrors)),
      success        => Redirect(controllers.routes.FormController.jobForm()).withSession(request.session + ("colour" -> s"${success.colour}"))
    )
  }

  def jobForm()= Action{ implicit request: Request[AnyContent] =>
    println("job request.session " + request.session)
    Ok(views.html.jobform(JobForm.form.fill(JobFormModel())))
  }

  def submitJobForm()= Action{implicit request =>
    JobForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.jobform(formWithErrors)),
      success        => Redirect(controllers.routes.FormController.nameForm()).withSession(request.session + ("job" -> s"${success.job}"))
    )
  }

  def nameForm()= Action{ implicit request: Request[AnyContent] =>
    println("name request.session " + request.session)
    Ok(views.html.nameform(NameForm.form.fill(NameFormModel())))
  }

  def submitNameForm()= Action{implicit request =>
    NameForm.form.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.nameform(formWithErrors)),
      success        => Redirect(controllers.routes.FormController.genderForm()).withSession(request.session + ("name" -> s"${success.name}"))
    )
  }

  def genderForm()= Action{ implicit request: Request[AnyContent] =>
    println("gender request.session " + request.session)
    Ok(views.html.genderform(GenderForm.form.fill(GenderFormModel())))
  }

  def submitGenderForm()=Action{implicit request =>
  GenderForm.form.bindFromRequest().fold(
    formWithErrors => BadRequest(views.html.genderform(formWithErrors)),
    success        => Redirect(controllers.routes.HomeController.summary()).withSession(request.session + ("gender" -> s"${success.gender}"))
  )
}
}

case class AgeFormModel(age:Int= 25)

object AgeForm {
  val form: Form[AgeFormModel] = Form(
    mapping(
      "age" -> number
    )(AgeFormModel.apply)(AgeFormModel.unapply)
  )
}

case class ColourFormModel(colour:List[String])
object ColourForm {
  val form: Form[ColourFormModel] = Form(
    mapping(
      "colour"    -> list(text)
    )(ColourFormModel.apply)(ColourFormModel.unapply)
  )
}

case class JobFormModel(job:String="Astronaut")
object JobForm {
  val form: Form[JobFormModel] = Form(
    mapping(
      "job" -> text
    )(JobFormModel.apply)(JobFormModel.unapply)
  )
}

case class NameFormModel(name:String="Ekip Kalir")
object NameForm {
  val form: Form[NameFormModel] = Form(
    mapping(
      "name" -> text
    )(NameFormModel.apply)(NameFormModel.unapply)
  )
}

case class GenderFormModel(gender:String="Male")
object GenderForm {
  val form: Form[GenderFormModel] = Form(
    mapping(
      "gender"   -> text
    )(GenderFormModel.apply)(GenderFormModel.unapply)
  )
}






