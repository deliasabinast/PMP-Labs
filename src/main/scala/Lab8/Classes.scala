package Lab8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored._
import javax.lang.model.element.Element

abstract class Departments{
    val budget: Element[Int]
    val health: Element[Int]
    def setBudget : Element[Int] = new (budget)  //to be continued
    def setHealth: Element[Int] = new (health)  //to be continued

}

case class HumanResources() extends Departments{

}

case class ResearchnDev(hr: HumanResources) extends Departments{
    val state = Apply(hr.state, (hr: Double) => if (hr< 0.7) 0.4 else 0.5)

}

case class Finance(sales: Sales) extends Departments{
     val state = Apply(sales.state, (sales: Double) => if (sales < 0.6) 0.3 else 0.5)


}
case class Production(resdev: ResearchnDev) extends Departments{
    val state = Apply(resdev.state, (resdev: Double) => if (resdev < 0.7) 0.2 else 0.8)

}

case class Sales(production: Production) extends Departments{
    val state = Apply(production.state, (production: Double) => if (production < 0.7) 0.2 else 0.8)

}