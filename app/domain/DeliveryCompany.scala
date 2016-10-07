package domain

import slick.lifted.Tag
import com.typesafe.slick.driver.ms.SQLServerDriver.api._



/**
  * Created by Gukov on 05.10.2016.
  */
case class DBDeliveryCompany(Id:Long,Name:String,Address:String,ContactPerson:String,Phone:String)

class DeliveryCompany(tag:Tag) extends Table[DBDeliveryCompany](tag,"gtk_delivery_company"){
    def id = column[Long]("Id",O.PrimaryKey,O.AutoInc)
    def name = column[String]("name")
    def address = column[String]("address")
    def contactPerson =column[String]("contact_person")
    def phone = column[String]("phone")
    def * = (id, name, address, contactPerson,phone) <> (DBDeliveryCompany.tupled, DBDeliveryCompany.unapply)

}
