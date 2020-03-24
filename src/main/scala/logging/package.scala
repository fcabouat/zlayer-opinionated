import zio._
import zio.clock._
import zio.console.Console
import zio.logging.Logging.Logging
import zio.logging._

package object logging {

  // Env & env creation helpers
  object MyLogging {
    val live: RLayer[Console with Clock, Logging] = Logging.console((_, logEntry) => logEntry)

    def mkLive(
        clockEnv: TaskLayer[Clock],
        consoleEnv: TaskLayer[Console]
    ): TaskLayer[Logging] = clockEnv ++ consoleEnv >>> live
  }
}
