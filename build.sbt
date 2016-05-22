name := """app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// This line makes the asset not found problem disappear,
// however I'm not sure if ws or cache libraries do not play a role in this
unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

fork in run := true
