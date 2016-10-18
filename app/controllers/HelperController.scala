package controllers

import javax.inject.Inject

import domain.Enterprise
import play.api.mvc.{Action, Controller}


import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json.{Json, Writes}

/**
  * Created by Gukov on 18.10.2016.
  */
class HelperController @Inject() extends  Controller{


  def serviceDstl = Action.async{

    implicit val enterpriseWriters = new Writes[Enterprise] {
      override def writes(enterprise: Enterprise) = Json.obj(
        "id" -> enterprise.id,
        "name" -> enterprise.name,
        "dbName" -> enterprise.dbName
      )


    }

       val list = Future{Enterprise.listServiceDst}
       list.map( l => Ok(Json.toJson(l)))

    }


}
