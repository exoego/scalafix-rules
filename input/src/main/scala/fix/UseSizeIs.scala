/*
rule = UseSizeIs
 */
package fix

import scala.collection.mutable
import scala.collection.immutable

object UseSizeIs {
  Seq(1, 2, 3).size > 5
  Seq(1, 2, 3).size >= 5
  Seq(1, 2, 3).size == 5
  Seq(1, 2, 3).size != 5
  Seq(1, 2, 3).size <= 5
  Seq(1, 2, 3).size < 5

  Seq(1, 2, 3).length > 10
  Seq(1, 2, 3).length >= 10
  Seq(1, 2, 3).length == 10
  Seq(1, 2, 3).length != 10
  Seq(1, 2, 3).length <= 10
  Seq(1, 2, 3).length < 10

  immutable.Seq(1, 2, 3).size > 5
  mutable.Seq(1, 2, 3).size > 5
  Iterable.single(1).size > 5
  Map.empty.size > 5

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
