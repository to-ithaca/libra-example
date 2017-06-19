lazy val compilerSettings = Seq()

lazy val commonResolvers = Seq(
  Resolver.sonatypeRepo("releases"),
  "fthomas" at "https://dl.bintray.com/fthomas/maven/"
)

lazy val buildSettings = Seq(
  scalaOrganization := "org.typelevel",
  scalaVersion := "2.12.2",
  name := "libra-example",
  version := "0.1.0-SNAPSHOT"
)

lazy val commonSettings = Seq(
  resolvers ++= commonResolvers,
  libraryDependencies ++= Seq(
    "com.github.to-ithaca" %% "libra" % "0.2.0"
  )
) ++ compilerSettings

lazy val root = (project in file(".")).settings(
  buildSettings,
  commonSettings
)
