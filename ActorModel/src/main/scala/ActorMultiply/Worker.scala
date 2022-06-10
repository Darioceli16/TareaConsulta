package ActorMultiply

import AkkaMultiply.Listener.{CompletedWork, DoneMsg}
import AkkaMultiply.Worker.{ExecuteMultiply, WhatToMultiply}
import akka.actor.{Actor, ActorRef}

class Worker (mensaje: Int, listenerActor: ActorRef) extends Actor {

  var workerID = mensaje
  var x1 = Array[Int]()
  var y1 = Array[Array[Int]]()
  var resultado = Array[Int]()

  def receive = {
    case WhatToMultiply(matriz1, matriz2) =>
      x1 = matriz1
      y1 = matriz2
      resultado = Array.ofDim[Int](x1.length)
    case ExecuteMultiply =>
      for (i <- 0 until x1.length) {
        var temp = 0
        for (j <- 0 until y1.length) {

          temp += x1(j) * y1(j)(i)
        }
        //println(temp)
        resultado(i) = temp
      }
      listenerActor ! DoneMsg(s"Worker #$workerID is done")
      listenerActor ! CompletedWork(mensaje, resultado)
  }
}
