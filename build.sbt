name := "zlayer-opinionated"
version := "0.1"
scalaVersion := "2.13.1"

val ZIOVersion            = "1.0.0-RC18-2"
val ZIOLoggingVersion     = "0.2.5"

val ScalaTestVersion = "3.2.0-M2"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked"
)

libraryDependencies ++= Seq(
  // zio
  "dev.zio" %% "zio"              % ZIOVersion,
  "dev.zio" %% "zio-logging"      % ZIOLoggingVersion
)

// Test dependencies
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % ScalaTestVersion % "test"
)
