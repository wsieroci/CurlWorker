name := "CurlWorker"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "com.typesafe.akka" %% "akka-actor" % "2.5.0",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test")
    