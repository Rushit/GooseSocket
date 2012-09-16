package com.tuvalabs.muhtasari.service

import actors.Actor

/**
 * Created with IntelliJ IDEA.
 * User: rushit
 * Date: 9/15/12
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
class Dispatcher() extends Actor
{
  import scala.collection.mutable.{Map, ListBuffer}
  import _root_.java.util.Random

  val idleWorkers = new ListBuffer[ServerWorker]
  val busyWorkers = Map[Int, ServerWorker]()
  val rng = new Random()

  val totalServer = Runtime.getRuntime().availableProcessors() * 4 + 1
  println("Total Thread running : " + totalServer)
  for (i <- 1 to totalServer)
  {
    val worker = new ServerWorker(i, this)
    worker.start()
    idleWorkers += worker

  }

  def act()
  {
    loop
    {
      react
       {
          case Idle(worker) =>
            busyWorkers -= worker.id
            idleWorkers += worker

          case conn: Connection =>
            val worker =
            if (idleWorkers.length == 0)
              busyWorkers.get(rng.nextInt(busyWorkers.size)).get
            else
            {
              val aWorker = idleWorkers.remove(0)
              busyWorkers += aWorker.id -> aWorker
              aWorker
            }
            worker ! conn
       }
    }
  }
}