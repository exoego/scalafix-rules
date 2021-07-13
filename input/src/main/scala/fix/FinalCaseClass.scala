/*
rule = FinalCaseClass
*/
package fix

case class Bad1(){}
case class Bad2(foo: String)

final case class Good1()
final case class Good2(foo: String)
