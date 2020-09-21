package fix

import scalafix.v1._

import scala.meta._

class UseSizeIs extends SemanticRule("UseSizeIs") {

  private val classes    = Set("scala/collection/IterableOnceOps#", "scala/collection/SeqOps#")
  private val badMethods = Set("size", "length")
  private val operators  = List(">=", ">", "==", "!=", "<", "<=")

  override def fix(implicit doc: SemanticDocument): Patch = {
    operators
      .map { operator =>
        doc.tree.collect {
          case Term.ApplyInfix(Term.Select(receiver, method), Term.Name(`operator`), _, _)
              if isTargetMethod(method) && notScalaIterator(receiver) =>
            Patch.replaceTree(method.asInstanceOf[Tree], "sizeIs")
        }.asPatch
      }
      .foldLeft(Patch.empty)(_ + _)
  }

  private def isTargetMethod(method: Term.Name)(implicit doc: SemanticDocument): Boolean = {
    badMethods(method.value) && classes(method.parent.get.symbol.owner.value)
  }

  private def notScalaIterator(receiver: Term)(implicit doc: SemanticDocument): Boolean = {
    // Iterator does not support sizeIs
    !receiver.symbol.info.exists(_.signature.toString().contains(": Iterator["))
  }

}
