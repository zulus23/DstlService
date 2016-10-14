import domain.Enterprise
import org.scalatestplus.play.PlaySpec

/**
  * Created by Gukov on 14.10.2016.
  */
class HelperSpec  extends PlaySpec{

  "Enterprises must be full" should  {

    "list must full" in {
         assert( !Enterprise.listEnterprise.isEmpty)
    }

     "list must contains item ГОФРО" in {
         assert(Enterprise.listEnterprise.exists(p => p.name == "ГОТЭК"))
     }

     "list must be single instance" in {
         val first = Enterprise.listEnterprise
         val second = Enterprise.listEnterprise

        assert(first == second)

     }

  }


}
