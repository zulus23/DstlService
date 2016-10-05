package security

import org.pac4j.core.context.HttpConstants
import org.pac4j.play.PlayWebContext
import org.pac4j.play.http.DefaultHttpActionAdapter
import play.mvc.Results
import play.mvc.Result

/**
  * Created by Gukov on 05.10.2016.
  */
class SecurityHttpActionAdapter extends  DefaultHttpActionAdapter {
  override def adapt(code: Int, context: PlayWebContext): Result = code match{
    case HttpConstants.UNAUTHORIZED => {
        Results.unauthorized(views.html.error401.render().toString()).as(HttpConstants.HTML_CONTENT_TYPE)
    }
    case HttpConstants.FORBIDDEN => {
        Results.forbidden(views.html.error403.render().toString()).as(HttpConstants.HTML_CONTENT_TYPE)
    }
    case _ => super.adapt(code,context)

  }
}
