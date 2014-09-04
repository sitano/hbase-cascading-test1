import sbt._
import Keys._

object BuildSettings {

  lazy val repos = Seq(
    "Sonatype OSS Repo" at "https://oss.sonatype.org/content/repositories/releases",
    "Concurrent Maven Repo" at "http://conjars.org/repo"

    /*,
    "Apache HBase" at "https://repository.apache.org/content/repositories/releases",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Twitter Maven Repo" at "http://maven.twttr.com",
    "Maven Repository" at "http://mvnrepository.com/artifact/",
    "releases" at "http://oss.sonatype.org/content/repositories/releases",
    "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
    "Sonatype OSS Repo 2" at "https://oss.sonatype.org/content/groups/scala-tools"*/)

  lazy val basicSettings = Seq[Setting[_]](
    organization  := "com.ivan",
    version       := "0.0.1",
    description   := "Sample data processing pipeline using cascading / scalding / hbase. Stream double -> (min, max, avg)",
    scalaVersion  := "2.10.4",
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers     ++= repos
  )

  import sbtassembly.Plugin._
  import AssemblyKeys._
  lazy val sbtAssemblySettings = assemblySettings ++ Seq(
    // Slightly cleaner jar name
    jarName in assembly := { name.value + "-" + version.value + ".jar" },

    // Drop these jars
    excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
      val excludes = Set(
        "ant-1.6.5.jar",
        "asm-3.1.jar",
        "objenesis-1.2.jar",
        "mockito-all-1.8.5.jar",
        "jsp-api-2.0.jar",
        "jsp-api-2.1-6.1.14.jar",
        "jsp-2.1-6.1.14.jar",
        "stax-api-1.0.1.jar",
        "jasper-compiler-5.5.12.jar",
        "minlog-1.2.jar", // Otherwise causes conflicts with Kyro (which bundles it)
        "janino-2.5.16.jar", // Janino includes a broken signature, and is not needed anyway
        "commons-beanutils-core-1.8.0.jar", // Clash with each other and with commons-collections
        "commons-beanutils-1.7.0.jar",      // "
        "servlet-api-2.4.jar",
        "servlet-api-2.5-20081211.jar"
      ) 
      cp filter { jar => excludes(jar.data.getName) }
    },

    mergeStrategy in assembly <<= (mergeStrategy in assembly) {
      (old) => {
        case "project.clj" => MergeStrategy.discard // Leiningen build files
        case x => old(x)
      }
    }
  )

  lazy val buildSettings = basicSettings ++ sbtAssemblySettings
}
