package com.tuvalabs.muhtasari.inputprocessor

//import net.liftweb.json._

/**
 * Created with IntelliJ IDEA.
 * User: rushit
 * Date: 9/15/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */


case class InputData(url: String, params: Array[String])

class InputDataParser {
  def extractInputData(request_ : String): InputData = {
    //val jsn = JsonParser.parse(request_)
    //val obj = jsn.extract[InputData]
    val x = new InputData("url",new Array[String](0) )
    x
  }
}
