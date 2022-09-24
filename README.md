# Pipez test cases

This project exists due to 3 reasons:

* pipez has to test Java Beans conversions, so I need some nice way of generating a lot of Java Beans with
  a reasonable `.equals` and `.hashCode` for tests
* Scala 3 pulled a rug from under me by changing the contracts when using `@BeanProperty` so I have to compile
  for 2.13 and then make Scala 3 use 2.13 artifact
* sbt-matrix (at version 0.9.0) has an annoying bug: when I used testcases as submodule with "test->compile"
  configuration it complained that it cannot find "testcases_3" even though dependenciesList showed only testcases for
 2.13

Long story short, it's a desperate attempt to workaround for dotty and sbt-matrix pulling a rug from under me.
