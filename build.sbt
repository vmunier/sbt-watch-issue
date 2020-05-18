lazy val firstProject = project.settings(
  compile in Compile := ((compile in Compile) dependsOn (secondProject / Compile / compile)).value
)

lazy val secondProject = project
