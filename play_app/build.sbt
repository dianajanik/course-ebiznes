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

name := """play-scala-slick-example"""

version := "2.6.x"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.3"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
libraryDependencies += "org.xerial"        %  "sqlite-jdbc" % "3.21.0"
//libraryDependencies += "slick.driver.SQLiteDriver" %%



libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += specs2 % Test

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"