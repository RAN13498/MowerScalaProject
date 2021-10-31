package mower

class MowerPositionManagement {
  /**
   * Calculate the new position of Mower in the lawn and returns a new Mower object with updated position.
   * @param inputMower
   * @param lawn
   * @param Command
   * @return Mower: updated Mower position and direction
   */
  def moveMower(inputMower: Mower, lawn: Lawn, command: Command.Value): Mower = {
    command match {
      // Turn Left command case
      case Command.G => inputMower.copy(direction = leftRight(inputMower.direction)._1)
      // Turn Right command case
      case Command.D => inputMower.copy(direction = leftRight(inputMower.direction)._2)
      // Go forward command case
      case Command.A => {
        val newCoordinates = calculateCoordinates(inputMower.coordinates, inputMower.direction)
        //Check if new coordinates are in the lawn otherwise conserving the original position
        if((newCoordinates.x >= 0) && (newCoordinates.y >= 0)
          && (newCoordinates.x <= lawn.upRight.x)
          && (newCoordinates.y <= lawn.upRight.y))
            inputMower.copy(coordinates = newCoordinates)
        else
          inputMower
      }
      case _ => inputMower
    }
  }

  /**
   * Helper function that returns the new directions obtained by rotating the mower 90 deg left and right from current direction
   * exp.: if current direction is N (North) and the command is D (right) --> the updated direction will be E (East)
   * otherwise (command G) --> the updated direction will be W (West)
   */
  private def leftRight(currentDirection: Direction.Value): (Direction.Value, Direction.Value) = currentDirection match {
    case Direction.N => (Direction.W, Direction.E)
    case Direction.E => (Direction.N, Direction.S)
    case Direction.S => (Direction.E, Direction.W)
    case Direction.W => (Direction.S, Direction.N)
    case _ => (currentDirection, currentDirection)
  }

  /**
   * Helper function that calculates the new coordinates using the current coordiante and direction of the mower
   * exp.:  if current direction is N (North) and the command is A --> the updated coordinates will be (x,y+1)
   */
  private def calculateCoordinates(currentCoordinates: Coordinates, currentDirection: Direction.Value): Coordinates = currentDirection match {
    case Direction.N => currentCoordinates.copy(y = currentCoordinates.y + 1)
    case Direction.E => currentCoordinates.copy(x = currentCoordinates.x + 1)
    case Direction.S => currentCoordinates.copy(y = currentCoordinates.y - 1)
    case Direction.W => currentCoordinates.copy(x = currentCoordinates.x - 1)
    case _ => currentCoordinates
  }

}
