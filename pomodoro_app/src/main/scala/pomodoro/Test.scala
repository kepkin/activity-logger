package pomodoro

import scala.concurrent.duration._
import rx.lang.scala.{Observable, Observer, Subscription, Subscriber}

object Test {

  def main(args: Array[String]): Unit = {

    println("Welcome to the Scala worksheet")

    val logger = new Observer[Long] {
      override def onNext(x: Long) {
        println(x)
      }
      override def onError(error: Throwable) {
        println(error)
      }

      override def onCompleted() {
      }

    }

    val timer = new RxPomodoroTimer
    timer.GetObservable().subscribe(logger)
    timer.StartPomodoro()
  }

}