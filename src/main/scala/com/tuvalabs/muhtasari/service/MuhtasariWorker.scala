package com.tuvalabs.muhtasari.service

import java.net.Socket
import actors.Actor
import java.io.{InputStreamReader, LineNumberReader, OutputStreamWriter}
import com.tuvalabs.muhtasari.extractor.GooseConnector

/**
 * Created with IntelliJ IDEA.
 * User: rushit
 * Date: 9/15/12
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
case class Idle(worker: ServerWorker)
case class Connection(socket: Socket, id: Int)

class ServerWorker(val id: Int, val dispatcher: Dispatcher) extends Actor
{
  def act()
  {
    loop
    {
      react
      {
        case Connection(socket, id) =>
          handleConnection(socket)
          socket.close()
          dispatcher ! Idle(this)
      }
    }
  }

  override def hashCode(): Int = id

  override def equals(other: Any): Boolean =
    other match
    {
      case that: ServerWorker => this.id == that.id
      case _ => false
      }

    def handleConnection(socket: Socket) =
    {
      val out = socket.getOutputStream
      val writer = new OutputStreamWriter(out)

      val in = socket.getInputStream
      val reader = new LineNumberReader(new InputStreamReader(in))

      val x = reader.readLine()
      val goose = new GooseConnector
      writer.write(goose.getContent(x))
      writer.flush()
    }
}