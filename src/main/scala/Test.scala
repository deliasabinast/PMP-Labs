import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.language.{Select, Apply, Constant, Element, Chain, Universe, Flip}
import com.cra.figaro.library.compound.{If, CPD, RichCPD, OneOf, *, ^^}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.algorithm.factored.beliefpropagation.BeliefPropagation
import javax.lang.model.element.Element

object Test {
	def main(args: Array[String]) {
		
		

	abstract class State {
			 val hours = 5
			val ploios: Array[Element[Symbol]] = Array.fill(hours)(Constant('putin))
			val insorit: Array[Element[Int]] = Array.fill(hours)(Constant(1))
			val inorat: Array[Element[Symbol]] = Array.fill(hours)(Constant('picat))

			ploios(0) = Select(0.2 -> 'putin, 0.5 -> 'mediu, 0.3 -> 'tot)
		for { hour <- 1 until hours }
		{
			/*ploios(hour) = CPD(ploios(hour - 1),
				'tot -> Select(0.1 -> 'putin, 0.2 -> 'mediu, 0.7 -> 'tot),
				'mediu -> Select(0.2 -> 'putin, 0.5 -> 'mediu, 0.3 -> 'tot),
				'putin -> Select(0.7 -> 'putin, 0.2 -> 'mediu, 0.1 -> 'tot))*/
		}

		for { hour <- 0 until hours }
		{
			/*score(hour) = CPD(ploios(hour),
				'tot -> Select(0.1 -> 1, 0.1 -> 2, 0.3 -> 3, 0.7 -> 4, 0.5 -> 5),
				'mediu -> Select(0.1 -> 1, 0.4 -> 2, 0.8 -> 3, 0.5 -> 4, 0.1 -> 5),
				'putin -> Select(0.4 -> 1, 0.6 -> 2, 0.2 -> 3, 0.1 -> 4, 0.1 -> 5))*/
		}

		for { hour <- 0 until hours }
		{
			/*passes(hour) = RichCPD(score(hour),
				OneOf(1, 2) -> Constant('picat),
				OneOf(3, 4) -> Constant('trecut),
				OneOf(5) -> Constant('success))*/
		}

		println(VariableElimination.probability(passes(9), 'success))

		passes(0).observe('trecut)
		println(VariableElimination.probability(passes(9), 'success))

		passes(1).observe('trecut)
		println(VariableElimination.probability(passes(9), 'success))

		passes(2).observe('trecut)
		println(VariableElimination.probability(passes(9), 'success))

	}
}

	class is_insorit() extends State {
        val umbrella = Flip(0.15)
        val noumbrella = Flip(0.85)
    }
    class is_innorat() extends State {
        val umbrella = Flip(0.65)
        val noumbrella = Flip(0.55)
    }
    class is_ploios() extends State {
        val umbrella = Flip(0.75)
        val noumbrella = Flip(0.25)
    }
 	class InitialState() extends State {
 			val confident = Flip(0.4)
 	}
 	class NextState(current: State) extends State {
 			val confident =
 			If(current.confident, Flip(0.6), Flip(0.3))
 	}
 		// produce a state sequence in reverse order of the given length
	def stateSequence(n: Int): List[State] = {
 		if (n == 0) List(new InitialState())
 		else {
 				val last :: rest = stateSequence(n - 1)
 				new NextState(last) :: last :: rest
			 }
 	}
 	// unroll the hmm and measure the amount of time to infer the last hidden state
 	def timing(obsSeq: List[Boolean]): Double = {
 			Universe.createNew() // ensures that each experiment is separate
 			val stateSeq = stateSequence(obsSeq.length)
 			for { i <- 0 until obsSeq.length } {
 			stateSeq(i).possession.observe(obsSeq(obsSeq.length - 1 - i))
 	}
 	val alg = VariableElimination(stateSeq(0).confident)
 	val time0 = System.currentTimeMillis()
 	alg.start()
 	val time1 = System.currentTimeMillis()
 	(time1 - time0) / 1000.0 // inference time in seconds
 }
 val steps = 1000
 val obsSeq = List.fill(steps)(scala.util.Random.nextBoolean())
 println(steps + ": " + timing(obsSeq))
}
