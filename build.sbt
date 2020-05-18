import com.typesafe.sbt.web.PathMapping
import com.typesafe.sbt.web.pipeline.Pipeline
import sbt.Def

lazy val fastOptPipeline = Def.taskKey[Pipeline.Stage]("Call fastOptJS")

lazy val firstProject = project
  .enablePlugins(SbtWeb)
  .settings(
    fastOptPipeline := fastOptPipelineTask.value,
    pipelineStages in Assets := Seq(fastOptPipeline),
  )

lazy val fastOptPipelineTask: Def.Initialize[Task[Pipeline.Stage]] =
  Def.taskDyn {
    val fastOptFile = (secondProject / Compile / fastOptJS).value.data
    Def.task { mappings: Seq[PathMapping] =>
      mappings :+ (fastOptFile -> fastOptFile.getName)
    }
  }

lazy val secondProject = project.enablePlugins(SbtWeb, ScalaJSPlugin)
