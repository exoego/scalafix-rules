package fix

import scala.collection.mutable
import scala.collection.immutable

object UseSizeIs {
  Seq(1, 2, 3).sizeIs > 5
  Seq(1, 2, 3).sizeIs >= 5
  Seq(1, 2, 3).sizeIs == 5
  Seq(1, 2, 3).sizeIs != 5
  Seq(1, 2, 3).sizeIs <= 5
  Seq(1, 2, 3).sizeIs < 5

  Seq(1, 2, 3).sizeIs > 10
  Seq(1, 2, 3).sizeIs >= 10
  Seq(1, 2, 3).sizeIs == 10
  Seq(1, 2, 3).sizeIs != 10
  Seq(1, 2, 3).sizeIs <= 10
  Seq(1, 2, 3).sizeIs < 10

  val x = 5
  immutable.Seq(1, 2, 3).sizeIs > x
  mutable.Seq(1, 2, 3).sizeIs > x
  Iterable.single(1).sizeIs > x
  Map.empty.sizeIs > (1 + 4)

  // Preserve
  // not size or length
  Seq(1, 2, 3).head < 10
  // Iterator does not support sizeIs
  Iterator.empty.length > 5
  Iterator.empty.size > 5
  val ie = Iterator.empty
  ie.size > 5
  // size/length not directly followed by operator
  val l = Seq(1).length
  l >= 5
  val l2 = Seq(1).size
  l2 >= 5
}
