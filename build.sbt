lazy val V = _root_.scalafix.sbt.BuildInfo
inThisBuild(
  List(
    organization := "net.exoego",
    homepage := Some(url("https://github.com/com/example")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        id = "exoego",
        name = "TATSUNO Yasuhiro",
        email = "ytatsuno.jp@gmail.com",
        url = url("https://www.exoego.net")
      )
    ),
    addCompilerPlugin(scalafixSemanticdb),
    scalacOptions ++= List(
      "-Yrangepos",
      "-P:semanticdb:synthetics:on"
    ),
    skip in publish := true
  )
)

lazy val rules = project.settings(
  moduleName := "scalafix-performance-rule",
  libraryDependencies += "ch.epfl.scala" %% "scalafix-core" % V.scalafixVersion
)

lazy val input = project.settings(
  skip in publish := true
)

lazy val output = project.settings(
  skip in publish := true
)

lazy val tests = project
  .settings(
    skip in publish := true,
    libraryDependencies += "ch.epfl.scala" % "scalafix-testkit" % V.scalafixVersion % Test cross CrossVersion.full,
    compile.in(Compile) :=
      compile.in(Compile).dependsOn(compile.in(input, Compile)).value,
    scalafixTestkitOutputSourceDirectories :=
      sourceDirectories.in(output, Compile).value,
    scalafixTestkitInputSourceDirectories :=
      sourceDirectories.in(input, Compile).value,
    scalafixTestkitInputClasspath :=
      fullClasspath.in(input, Compile).value
  )
  .dependsOn(rules)
  .enablePlugins(ScalafixTestkitPlugin)
