import java.io.{PrintWriter, StringWriter}

import AppEnv.AppEnv
import mypackage._
import zio._
import zio.console._
import zio.clock._
import zio.random._

object Main extends App {

  val program: RIO[AppEnv, Unit] = for {
    _ <- Service1.foo
    _ <- Service2.bar(10)
  } yield ()

  val programWithDepedenciesResolved: Task[Unit] = program.provideLayer(AppEnv.mkLive(Clock.live, Console.live, Random.live))

  override def run(args: List[String]): URIO[ZEnv, Int] =
    programWithDepedenciesResolved
      .catchAll {
        case e: Exception =>
          val sw = new StringWriter()
          e.printStackTrace(new PrintWriter(sw))
          putStrLn(sw.toString)
      }.as(0)
}
