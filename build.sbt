name := "akka-http-proto"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += Resolver.bintrayRepo("hseeberger", "maven")


resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= List(
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.json4s" %% "json4s-jackson" % "3.3.0",
  "de.heikoseeberger" %% "akka-http-json4s" % "1.4.2",
  "com.typesafe.akka" %% "akka-stream-experimental" % "2.0.2",
  "com.typesafe.akka" %% "akka-http-core-experimental" % "2.0.2",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.0.2",
  "com.storm-enroute" %% "scalameter" % "0.7",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

logBuffered := false

parallelExecution in Test := false