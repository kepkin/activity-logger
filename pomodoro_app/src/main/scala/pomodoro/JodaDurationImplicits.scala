package pomodoro

import com.github.nscala_time.time.Imports.{ Duration => JodaDuration }
import scala.concurrent.duration._

object JodaDurationImplicits {
   implicit def Dur2Dur(x: JodaDuration) : Duration = x.getStandardSeconds() seconds 
}