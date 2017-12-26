lazy val compilerSettings = Seq()

lazy val commonResolvers = Seq(
  Resolver.sonatypeRepo("releases"),
  "fthomas" at "https://dl.bintray.com/fthomas/maven/"
)

lazy val buildSettings = Seq(
  scalaVersion := "2.12.4",
  name := "libra-example",
  version := "0.1.0-SNAPSHOT"
)

lazy val commonSettings = Seq(
  resolvers ++= commonResolvers,
  libraryDependencies ++= Seq(
    "com.github.to-ithaca" %% "libra" % "0.3.0",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  )
) ++ compilerSettings

lazy val root = (project in file(".")).settings(
  buildSettings,
  commonSettings
)
