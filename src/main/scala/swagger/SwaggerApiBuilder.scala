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

import com.wordnik.swagger.config._
import com.wordnik.swagger.core.util.ReaderUtil
import com.wordnik.swagger.model._

import scala.reflect.runtime.universe._

class SwaggerApiBuilder(
    config: SwaggerConfig,
    apiTypes: Seq[Type]) extends ReaderUtil {

  val scanner = new SprayApiScanner(apiTypes)
  val reader = new SprayApiReader()

  val listings: Map[String, ApiListing] = {
    val classes = scanner match {
      case scanner: Scanner => scanner.classes()
      case _ => List()
    }
    val listings = (for (cls <- classes) yield reader.read("", cls, config)).flatten
    val mergedListings = groupByResourcePath(listings)
    mergedListings.map(m => (m.resourcePath, m)).toMap
  }

  def getApiListing(path: String): Option[ApiListing] = {
    listings.get(path)
  }

  def getResourceListing(): ResourceListing = {

    val references = listings.map {
      case (path, listing) => ApiListingReference(path, listing.description)
    }.toList

    ResourceListing(
      config.getApiVersion,
      config.getSwaggerVersion,
      references, //apilistingreference
      config.authorizations, //authorizations tbd
      config.info
    )
  }

}
