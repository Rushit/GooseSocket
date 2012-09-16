package com.tuvalabs.muhtasari.extractor

import java.nio.channels.SocketChannel
import java.nio.ByteBuffer
import com.tuvalabs.muhtasari.inputprocessor.InputDataParser
import com.gravity.goose.{Configuration, Goose}

/**
 * Created with IntelliJ IDEA.
 * User: rushit
 * Date: 9/15/12
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
class GooseConnector
{

  def getContent(request_ : String) : String =
  {
    val in = new InputDataParser()
    //val req_ = in.extractInputData(request_)
    val result = callGoose(request_)
    result
  }

  private def callGoose( url_ : String): String = {
    try {
      val config: Configuration = new Configuration
      config.enableImageFetching = false
      val goose = new Goose(config)
      val article = goose.extractContent(url_)
      article.cleanedArticleText
    }
    catch {
      case e: Exception => {
        println("Invalid URL: " + e.toString)
        throw e
      }
    }
  }

}
