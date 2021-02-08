package RestMariri

import scala.collection._
import com.cra.figaro.language._
import com.cra.figaro.library.atomic.continuous.Beta
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.experimental.normalproposals.Normal
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored._

object RestMariri {
  def main(args: Array[String])
  {
   

    // clasa abstracta pt modelul Markov
    abstract class State {
      var starePacient = Array[Element[Boolean]] = Array.fill(chapters)(Constant('buna))
    }

    // clasa starii initiale
    class initialState() extends State {
      // setam probabilitatile conform tabelului:
      stare(0) = Select(0.721 -> 'buna, 0.581 -> 'nuPreaBuna, 0.75 -> 'bolnav, 1.0 -> 'decedat)
    }

    //clasa pentru starea urmatoare starii curente
    class NextState(current: State, iteration: Int) extends State {
      // de aici luam valorile fiecarei stari:
      stare(iteration) = CPD(stare(chapter - 1),
        'buna -> Select(0.721 -> 'buna, 0.202 -> 'nuPreaBuna, 0.067 -> 'bolnav, 0.1 -> 'decedat),
        'nuPreaBuna -> Select(0 -> 'buna, 0.581 -> 'nuPreaBuna, 0.407 -> 'bolnav, 0.012 ->'decedat),
        'bolnav -> Select(0 -> 'buna, 0 -> 'nuPreaBuna, 0.75 -> 'bolnav, 0.25 -> 'decedat),
        'decedat -> Select(0 -> 'buna, 0 -> 'nuPreaBuna, 0 -> 'bolnav, 1.0 -> 'decedat))
    }

  }
}
