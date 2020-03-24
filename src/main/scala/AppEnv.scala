import logging._
import mypackage._
import zio._
import zio.clock._
import zio.console._
import zio.random._

object AppEnv {
  type AppEnv = HasService1 with HasService2

  def mkLive(clockLayer: TaskLayer[Clock], consoleLayer: TaskLayer[Console], randomLayer: TaskLayer[Random]): TaskLayer[AppEnv] = {
    val loggingEnv = MyLogging.mkLive(clockLayer, consoleLayer)
    val service1Env = Service1Env.mkLive(clockLayer, randomLayer)
    val service2Env = Service2Env.mkLive(clockLayer, loggingEnv)

    (service1Env ++ service2Env)
      // temporary fixes some inference error
      .asInstanceOf[TaskLayer[AppEnv]]
  }
}
