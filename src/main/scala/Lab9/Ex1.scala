package Lab9

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.atomic.{discrete,continuous}
import com.cra.figaro.library.atomic.continuous.Normal
import com.cra.figaro.library.atomic.discrete.{FromRange, Poisson}
import com.cra.figaro.library.compound.{If, ^^, RichCPD, OneOf, *}
import com.cra.figaro.algorithm.filtering.ParticleFiller
import com.cra.figaro.library.collection_
import com.cra.figaro.algorithm.factored.VariableElimination
import javax.lang.model.element.Element

object Marrrrkov{
	val chapters=10
	val initial=Universe.createNew()
	Constant(3)("score",initial)
	Constant(8)("learned", initial)
	Constant(5)("difficulty", initial)

	def transition(learned: Int, difficulty: Int): (Element[(Int, Int, Int)]) = {
		////
	}

	def nextUniverse(previous: Universe): Universe = {
		/////
	}

	def main(args: Array[String]) {
		val arrivingObservation = List(Some(1), Some(1), Some(1), None, None, None, None, None, None, None, None, None)
		val alg = ParticleFiller(initial, nextUniverse, 100)
		alg.start()
		for {time <- 1 to 10} {
			val evidence = {
				arrivingObservation(time) match {
					case None -> List()
					case Some(n) -> List(NamedEvidence("score", Observation(n)))
				}
			}
			alg.advanceTime(evidence)
			print("Chapter "+ time + ":")
			println("expected learning = "+ alg.currentExpectation("score", (i: Int)=>i))
		}
	}
}
