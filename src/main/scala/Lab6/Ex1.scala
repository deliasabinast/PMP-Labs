/***package Lab6

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	/*def main(args: Array[String]) {
		val test = Constant("Test")

		val algorithm = Importance(1000, test)
		algorithm.start()
		
		println(algorithm.probability(test, "Test"))
	}*/

	def tennis(probP1ServeWin: Double, probP1Winner: Double, probP1Error: Double, probP2ServeWin: Double, probP2Winner: Double, probP2Error: Double):Element[Boolean] = {
 		def rally(firstShot: Boolean, player1: Boolean): Element[Boolean] = {
 			val pWinner = if (firstShot && player1) probP1ServeWin //probabilitatea ca player1 sa castige atunci cand serveste primul
 						  else if (firstShot && !player1) probP2ServeWin //prob. ca player2 sa castige atunci cand serveste player1
 						  else if (player1) probP1Winner // nu mai conteaza cine a servit, se ia in calcul doar probabilitatea ca player1 sa castige
 						  else probP2Winner //altfel, castiga player2
 			val pError = if (player1) probP1Error else probP2Error //probabilitatea de eroare pentru fiecare player in parte
 			val winner = Flip(pWinner) //se alege un castigator "random"
 			val error = Flip(pError) //probabilitatea de eroare pentru castigatorul ales
 			If(winner, Constant(player1),If(error, Constant(!player1), rally(false, !player1)))
 }

 	def game(p1Serves: Boolean, p1Points: Element[Int],p2Points: Element[Int]): Element[Boolean] = {
 		val p1WinsPoint = rally(true, p1Serves) //player1 castiga la servire
 		val newP1Points = Apply(p1WinsPoint, p1Points, (wins: Boolean, points: Int) =>
 				if (wins) points + 1 else points) //se adauga puncte playerului 1 
		val newP2Points = Apply(p1WinsPoint, p2Points, (wins: Boolean, points: Int) =>
 				if (wins) points else points + 1) //se adauga puncte playerului 2
 		val p1WinsGame = Apply(newP1Points, newP2Points, (p1: Int, p2: Int) => p1 >= 4 && p1 - p2 >= 2) //se aduna toate punctele pt player1
 		val p2WinsGame = Apply(newP2Points, newP1Points, (p2: Int, p1: Int) => p2 >= 4 && p2 - p1 >= 2) //se aduna toate punctele pt player2
 		val gameOver = p1WinsGame || p2WinsGame //vedem cine castiga
 		If(gameOver, p1WinsGame, game(p1Serves, newP1Points, newP2Points))
 }

 	def play(p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int],p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] = {
 		val p1WinsGame = game(p1Serves, Constant(0), Constant(0))
 		val newP1Games =Apply(p1WinsGame, p1Games, p2Games,(wins: Boolean, p1: Int, p2: Int) =>
 			if (wins) {
 				if (p1 >= 5) 0 else p1 + 1
			 } // daca player1 castiga 5 jocuri, inseamna ca a castigat un set si se reseteaza nr de jocuri castigate
			 			 else {
 				if (p2 >= 5) 0 else p1
 			})

 		val newP2Games =Apply(p1WinsGame, p1Games, p2Games,(wins: Boolean, p1: Int, p2: Int) =>
 			if (wins) {
 				if (p1 >= 5) 0 else p2
			 } //daca player2 castiga 5 jocuri, inseamna ca a castigat un set si se reseteaza nr de jocuri castigate
			 else {
 				if (p2 >= 5) 0 else p2 + 1
 			})
 		val newP1Sets =	Apply(p1WinsGame, p1Games, p1Sets,(wins: Boolean, games: Int, sets: Int) =>
 				if (wins && games == 5) sets + 1 else sets) //crestem numarul de seturi castigate  pt player1
 		val newP2Sets = Apply(p1WinsGame, p2Games, p2Sets,(wins: Boolean, games: Int, sets: Int) =>
 				if (!wins && games == 5) sets + 1 else sets)  //crestem numarul de seturi castigate  pt player2
 		val matchOver = Apply(newP1Sets, newP2Sets, (p1: Int, p2: Int) => p1 >= 2 || p2 >= 2)
		 If(matchOver,Apply(newP1Sets, (sets: Int) => sets >= 2), play(!p1Serves, newP1Sets, newP2Sets, newP1Games, newP2Games))
		 // game over cand unul dintre playeri ajunge sa castige 2 seturi
 }
 play(true, Constant(0), Constant(0), Constant(0), Constant(0)) //start game
 }

}
***/
 