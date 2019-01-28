lazy val commonSettings = Def.settings(
  scalaVersion := "2.12.8"
)

lazy val myWarts = project.in(file("my-warts")).settings(
  commonSettings,
  libraryDependencies ++= Seq(
    "org.wartremover" %% "wartremover" % wartremover.Wart.PluginVersion
  )
)

lazy val main = project.in(file("main")).settings(
  commonSettings,
  wartremoverWarnings += Wart.custom("mywarts.Unimplemented"),
  wartremoverClasspaths ++= {
    (fullClasspath in (myWarts, Compile)).value.map(_.data.toURI.toString)
  }
)
