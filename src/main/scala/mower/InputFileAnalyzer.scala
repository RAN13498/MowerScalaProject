package mower

import java.io.File
import scala.io.Source

class InputFileAnalyzer {

  /**
   * Analyse the input file to extract the first line and define it as a Lawn object
   * @param file input file containing the mower,lawn and commands information
   * @return Lawn or None
   */
  def getTheLawn(file:File): Option[Lawn] = {
    val input = Source.fromFile(file).getLines().toList
    if(!input.isEmpty) {
      val LawnArray = input(0).split(" ")
      if(LawnArray.length != 2)
        None
      else
        Some(Lawn(Coordinates(LawnArray(0).toInt, LawnArray(1).toInt)))
    }
    else
      None
  }

  /**
   * Analyse the input file to extract all the odd lines and convert them into Mower objects.
   * @param file input file containing the mower,lawn and commands information
   * @return Mower
   */
  def getMowers(file: File): List[Mower] = {
    val input = Source.fromFile(file).getLines().toList
    if(!input.isEmpty && input.length > 1) {
      // Get the mower lines from input file (only odd lines)
      val mowerLines = input.zipWithIndex.filter{
        case (_, index) => index != 0 && index % 2 != 0
      }.map(_._1)

      // Get the mower parameters from each line and return a Mower object
      mowerLines.filter(_.split(" ").length == 3).map(line => {
        val mowerParams = line.split(" ")
        Mower(Coordinates(mowerParams(0).toInt, mowerParams(1).toInt), Direction.withName(mowerParams(2)))
      })
    } else List.empty[Mower]
  }

  /**
   * Analyse the input file to extract all the even lines and convert them into List of commands.
   * @param file input file containing the mower,lawn and commands information
   * @return List of commands
   */
  def getCommands(file: File): List[List[Command.Value]] = {
    val input = Source.fromFile(file).getLines().toList
    if(!input.isEmpty && input.length > 2) {
      // Get the commands from the input file (only the even lines without the line '0')
      val commandLines = input.zipWithIndex.filter{
        case (item, index) => index != 0 && index % 2 == 0
      }.map(_._1)

      // Return the Mower commands as a list
      commandLines.map(line => line.toList.map(cmd => Command.withName(cmd.toString)))
    }
    else
      List.empty[List[Command.Value]]
  }
}
