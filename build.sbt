lazy val root = (project in file(".")).
  settings(
    name := "matrix",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.4"
  )

libraryDependencies ++= Seq(
      "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
    )
