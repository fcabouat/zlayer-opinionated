package mypackage

import zio._

trait Service2 extends Serializable {
  def bar(param: Int): Task[Int]
}

object Service2 {
  def bar(param: Int): RIO[HasService2, Int] = RIO.accessM(_.get.bar(param))
}