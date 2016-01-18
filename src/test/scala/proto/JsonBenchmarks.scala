package proto

import akka.http.scaladsl.model.{MediaTypes, HttpCharsets}
import akka.http.scaladsl.unmarshalling.Unmarshaller
import org.scalameter.api._
import org.scalameter.picklers.Implicits._
import org.json4s._

class JsonBenchmarks extends Bench[Double]  {
  /* configuration */

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min[Double],
    measurer)
  lazy val measurer = new Measurer.Default
  lazy val reporter = new LoggingReporter[Double]
  lazy val persistor = Persistor.None

  /* inputs */

  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges:Gen[Range] = for {
    size <- sizes
  } yield {
    val r:Range = 0 until size
    r
  }

  val responses:Gen[Response] = for {
    range <- ranges
  } yield {
    Response(range.toList, "s")
  }


  /* tests */

  performance of "Range" in {
    measure method "map" in {
      using(responses) in {

        implicit val serialization: Serialization =  native.Serialization
        implicit val formats = DefaultFormats

//        val x:Response   = serialization.read[Response]("""{
//                             |"v": 1,
//                             |"x": "2"
//                             |}""".stripMargin)
//            println(x)
//        r => r.map(_ + 1)

        res => serialization.write(res)

      }
    }
  }
}
