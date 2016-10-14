package domain

/**
  * Created by Gukov on 14.10.2016.
  */
case class Enterprise(id:Int,name:String,dbName:String)




object Enterprise {
   val listEnterprise:List[Enterprise] = {
      List(Enterprise(0,"ЗАО ГОТЭК-ЦПУ",""),
           Enterprise(1,"ЗАО ГОТЭК-СЕВЕРО-ЗАПАД",""),
           Enterprise(2,"ГОТЭК",""),
           Enterprise(3,"ПРИНТ",""),
           Enterprise(4,"ПОЛИПАК",""),
           Enterprise(5,"ЛИТАР",""),
           Enterprise(6,"ГОФРО","")

          )
   }
}


