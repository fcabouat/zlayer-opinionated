package mypackage

import zio._

trait Service1 extends Serializable {
  def foo: Task[Unit]
}

object Service1 {
  def foo: RIO[HasService1, Unit] = RIO.accessM(_.get.foo)
}
