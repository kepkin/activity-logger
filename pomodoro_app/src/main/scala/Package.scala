package pomodoro

import scala.collection.mutable.ListBuffer
import scala.collection.JavaConverters._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.swing._
import scala.util.{ Try, Success, Failure }
import scala.swing.event._
import swing.Swing._
import javax.swing.UIManager
import Orientation._
import java.awt.{ Graphics, Graphics2D, RenderingHints, GridLayout, BorderLayout, Color, Dimension, Rectangle, Polygon, Point }
import javax.swing.{ JComponent, JFrame, JLabel, Timer, SwingUtilities }
import math.abs
import javax.swing.Action
import java.awt.event.ActionListener
import rx.subscriptions.CompositeSubscription
import rx.lang.scala.Observable
import rx.lang.scala.Subscription
import scala.concurrent.duration._
import com.sun.awt.AWTUtilities

object PomodoroApp extends SimpleSwingApplication with ConcreteSwingApi {

  {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    } catch {
      case t: Throwable =>
    }
  }
  
  val timerFrame = new PomTimeLabel
  
  def top = new MainFrame {

    /* gui setup */

    title = "Query Wikipedia"
    minimumSize = new Dimension(900, 600)

    val button = new Button("Get") {
      //      icon = new javax.swing.ImageIcon(javax.imageio.ImageIO.read(this.getClass.getResourceAsStream("/suggestions/wiki-icon.png")))
    }
    val searchTermField = new TextField
    val suggestionList = new ListView(ListBuffer[String]())
    val status = new Label(" ")
    val editorpane = new EditorPane {
      import javax.swing.border._
      border = new EtchedBorder(EtchedBorder.LOWERED)
      editable = false
      peer.setContentType("text/html")
    }

    contents = new BoxPanel(orientation = Vertical) {
      border = EmptyBorder(top = 5, left = 5, bottom = 5, right = 5)
      contents += new BoxPanel(orientation = Horizontal) {
        contents += new BoxPanel(orientation = Vertical) {
          maximumSize = new Dimension(240, 900)
          border = EmptyBorder(top = 10, left = 10, bottom = 10, right = 10)
          contents += new BoxPanel(orientation = Horizontal) {
            maximumSize = new Dimension(640, 30)
            border = EmptyBorder(top = 5, left = 0, bottom = 5, right = 0)
            contents += searchTermField
          }
          contents += new ScrollPane(suggestionList)
          contents += new BorderPanel {
            maximumSize = new Dimension(640, 30)
            add(button, BorderPanel.Position.Center)
          }
        }
//        contents += t
      }
      contents += status
    }

    val eventScheduler = SwingEventThreadScheduler()

    /**
     * Observables
     * You may find the following methods useful when manipulating GUI elements:
     *  `myListView.listData = aList` : sets the content of `myListView` to `aList`
     *  `myTextField.text = "react"` : sets the content of `myTextField` to "react"
     *  `myListView.selection.items` returns a list of selected items from `myListView`
     *  `myEditorPane.text = "act"` : sets the content of `myEditorPane` to "act"
     */

    def onNext(x: Button) {
      timerFrame.start(13 seconds)
    }
    
    button.clicks.observeOn(eventScheduler).subscribe(onNext(_))
  }

}

class PomTimeLabel extends Frame {
  private val form = new java.text.SimpleDateFormat("HH:mm:ss")
//  def current = form.format(java.util.Calendar.getInstance().getTime)

  peer.setUndecorated(true)
  val text = new Label("0 seconds.fdsfsdfsdffds")
  contents = text
  size = (100, 50)
  AWTUtilities.setWindowOpacity(peer, 0.8.toFloat)
    
  val eventScheduler = SwingEventThreadScheduler()
  
  def start(x: Duration) {
    this.visible = true
	  val ticks = Observable.interval(1 second)
	  ticks.observeOn(eventScheduler).take(x.toSeconds.toInt).subscribe(last => {
	    val a = x - (last seconds)
	    text.text = a.toString()
	  })
  }
} 

trait ConcreteSwingApi extends SwingApi {
  type ValueChanged = scala.swing.event.ValueChanged
  object ValueChanged {
    def unapply(x: Event) = x match {
      case vc: ValueChanged => Some(vc.source.asInstanceOf[TextField])
      case _ => None
    }
  }

  type ButtonClicked = scala.swing.event.ButtonClicked  
  object ButtonClicked {
    def unapply(x: Event) = x match {
      case bc: ButtonClicked => Some(bc.source.asInstanceOf[Button])
      case _ => None
    }
  }
  
  type TextField = scala.swing.TextField
  type Button = scala.swing.Button
}