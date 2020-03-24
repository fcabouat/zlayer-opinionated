package mypackage.impl

import mypackage._
import zio._
import zio.clock._
import zio.duration._
import zio.random._

trait Service1Live extends Service1 {
  val clockService: Clock.Service
  val randomService: Random.Service

  override def foo: Task[Unit] =
    for {
      randomSleep <- randomService.nextInt(1000)
      _ <- clockService.sleep((5000 + randomSleep).millis)
    } yield ()
}
