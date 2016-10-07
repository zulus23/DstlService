package domain

import java.sql.Date
import java.time.LocalDate

import slick.lifted.Tag
import com.typesafe.slick.driver.ms.SQLServerDriver.api._
/**
  * Created by Gukov on 07.10.2016.
  */
case class Const(persId:String, name:String,tabel:String,fullname:String,dateIn:Date,dateFormat:String)

//(tabel.trim + " - "+ name.trim)
/*val rowToConst((String, String,String)) => Const = {
  case()
}
val constToRow:Const => Option[(String, String,String)]  =   p => Some((p.persId,p.name,p.tabel))
*/
class ConstTable(tag:Tag) extends Table[Const](tag,"const"){
  val constToRow:Const => Option[(String, String,String,Date)]  =   p => Some((p.persId,p.name,p.tabel,p.dateIn))
  val rowToConst:((String, String,String,Date)) => Const = {
    case(persId,name,tabel,dateIn) => Const(persId,name,tabel,fullname(tabel,name),dateIn,String.format("%s",dateIn.toLocalDate))
  }

  val fullname:(String,String) => String =   _.trim + " - "+_.trim
  def persId = column[String]("pers_id")
  def name = column[String]("name")
  def tabel = column[String]("tabel_n")
  def dateIn = column[Date]("date_post")


  def * = (persId,name,tabel,dateIn) <> (rowToConst,constToRow)
}


