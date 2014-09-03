import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    "ScalaTools snapshots at Sonatype" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "Concurrent Maven Repo" at "http://conjars.org/repo" // For Scalding, Cascading etc
  )

  object V {
    val scalding  = "0.9.1"
    val hadoop    = "1.1.2"
    val specs2    = "2.3.11"
  }

  object Libraries {
    val scaldingCore = "com.twitter"                %%  "scalding-core"       % V.scalding
    val hadoopCore   = "org.apache.hadoop"          %   "hadoop-core"         % V.hadoop       % "provided"

    val specs2       = "org.specs2"                 %% "specs2"               % V.specs2       % "test"
  }
}
