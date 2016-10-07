import com.google.inject.Inject
import domain.{ConstTable, DBDeliveryCompany, DeliveryCompany}
import org.scalatest.{BeforeAndAfter, FunSuite}
import com.typesafe.slick.driver.ms.SQLServerDriver.api._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import play.test.WithApplication
import slick.driver.JdbcProfile
import slick.jdbc.meta.MTable

/**
  * Created by Gukov on 07.10.2016.
  */
class DbTest extends FunSuite with BeforeAndAfter with ScalaFutures{
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))
  val consts  = TableQuery[ConstTable]




   var db: Database = _


   before  (db = Database.forConfig("mssqltest"))

   test("Select count from db"){

     val constList =  db.run(consts.filter(f => f.name.trim ==="АБДРАШИТОВ").result).futureValue


     assert(constList.size == 1)
     assert(constList.filter(f => f.persId.trim =="001").size == 1)
     assert(constList.find(f => f.fullname =="0520 - АБДРАШИТОВ").size == 1)
   }

  after(db.close())


}
