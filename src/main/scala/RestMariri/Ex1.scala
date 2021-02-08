package RestMariri

import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.library.collection
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.compound.{*, OneOf, RichCPD}
import com.cra.figaro.language.Element._
import com.cra.figaro.language.ElementCollection
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.library.collection
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.compound.{*, OneOf, RichCPD}
import com.cra.figaro.language.Element._
import com.cra.figaro.language.ElementCollection
import com.cra.figaro.library.atomic.continuous.Normal
import com.cra.figaro.algorithm.sampling.Importance

object Ex1{
  def main(args: Array[String])
  {
    //A:
    val snowyDaysInAWeek = Binomial (7, 0.6) //7 zile intr-o saptamana, in fiecare zi probabilitatea de 0.6 sa ninga
    val week = Apply(snowyDaysInAWeek, (i: Int)) => {
      if (i>5) "prea multa ninsoare"  //daca sunt mai mult de 5 zile cu ninsoare
      else if(i<3) "prea putina ninsoare" //maxim 2 cu ninsoare
      else "normala" //altfel.. 
  }
    //B:
    snowyDaysInAWeek.setCondition((i: Int) => i<=5 && i>2) //conditia ca sapt sa fie normala
    println(VariableElimination.probability(week, "normala")) //probabilitatea ca sapt sa fie normala



  }
}