package proto

import javax.ws.rs.Path

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.wordnik.swagger.annotations.{ApiOperation, Api}
import org.json4s.{ DefaultFormats, jackson }
import de.heikoseeberger.akkahttpjson4s.Json4sSupport

import Json4sSupport._
import org.json4s.DefaultFormats

case class Response(v:List[Int], x:String)

@Api(value = "/v1/proto", description = "desc", produces = "application/json", position = 1)
trait ProtoRoutes {

  implicit val serialization = jackson.Serialization // or native.Serialization
  implicit val formats = DefaultFormats

  @Path("/hello")
  @ApiOperation(value = "Display Project Version", httpMethod = "GET")
  val route =
    path("v1/proto/hello") {
      get {
        complete {
          Response(List(1),"2")
        }
      }
    }

}
