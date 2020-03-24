package mypackage.impl

import mypackage._
import zio._

trait Service2Delegate extends Service2 {

  override def bar(param: Int): Task[Int] = ZIO.succeed(2 * param)
}
