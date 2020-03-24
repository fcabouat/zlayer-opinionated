import mypackage.impl._
import zio._
import zio.clock._
import zio.logging.Logging
import zio.logging.Logging.Logging
import zio.random._

package object mypackage {
  // Dependencies types
  type HasService1 = Has[Service1]
  type HasService2 = Has[Service2]

  // Other shared types
  type CustomQueue = Queue[(Task[Any], Promise[Throwable, Any])]

  // Exceptions
  final case object CustomException1 extends Exception("My custom exception 1 !")
  final case object CustomException2 extends Exception("My custom exception 2 !")

  // Env & env creation helpers
  object Service1Env {
    val any: ZLayer[HasService1, Nothing, HasService1] =
      ZLayer.requires[HasService1]

    val live: ZLayer[Clock with Random, Nothing, HasService1] =
      ZLayer.fromServices[Clock.Service, Random.Service, Service1]((clock, random) =>
        new Service1Live {
          override val clockService: Clock.Service   = clock
          override val randomService: Random.Service = random
        }
      )

    def mkLive(
        clockEnv: TaskLayer[Clock],
        randomEnv: TaskLayer[Random]
    ): TaskLayer[HasService1] = clockEnv ++ randomEnv >>> live
  }

  object Service2Env {
    val any: ZLayer[HasService2, Nothing, HasService2] =
      ZLayer.requires[HasService2]

    val live: ZLayer[Clock with Logging, Nothing, HasService2] =
      ZLayer.fromServices[Clock.Service, Logging.Service, Service2]((clock, logging) =>
        new Service2Live {
          override val loggingService: Logging.Service = logging
        }
      )

    def mkLive(
        clockEnv: TaskLayer[Clock],
        loggingEnv: TaskLayer[Logging]
    ): TaskLayer[HasService2] = clockEnv ++ loggingEnv >>> live
  }
}
