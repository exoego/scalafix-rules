package fix

import scalafix.v1._

import scala.meta._

class UseSizeIs extends SemanticRule("UseSizeIs") {

  private val classes     = Set("scala/collection/IterableOnceOps#", "scala/collection/SeqOps#")
  private val badMethods  = Set("size", "length")
  private val comparators = Set(">=", ">", "==", "!=", "<", "<=")

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case s @ Term.ApplyInfix(Term.Select(receiver, method), Term.Name(operator), _, _)
          if isComparing(operator) && isTargetMethod(method) && notScalaIterator(receiver) =>
        Patch.replaceTree(method.asInstanceOf[Tree], "sizeIs")
    }.asPatch
  }

  private def isComparing(operator: String): Boolean = comparators(operator)

  private def isTargetMethod(method: Term.Name)(implicit doc: SemanticDocument): Boolean = {
    badMethods(method.value) && classes(method.parent.get.symbol.owner.value)
  }

  private def notScalaIterator(receiver: Term)(implicit doc: SemanticDocument): Boolean = {
    // Iterator does not support sizeIs
    !receiver.symbol.info.exists(s => {
      val sig = s.signature.toString()
      // Empty on Scala 3
      sig.isEmpty || sig.contains(": Iterator[")
    })
  }

}
