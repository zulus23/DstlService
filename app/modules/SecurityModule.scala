package modules

import com.google.inject.AbstractModule
import org.pac4j.core.config.Config
import org.pac4j.core.client.Clients
import org.pac4j.http.client.direct.HeaderClient
import org.pac4j.http.client.indirect.{FormClient, IndirectBasicAuthClient}
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator
import org.pac4j.play.store.{PlayCacheStore, PlaySessionStore}
import org.pac4j.play.{ApplicationLogoutController, CallbackController}
import play.api.{Configuration, Environment}
import security.SecurityHttpActionAdapter

/**
  * Created by Gukov on 05.10.2016.
  */
class SecurityModule(environment: Environment, configuration: Configuration) extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[PlaySessionStore]).to(classOf[PlayCacheStore])
    val formClient = new FormClient("/loginForm", new SimpleTestUsernamePasswordAuthenticator())
    val indirectBasicAuthClient = new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator())

    // REST authent with JWT for a token passed in header as the token parameter
    val jwtAuthenticator = new JwtAuthenticator()
    jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration("12345678901234567890123456789012"))
    jwtAuthenticator.addEncryptionConfiguration(new SecretEncryptionConfiguration("12345678901234567890123456789012"))
    val headerClient = new HeaderClient("token", jwtAuthenticator);



    val clients = new Clients("/callback",formClient,headerClient)

    val config = new Config(clients)
    config.setHttpActionAdapter(new SecurityHttpActionAdapter())
    bind(classOf[Config]).toInstance(config)



    // callback
    val callbackController = new CallbackController()
    callbackController.setDefaultUrl("/?defaulturlafterlogout")
    callbackController.setMultiProfile(true)
    bind(classOf[CallbackController]).toInstance(callbackController)

    // logout
    val logoutController = new ApplicationLogoutController()
    logoutController.setDefaultUrl("/")
    bind(classOf[ApplicationLogoutController]).toInstance(logoutController)

  }
}
