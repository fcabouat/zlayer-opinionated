package mypackage.impl

import zio._
import zio.logging._

trait Service2Live extends Service2Delegate {
  val loggingService: Logging.Service

  def logInfo(str: String): UIO[Unit] = loggingService.logger.log(LogLevel.Info)(str)

  override def bar(param: Int): Task[Int] = for {
    delegatedResult <- super.bar(param)
    _ <- logInfo(s"called bar(${param.toString}), with result : ${delegatedResult}")
  } yield delegatedResult
}
