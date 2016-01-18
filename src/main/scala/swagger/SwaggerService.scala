package swagger

import akka.actor.ActorContext
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.wordnik.swagger.model.ApiInfo

/**
 * Implements routes for Swagger API documentation and testing.
 *
 * @param actorContext The Actor context of the Akka Http server.
 */
case class SwaggerService(types: scala.Seq[scala.reflect.runtime.universe.Type]) {

  val routes = getSwaggerUiRoute ~ getSwaggerApiRoute(types)

  def getSwaggerUiRoute: Route =
    get {
      pathPrefix("") {
        pathEndOrSingleSlash {
          getFromResource("swagger-ui/index.html")
        }
      } ~ getFromResourceDirectory("swagger-ui") ~ getFromFile("swagger-ui/index.html") ~ getFromDirectory("swagger-ui")

    }

  def getSwaggerApiRoute( types: scala.Seq[scala.reflect.runtime.universe.Type]): Route = {
    new SwaggerHttpService {
      override def apiTypes = types

      override def apiVersion = "2.0"

      override def baseUrl = "/"

      // let swagger-ui determine the host and port
      override def docsPath = "api-docs"

//      def actorRefFactory = actorContext

      override def apiInfo = Some(new ApiInfo("akka-http-proto API", "API for akka-http-proto", "", "", "", ""))
    }.routes
  }
}
