import com.timushev.sbt.updates.UpdatesKeys
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import org.scalastyle.sbt.ScalastylePlugin
import sbt.Keys._

// ---------------------

// Settings
// ---------------------

organization := "co.com.ach.exploda.consultas.pila"

name :="mapeador"

scalaVersion := Versions.scala

autoScalaLibrary := false

autoCompilerPlugins := true

resolvers ++= Seq(
  "S4N Nexus Snapshots" at "http://nexus.seven4n.com/content/repositories/ach-binaries-snapshots/", // Por co.com.ach.exploda.*/*-snapshot
  "S4N Nexus" at "http://nexus.seven4n.com/content/repositories/ach-binaries/", // Por co.com.ach.exploda.*/*-snapshot
  Resolver.jcenterRepo // Por ficus
)

/**
 * @see https://github.com/scala/scala/tree/2.11.x/src/compiler/scala/tools/nsc/settings
 */
scalacOptions in ThisBuild ++= Seq(
  s"-target:jvm-${Versions.jdk}", "-encoding", "UTF-8", "-feature", "-unchecked", "-deprecation", "-Xlint", "-Ywarn-dead-code", "-Ywarn-value-discard",
  "-Ywarn-numeric-widen"
)

/**
 * @see https://github.com/scala/scala/tree/2.11.x/src/compiler/scala/tools/nsc/settings
 */
scalacOptions in Test += "-Yrangepos"

libraryDependencies ++= {
  import Dependencies._
  achLibs ++ testLibs
}

// compileOrder in Compile := CompileOrder.JavaThenScala


// -----------------------
// Custom settings
// -----------------------

val customCompile: TaskKey[Unit] = taskKey[Unit]("customCompile")
customCompile := customCompileInit.value

val customTest: TaskKey[Unit] = taskKey[Unit]("customTest")
customTest := customTestInit.value

(compile in Compile) <<= (compile in Compile).dependsOn(customCompile)
(test in Test) <<= (test in Test).dependsOn(customTest)

/**
 * Tareas secuenciales ejecutadas al ejecutar compile.
 */
def customCompileInit: sbt.Def.Initialize[Task[Unit]] = {
  Def.sequential(
    UpdatesKeys.dependencyUpdates.in(Compile).toTask,
    ScalariformKeys.format.in(Compile).toTask,
    ScalastylePlugin.scalastyle.in(Compile).toTask("")
  )
}

/**
 * Tareas secuenciales ejecutadas al ejecutar test.
 */
def customTestInit: sbt.Def.Initialize[Task[Unit]] = {
  Def.sequential(
    UpdatesKeys.dependencyUpdates.in(Test).toTask,
    ScalariformKeys.format.in(Test).toTask,
    ScalastylePlugin.scalastyle.in(Test).toTask("")
  )
}

