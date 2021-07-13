# Scalafix rules for scalafix-my-rules

To develop rule:
```
sbt ~tests/test
```

# List of rules

- [UseSizeIs](https://github.com/exoego/scalafix-rules/blob/master/rules/src/main/scala/fix/UseSizeIs.scala): Rewrite `seq.size <= n` into `seq.sizeIs <= n`. See [IterableOps#sizeIs](https://www.scala-lang.org/api/current/scala/collection/IterableOps.html#sizeIs:scala.collection.IterableOps.SizeCompareOps)
  


