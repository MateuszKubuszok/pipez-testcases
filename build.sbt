import commandmatrix.extra._
import Settings._

// 2.13 only since apparently my way of doing Java Beans (@BeanProperty) have slightly different behavior in 3.x
// because of https://github.com/lampepfl/dotty/issues/11972
val testCases = projectMatrix
  .in(file("testcases"))
  .allVariations(
    List(scala2_13version),
    List(VirtualAxis.jvm, VirtualAxis.js, VirtualAxis.native)
  )
  .settings(name := "pipez-testcases")
  .settings(commonSettings)
  .settings(publishSettings)

val root = project
  .in(file("."))
  .enablePlugins(GitVersioning)
  .settings(name := "pipez-testcases-build")
  .settings(commonSettings: _*)
  .settings(publishSettings: _*)
  .settings(noPublishSettings: _*)
  .aggregate(testCases.projectRefs: _*)
