package fix

import scalafix.v1._
import scala.meta._

final class FinalCaseClass extends SemanticRule("FinalCaseClass") {
  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      case a @ q"..$mods case class $klass(..$args)" =>
        mods.collectFirst {
          case Mod.Final() =>
        } match {
          case Some(_) => Patch.empty
          case None => Patch.addLeft(a, "final ")
        }
    }.asPatch
  }
}
