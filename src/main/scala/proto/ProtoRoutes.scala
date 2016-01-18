package proto

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.json4s.{ DefaultFormats, jackson }
import de.heikoseeberger.akkahttpjson4s.Json4sSupport

import Json4sSupport._
import org.json4s.DefaultFormats

case class Response(v:List[Int], x:String)

trait ProtoRoutes {

  implicit val serialization = jackson.Serialization // or native.Serialization
  implicit val formats = DefaultFormats

  val route =
    path("hello") {
      get {
        complete {
          Response(List(1),"2")
        }
      }
    }

}
