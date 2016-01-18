import sbt.Keys._
import sbt._

object Version {
  val swaggerCore    = "1.3.12"
  val json4s   = "3.3.0"
  val akka = "2.4.1"
  val akkaHttpJson4s = "1.4.2"
  val akkaHttp = "2.0.2"


  val config               = "1.3.0"
  val commonsCodec         = "1.10"
  val commonsLang          = "3.4"

  val javaxsRs             = "1.1.1"


  val logBack              = "1.1.2"
  val scalaLogging         = "3.1.0"


  val restAssured           = "1.6"

  val scalacheck           = "1.12.2"
  val scalaMeter = "0.7"
  val scalaTest = "2.2.6"

}

object Library {

  val swaggerCore          = "com.wordnik"                %% "swagger-core"                   % Version.swaggerCore excludeAll(
    ExclusionRule(organization = "org.json4s"),  ExclusionRule(organization="org.fasterxml*"))


  val akkaStream = "com.typesafe.akka" %% "akka-stream-experimental" % Version.akkaHttp
  val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core-experimental" % Version.akkaHttp
  val akkaHttp =  "com.typesafe.akka" %% "akka-http-experimental" % Version.akkaHttp
  val akkaLog4j            = "com.typesafe.akka"          %% "akka-slf4j"                     % Version.akka


  val commonsCodec         = "commons-codec"              % "commons-codec"                   % Version.commonsCodec
  val commonsLang          = "org.apache.commons"         % "commons-lang3"                   % Version.commonsLang


  val scalaLogging         = "com.typesafe.scala-logging" %% "scala-logging"                  % Version.scalaLogging

  val logBack              = "ch.qos.logback"             % "logback-classic"                 % Version.logBack

  val config               = "com.typesafe"                % "config"                         % Version.config


  val javaxsWsRs           = "javax.ws.rs"                %  "jsr311-api"                     % Version.javaxsRs

  val scalacheck           = "org.scalacheck"             %% "scalacheck"                     % Version.scalacheck

  val restAssured       = "com.jayway.restassured" % "rest-assured"                 % Version.restAssured


  val json4sNative  = "org.json4s" %% "json4s-native" % Version.json4s
  val json4sJackson  = "org.json4s" %% "json4s-jackson" % Version.json4s
  val akkaHttpJson4s = "de.heikoseeberger" %% "akka-http-json4s" % Version.akkaHttpJson4s


  val scalaMeter = "com.storm-enroute" %% "scalameter" % Version.scalaMeter

  val scalaTest = "org.scalatest" %% "scalatest" % Version.scalaTest % "test"


}


