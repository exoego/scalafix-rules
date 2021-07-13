# Scalafix-Rules

Some useful linters/autofix for Scala.

- autofix
    - `FinalCaseClass`: Add `final` modifier to case classes to prevent being inherited by non-case class.
    - `UseSizeIs`: Rewrite `seq.size <= n` into `seq.sizeIs <= n`. See [IterableOps#sizeIs](https://www.scala-lang.org/api/current/scala/collection/IterableOps.html#sizeIs:scala.collection.IterableOps.SizeCompareOps)

# Development

To develop rule:
```
sbt ~tests/test
```

