package com.tuvalabs.muhtasari.service

import java.net.ServerSocket

/**
 * Created with IntelliJ IDEA.
 * User: rushit
 * Date: 9/15/12
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
class MuhtasariServer {
  val name: String = "Main"

  def run()={
    val socket = new ServerSocket(7779)
    val dispatcher = new Dispatcher()
    var i = 0

    dispatcher.start()

    while (true)
    {
      val clientConn = socket.accept()
      i+= 1
      dispatcher ! Connection(clientConn, i)
    }
  }
}

object MuhtasariService
{
  def main(args : Array[String])
  {
    println("RUNNING ON PORT : 7779")
    new MuhtasariServer().run();
  }
}

