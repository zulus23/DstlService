name := "play-scala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.pac4j"%"play-pac4j"%"2.5.0",
  "org.pac4j"%"pac4j-http"%"1.9.2",
  "org.pac4j"%"pac4j-jwt"%"1.9.2"  exclude("commons-io" , "commons-io"),
  "org.pac4j"%"pac4j-sql"%"1.9.2",
  "com.typesafe.play" % "play-cache_2.11" % "2.5.8",
  "commons-io" % "commons-io" % "2.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

resolvers ++= Seq(Resolver.mavenLocal, "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases", "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/")

routesGenerator := InjectedRoutesGenerator