import mower.{Command, Coordinates, Direction, Lawn, Mower, MowerPositionManagement}
import org.scalatest.flatspec.AnyFlatSpec

class MowItNowTest extends AnyFlatSpec{

  it should "match" in {
    val mowerManagement = new MowerPositionManagement
    val lawn = Lawn(Coordinates(5, 5))

    val mower1 = Mower(Coordinates(1, 2), Direction.N)
    val commands1: Seq[Command.Value] = Seq(Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.G, Command.A, Command.A)
    val movedMower1 = commands1.foldLeft(mower1)((accumulator, command)  => mowerManagement.moveMower(accumulator, lawn, command))
    assert(1 == movedMower1.coordinates.x)
    assert(3 == movedMower1.coordinates.y)
    assert(Direction.N == movedMower1.direction)

    val mower2 = Mower(Coordinates(3, 3), Direction.E)
    val commands2: Seq[Command.Value] = Seq(Command.A, Command.A, Command.D, Command.A, Command.A, Command.D, Command.A, Command.D, Command.D, Command.A)
    val movedMower2 = commands2.foldLeft(mower2)((accumulator, command)  => mowerManagement.moveMower(accumulator, lawn, command))
    assert(5 == movedMower2.coordinates.x)
    assert(1 == movedMower2.coordinates.y)
    assert(Direction.E == movedMower2.direction)
  }
}
