# sbt-watch-issue

There is no issue with SBT 1.2.8. The following issue is only present with SBT 1.3.x (tested with 1.3.0 and 1.3.10).

When running the following commands using SBT 1.3.10, I would expect `compile` to _not_ run again when secondProject's Scala sources change:
```bash
$ sbt
> project firstProject
> ~compile
# When updating Main.scala in firstProject, compile is being run again as expected.
# However, when updating Main.scala in secondProject, compile is also being run, whereas it should not,
# since the secondProject's Scala sources are not part of firstProject's watchSources.
```
