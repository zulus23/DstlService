package domain

import javax.persistence.{Column, Entity, Id, Table}

/**
  * Created by Gukov on 05.10.2016.
  */
@Entity
@Table(name = "gtk_delivery_company")
class DeliveryCompany(vId:Long,vName:String,vAddress:String,vContactPerson:String,vPhone:String) {

    @Id
    @Column(name="id")
    val id = vId

    @Column(name="name")
    val name = vName

    @Column(name="address")
    val adress = vAddress

    @Column(name="contact_person")
    val contactPerson = vContactPerson

    @Column(name="phone")
    val phone = vPhone

}
