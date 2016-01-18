name := "akka-http-proto"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += Resolver.bintrayRepo("hseeberger", "maven")


resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= List(
  Library.akkaStream,
  Library.akkaHttpCore,
  Library.akkaHttp,
  Library.akkaHttpJson4s,
  Library.config,
  Library.javaxsWsRs,
  Library.json4sJackson,
  Library.json4sNative,
  Library.logBack,
  Library.scalaMeter,
  Library.scalaTest,
  Library.swaggerCore,
  Library.scalacheck,
  Library.commonsCodec,
  Library.commonsLang,
  Library.scalaLogging,
  Library.swaggerCore



)

testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")

logBuffered := false

parallelExecution in Test := false