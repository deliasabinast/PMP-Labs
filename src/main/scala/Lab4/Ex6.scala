package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.atomic.discrete._

object Ex6 {
	def main(args: Array[String]) {
        def doubles = {
            val die1 = FromRange(1, 7)
            val die2 = FromRange(1, 7)
            val die3 = FromRange(1, 7)
            val die4 = FromRange(1, 7)
            val die5 = FromRange(1, 7)
            val die6 = FromRange(1, 7)
            (die1 === die2) && (die3 === die4) && (die5 === die6) && (die1 !== die3) && (die3 !== die5) && (die5 !== die1)
        }
        val jail = doubles && doubles && doubles
        println(VariableElimination.probability(jail, true))
 	}
}