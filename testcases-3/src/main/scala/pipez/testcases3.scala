package pipez

import scala.beans.BeanProperty

// Scala 3 changed the behavior of @BeanProperty (hide set and get from the user) so they require separate tests

final case class Bean3ManyIn private (
  @BeanProperty var a: Int,
  @BeanProperty var b: String,
  @BeanProperty var c: Long
) { def this() = this(0, "", 0L) }
final case class Bean3ManyOut private (
  @BeanProperty var a: Int,
  @BeanProperty var b: String,
  @BeanProperty var c: Long
) { def this() = this(0, "", 0L) }

// Scala 2 doesn't have 3' enums but allow for cross-compile so we have to test working with them

enum EnumIn[+T]:
  case A
  case B(b: T)
  case C(s: String) extends EnumIn[String]
enum EnumOut[+T]:
  case A
  case B(b: T)
  case C(s: String) extends EnumOut[String]
