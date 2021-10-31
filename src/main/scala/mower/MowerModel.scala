package mower

/**
 * Direction constants.
 * N for North.
 * E for East.
 * W for West.
 * S for South.
 */
object Direction extends Enumeration {
  val N, E, W, S = Value
}

/**
 * Command constants.
 * D to turn 90 deg to right.
 * G to turn 90 deg to left.
 * A to move forward one step.
 */
object Command extends Enumeration {
  val D, G, A = Value
}

/**
 * Coordinate data model.
 * @param x X coordinate
 * @param y Y coordinate
 */
case class Coordinates (x: Int, y: Int)


/**
 * Data model for Lawn.
 * @param upRight Top right corner Coordinates of the lawn
 */
case class Lawn (upRight: Coordinates)

/**
 * Data model for Mower.
 * @param coordinates Current coordinates
 * @param direction Current direction
 */
case class Mower (coordinates: Coordinates, direction: Direction.Value) {
  def print = this.coordinates.x + " " + this.coordinates.y + " " + this.direction.toString
}

