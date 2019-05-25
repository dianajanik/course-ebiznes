//name := "play_app"
//
//version := "1.0"
//
//lazy val `play_app` = (project in file(".")).enablePlugins(PlayScala)
//
//resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
//
//resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
//
//scalaVersion := "2.12.2"
//
//libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
//
//unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
//
//

name := "play_appp"

version := "1.0"

lazy val `play_app` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.2"
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

libraryDependencies ++= Seq( ehcache , ws , specs2 % Test , guice, evolutions )

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.15"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

