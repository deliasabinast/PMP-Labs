package Lab8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored._
object Alarma{
	Universe.createNew()
 	private val alarmNotRings = Flip(0.1) //prob. ca alarma sa nu sune
	private val busLate = Flip(0.2) // prob. sa intarzie autobuzul
	private val sleepsAgain = CPD(alarm,true->Flip(0.01)) //prob. sa adoarma la loc, chiar daca a sunat alarma
	private val doesntWakeup = CPD(alarmNotRings,sleepsAgain, (true,true)-> Flip(0.2)) //prob. sa nu se trezeasca, fie pt. ca nu a pus alarma, fie pt ca a adormit la loc
	// prob. sa intarzie:
	private val isLate=CPD(doesntWakeup,busLate,(false, false)-> Flip(0.1), (false, true)->Flip(0.8), (true, false)-> Flip(0.7), (false, false)-> Flip(0.9)) 
  	def main(args: Array[String]){
 		isLate.observe(true)
		 val alg = VariableElimination(doesntWakeup, busLate)
		 alg.start()
		 //subpunctul 2a:
		 println("Probability of being on time: " + alg.probability(doesntWakeup,true))
		 //subpunctul 2b:
		 println("Probability of having the alarm set: "+alg.probability(alarmNotRings, false))
		 //subpunctul 2c:
		 print(sleepsAgain)
		//subpunctul 3:
		println(VariableElimination.probability(doesntWakeup, true))
 		alg.kill
	
  }
}
