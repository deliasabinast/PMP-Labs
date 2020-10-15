package Lab3

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.compound._

object CovidProb{
    Universe.createNew()
    private val tuse=Flip(0.04)
    private val febra=Flip(0.06)
    private val covid=CPD(febra,tuse,(false,false)->Flip(0.001),(false,true)->Flip(0.002),(true, false)->Flip(0.4),(true,true)->Flip(0.99))
    def main(args: Array[String]){
        covid.observe(true)
        val alg = VariableElimination(febra,tuse)
        alg.start()
        println("Probability of suffering of COVID-19: " + (alg.probability(febra,true))*(alg.probability(tuse,true)))
        println("Probability of suffering of COVID-19 - fever only: " + alg.probability(febra,true))
        println("Probability of suffering of COVID-19 - cough only: " + alg.probability(tuse,true))
        println("Probability of suffering of COVID-19 - asimptomatic: " + 1/(alg.probability(febra,true))*(alg.probability(tuse,true)))
        println("Probability of being healthy: " + (alg.probability(febra,false))*(alg.probability(tuse,false)))
        alg.kill
    }
}
