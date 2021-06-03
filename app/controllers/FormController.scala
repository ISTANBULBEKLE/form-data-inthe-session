package controllers

import play.api.data.Form
import play.api.data.Forms.{boolean, mapping, number, text}
import play.api.mvc.{AbstractController, AnyContent, BaseController, ControllerComponents, Request}
import javax.inject.{Inject, Singleton}


class FormController @Inject() (cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport{

  def ageForm()= Action{ implicit request: Request[AnyContent] =>
    val receivedData = request.body.asFormUrlEncoded
    println("receiveddata " + receivedData)

    val ageFromInput = receivedData.map{ args =>
      args("Your Age").head
    }.getOrElse(Ok("Error"))

    val ageDataAddSession = request.session + ("age" -> s"${ageFromInput}")
    Ok(views.html.ageform(AgeForm.form)).withSession(ageDataAddSession)
  }

  def genderForm()= Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.genderform(GenderForm.form))
  }

  def jobForm()= Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.jobform(JobForm.form))
  }

  def nameForm()= Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.nameform(NameForm.form))
  }

  def colourForm()= Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.colourform(ColourForm.form))
  }


}

case class AgeForm(age:Int)
object AgeForm {
  val form: Form[AgeForm] = Form(
    mapping(
      "age" -> number
    )(AgeForm.apply)(AgeForm.unapply)
  )
}
case class GenderForm(woman:Boolean, man:Boolean)
object GenderForm {
  val form: Form[GenderForm] = Form(
    mapping(
      "woman" -> boolean,
      "man"   -> boolean
    )(GenderForm.apply)(GenderForm.unapply)
  )
}

case class JobForm(job:String)
object JobForm {
  val form: Form[JobForm] = Form(
    mapping(
      "job" -> text
    )(JobForm.apply)(JobForm.unapply)
  )
}

case class NameForm(name:String)
object NameForm {
  val form: Form[NameForm] = Form(
    mapping(
      "name" -> text
    )(NameForm.apply)(NameForm.unapply)
  )
}
case class ColourForm(blue:Boolean, purple:Boolean, green:Boolean, pink:Boolean, red:Boolean)
object ColourForm {
  val form: Form[ColourForm] = Form(
    mapping(
      "blue"    -> boolean,
      "purple"  -> boolean,
      "green"   -> boolean,
      "pink"    -> boolean,
      "red"     -> boolean
    )(ColourForm.apply)(ColourForm.unapply)
  )
}




