import sbt._
object Dependencies{
lazy val sprayJson = "io.spray"%%"spray-json"%"1.3.6"


lazy val configVersion="1.4.1"
lazy val config= "com.typesafe" % "config" %  configVersion

def compileDependencies:Seq[ModuleID]=Seq(sprayJson,config)
}
