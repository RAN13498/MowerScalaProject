package mower

import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

object Simulate extends App {
  //Allow user to choose a file from his directory
  var chooser=new JFileChooser()
  chooser.setCurrentDirectory(new java.io.File("."))
  chooser.setDialogTitle("Please choose an input file")

  // Allow user to choose only ".txt" files
  val filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text")
  chooser.setFileFilter(filter)
  chooser.setAcceptAllFileFilterUsed(false)

  try{
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      val inputFile=chooser.getSelectedFile
      // Create a new fileAnalyzer instance
      val fileAnalyse = new InputFileAnalyzer()

      // Get the Lawn information
      val ourLawn = fileAnalyse.getTheLawn(inputFile)

      ourLawn match{
        case Some(lawn)=>
          // Get mowers information
          val mowers=fileAnalyse.getMowers(inputFile)
          // Get commands information
          val commands=fileAnalyse.getCommands(inputFile)

          mowers.zipWithIndex.foreach{
            case (mower,index) if commands.length >= index +1 =>  //When there is a list of command for the mower
              // Create a Mower Position Management instance
              val mowerManagement = new MowerPositionManagement()
              // Apply the commands one by one to the Mower and get its last position and direction
              val moveMower = commands(index).foldLeft(mower)((accumulator, command) => mowerManagement.moveMower(accumulator, lawn, command))
              // Print the last position and direction of each mower
              println(s"Tondeuse ${index+1} : ${moveMower.coordinates.x} ${moveMower.coordinates.y} ${moveMower.direction} ")

          }
      }
  }
  else {
      println("No Selection ")
    }
  }
  catch{
    case e:NumberFormatException =>println("Please enter a valid input format")
    case _ => println("Please enter a valid file")
  }
}
