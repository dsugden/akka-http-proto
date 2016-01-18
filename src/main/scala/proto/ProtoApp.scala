package proto

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import swagger.SwaggerService

object ProtoApp extends App{

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher
  val protoRoutes = new ProtoRoutes {}

  import scala.reflect.runtime.universe._
  val swaggerApiService = SwaggerService(Seq(typeOf[ProtoRoutes]))


  val bindingFuture = Http().bindAndHandle(protoRoutes.route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  Console.readLine() // for the future transformations
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.shutdown()) // and shutdown when done

}
