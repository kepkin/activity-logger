package pomodoro

import scala.concurrent.duration._
//import rx.Observable
import rx.lang.scala.{Observable, Observer, Subscription, Subscriber}


object tests {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  (2 minutes).toSeconds                           //> res0: Long = 120
  
  def lolo(observer: Subscriber[Long]) {
  observer.onNext(0)
  	observer.onCompleted()
  }                                               //> lolo: (observer: rx.lang.scala.Subscriber[Long])Unit
  
  Observable(lolo)                                //> res1: rx.lang.scala.Observable[Long] = rx.lang.scala.JavaConversions$$anon$1
                                                  //| @5b3353f
}