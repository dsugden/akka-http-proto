/**
 * Copyright 2014 Getty Imges, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package swagger

import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, HttpResponse }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.wordnik.swagger.config.SwaggerConfig
import com.wordnik.swagger.core.SwaggerSpec
import com.wordnik.swagger.core.util.JsonSerializer
import com.wordnik.swagger.model._

import scala.reflect.runtime.universe.Type

trait SwaggerHttpService {

  def apiTypes: Seq[Type]

  def apiVersion: String
  def swaggerVersion: String = SwaggerSpec.version

  def baseUrl: String //url of api
  def docsPath: String = "api-docs" //path to swagger's endpoint
  def apiInfo: Option[ApiInfo] = None
  def authorizations: List[AuthorizationType] = List()

  private val api =
    new SwaggerApiBuilder(
      new SwaggerConfig(
        apiVersion,
        swaggerVersion,
        baseUrl,
        "", //api path, baseUrl is used instead
        authorizations, //authorizations
        apiInfo
      ), apiTypes
    )

  final def routes: Route =
    get {
      path(docsPath) {
        complete(HttpResponse.apply(entity =
          HttpEntity.apply(ContentTypes.`application/json`, toJsonString(api.getResourceListing())))
        )
      } ~ {
        for (
          (subPath, apiListing) <- api.listings
        ) yield {
          path(docsPath / subPath.drop(1).split('/').map(
            segmentStringToPathMatcher(_)
          ).reduceLeft(_ / _)) {
            complete(
              HttpResponse.apply(entity =
                HttpEntity.apply(ContentTypes.`application/json`, toJsonString(apiListing)))
            )
          }
        }
      }.reduceLeft(_ ~ _)
    }

  def toJsonString(data: Any): String = {
    if (data.getClass.equals(classOf[String])) {
      data.asInstanceOf[String]
    } else {
      JsonSerializer.asJson(data.asInstanceOf[AnyRef])
    }

  }
}
