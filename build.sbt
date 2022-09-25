import commandmatrix.extra._
import Settings._

Global / excludeLintKeys += ideSkipProject
val noJsNoNativeInIde =
  MatrixAction.ForPlatforms(VirtualAxis.js, VirtualAxis.native).Configure(_.settings(ideSkipProject := true))

// 2.13 only since apparently my way of doing Java Beans (@BeanProperty) have slightly different behavior in 3.x
// because of https://github.com/lampepfl/dotty/issues/11972
val testCases = projectMatrix
  .in(file("testcases"))
  .someVariations(
    List(scala2_13version),
    List(VirtualAxis.jvm, VirtualAxis.js, VirtualAxis.native)
  )(noJsNoNativeInIde)
  .settings(name := "pipez-testcases")
  .settings(commonSettings)
  .settings(publishSettings)

val testCases3 = projectMatrix
  .in(file("testcases-3"))
  .someVariations(
    List(scala3version),
    List(VirtualAxis.jvm, VirtualAxis.js, VirtualAxis.native)
  )(noJsNoNativeInIde)
  .settings(name := "pipez-testcases-scala3")
  .settings(commonSettings)
  .settings(publishSettings)

val root = project
  .in(file("."))
  .enablePlugins(GitVersioning)
  .settings(name := "pipez-testcases-build")
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)
  .settings(noPublishSettings: _*)
  .aggregate(testCases.projectRefs ++ testCases3.projectRefs: _*)
