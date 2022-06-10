package ActorMultiply

import AkkaMultiply.Listener.{CompletedWork, DoneMsg}
import akka.actor.Actor

import scala.concurrent.duration.DurationLong

class Listener(message: Int) extends Actor {

  val iT: Long = System.currentTimeMillis
  var filas = 0
  var r = Array.ofDim[Array[Int]](message)
  

  def receive = {
    case DoneMsg(message) => println(message + "\n")
    case CompletedWork(row, work) =>
      r(row) = work
      filas += 1
      if (filas == r.length) {
        println("All workers done. Calculation time: %s"
          .format((System.currentTimeMillis - iT).millis))
        // se imprime el resultado del producto de matrices
        print("Matriz Total\n")
        for {
          i <- 0 until r.length
          j <- 0 until r.length
        } println(s"($i)($j) = ${r(i)(j)}")
      }
  }

}
