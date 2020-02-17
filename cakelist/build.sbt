val ScalatraVersion = "2.7.0-RC1"

organization := "com.oleksandrah"

name := "CakeList"

version := "1.0.0"

scalaVersion := "2.11.12"

//val scalatraVersion = "2.4.0-RC2-2"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.19.v20190610" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "com.typesafe.slick" %% "slick" % "3.2.0",
  "com.h2database" % "h2" % "1.4.196",
  "com.mchange" % "c3p0" % "0.9.5.2"

)


enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
