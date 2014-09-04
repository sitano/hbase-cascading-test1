import sbt._
import Keys._

object HBaseCascadingTest1ProjectBuild extends Build {
  import BuildSettings._

  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  lazy val project = Project("hbase-cascading-test1", file("."))
    .settings(buildSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "org.apache.hadoop" % "hadoop-core" % "1.2.1",
        "cascading" % "cascading-core" % "2.5.5",
        "cascading" % "cascading-local" % "2.5.5",
        "cascading" % "cascading-hadoop" % "2.5.5",
        "com.twitter" %% "scalding-core" % "0.11.2",
        "org.apache.hbase" % "hbase" % "0.94.11",
      // This is override to handle old hbase binding
        "org.slf4j" % "slf4j-api" % "1.7.2" % "test",
        "org.slf4j" % "slf4j-log4j12" % "1.7.2" % "test",
      // Test
        "org.specs2" %% "specs2" % "2.4.2" % "test" exclude("org.scalacheck", "scalacheck_2.10")
      )
    )
}
