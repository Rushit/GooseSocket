package com.tuvalabs.muhtasari.outputprocessor

//import net.liftweb.json.Serialization._

case class OutputData(isError: Boolean , data : String)

class OutputDataParser {
  def convertJson(data_ : OutputData): String = {
    //val result = write(data_)
    val result   = ""
    result
  }
}
