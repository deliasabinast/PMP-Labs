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


object RestMariri {
  def main(args: Array[String]): 
  Unit = {
      //A:
    val avg_temperature = Normal(7, 5)    //distributie normala cu media 7 si variatia 5, reprezentata cu ajutorul metodei Normal
    val variance = Flip(0.5) //probabilitea de 0.5 pt fiecare dintre cele 2 valori ale variantei (adica 20 si 30)
    variance1 = If(variance, 20, 30) //in functie de valoarea lui variance, se alege o valoare pt variance1 (dintre 20 si 30)
    val avg_temp = Normal(7, variance1)   //se modeleaza temperatura in functie de distributia normala cu varianta egala cu variance1, pt media 7

    //def greaterThan50(d: Double) = d > 50
    //println(Importance.probability(avg_temperature, greaterThan50 _))

    //B:
    def greaterThan50lowerThan20(d: Double) = d < 50 && d > 20   //functie care va testa cand valoarea este cuprinsa in intervalul cerut (intre 20 si 50)
    println(Importance.probability(avg_temperature, greaterThan50lowerThan20 _))  //pentru temperatura medie curenta, se verifica daca este in intervalul mentionat mai sus prin intermediul functiei greaterThan50lowerThan20 

  }

}