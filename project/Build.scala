import sbt._
import Keys._

object HBaseCascadingTest1ProjectBuild extends Build {
  import Dependencies._
  import BuildSettings._

  // scalaVersion := "2.10.4"

  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  lazy val project = Project("hbase-cascading-test1", file("."))
    .settings(buildSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        Libraries.scaldingCore,
        Libraries.hadoopCore,
        Libraries.specs2
      )
    )
}
