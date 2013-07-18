name := """hakkyhour"""

version := "0.1"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.9" % "test->default"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")
