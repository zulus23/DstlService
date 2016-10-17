package controllers

import com.google.inject.Inject
import org.pac4j.core.profile.{CommonProfile, ProfileManager}
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.play.PlayWebContext
import org.pac4j.play.scala.Security
import play.api.mvc._
import play.libs.concurrent.HttpExecutionContext
import org.pac4j.core.config.Config
import org.pac4j.play.store.PlaySessionStore

import scala.collection.JavaConversions._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */

class ApplicationController @Inject()(implicit webJarAssets: WebJarAssets,val config: Config, val playSessionStore: PlaySessionStore, override val ec: HttpExecutionContext)
              extends Controller with Security[CommonProfile] {

  private def getProfiles(implicit request: RequestHeader): List[CommonProfile] = {
    val webContext = new PlayWebContext(request, playSessionStore)
    val profileManager = new ProfileManager[CommonProfile](webContext)
    val profiles = profileManager.getAll(true)
    asScalaBuffer(profiles).toList
  }


  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {request =>
    val profile = getProfiles(request)
    Ok(views.html.index("Your new application is ready.")(webJarAssets,profile))
  }

  def loginForm() = Action{ request =>
    val formClient = config.getClients.findClient("FormClient").asInstanceOf[FormClient]
    Ok(views.html.loginForm.render(formClient.getCallbackUrl))
  }

  def planShipment() = Action { request => Ok(views.html.planShipment("План отгрузки на сутки")(webJarAssets))  }
}
