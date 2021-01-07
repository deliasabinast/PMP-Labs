/***package Lab11

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	def main(args: Array[String]) {
		private val left_population= Flip(0.1) // prob. ca un om obisnuit sa fie left handed
		private val left_president = CPD(left_population,true->Flip(0.5))
		 val alg = VariableElimination(left_president, president_Harvard)
		 alg.start()
		 //a:
		  println("Probability of being president: " + alg.probability(left_president,true))

		private val wentToHarvard = Flip(0.0005)
		private val president_Harvard = CPD(wentToHarvard,true->Flip(0.15))
		//b:
		 println("Probability of being president that went to Harvard: " + alg.probability(president_Harvard,true))

		 //c:
		  private val both = CPD(wentToHarvard,left_president,(true,true)->Flip(0.0025)) //to be continued
		  println("Probability of being president that went to Harvard and is left handed: " + alg.probability(both,true))

		  alg.close()

	}
}
***/