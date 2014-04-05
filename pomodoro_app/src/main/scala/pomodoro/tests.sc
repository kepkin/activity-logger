package pomodoro

import scala.concurrent.duration._
import rx.lang.scala.{Observable, Observer, Subscription, Subscriber}


object tests {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val logger = new Subscriber[Long] {
    override def onNext(x: Long) {
      println(x)
    }
    override def onError(error: Throwable) {
      println(error)
    }

    override def onCompleted() {
    }

  }                                               //> logger  : rx.lang.scala.Subscriber[Long] = pomodoro.tests$$anonfun$main$1$$a
                                                  //| non$1@422f3d17

  val timer = new RxPomodoroTimer                 //> timer  : pomodoro.RxPomodoroTimer = pomodoro.RxPomodoroTimer@19d449fc
  timer.GetObservable().subscribe(logger)         //> res0: rx.lang.scala.Subscription = rx.lang.scala.Subscription$$anon$2@47520a
                                                  //| 06
  timer.StartPomodoro()                           //> java.lang.RuntimeException: Error occurred when trying to propagate error to
                                                  //|  Observer.onError
                                                  //| 	at rx.observers.SafeSubscriber._onError(SafeSubscriber.java:171)
                                                  //| 	at rx.observers.SafeSubscriber.onError(SafeSubscriber.java:93)
                                                  //| 	at rx.observers.SafeSubscriber.onNext(SafeSubscriber.java:108)
                                                  //| 	at rx.lang.scala.Subscriber$$anon$2.onNext(Subscriber.scala:38)
                                                  //| 	at pomodoro.RxPomodoroTimer$$anonfun$StartPomodoro$1.apply(Package.scala
                                                  //| :173)
                                                  //| 	at pomodoro.RxPomodoroTimer$$anonfun$StartPomodoro$1.apply(Package.scala
                                                  //| :173)
                                                  //| 	at scala.collection.immutable.List.foreach(List.scala:318)
                                                  //| 	at pomodoro.RxPomodoroTimer.StartPomodoro(Package.scala:173)
                                                  //| 	at pomodoro.tests$$anonfun$main$1.apply$mcV$sp(pomodoro.tests.scala:25)
                                                  //| 
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupp
                                                  //| Output exceeds cutoff limit.
  
}