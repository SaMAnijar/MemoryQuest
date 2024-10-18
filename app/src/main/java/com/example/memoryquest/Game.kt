import android.util.Log
import com.example.memoryquest.Card

class Game(private val cardImages: List<Int>) {

    private val cards = mutableListOf<Card>()
    var score: Int = 0
    private var streak: Int = 0

    fun initializeGame() {
        // Inicializar as cartas duplicadas e embaralhá-las
        val cardList = cardImages.flatMap { imageResource ->
            listOf(Card(imageResource), Card(imageResource))
        }.shuffled()

        cards.clear()
        cards.addAll(cardList)
    }

    fun getCards(): List<Card> = cards

    fun flipCard(card: Card) {
        card.isFaceUp = !card.isFaceUp
    }

    fun validateCards(firstIndex: Int, secondIndex: Int): Boolean {
        val firstCard = cards[firstIndex]
        val secondCard = cards[secondIndex]

        if (firstCard.imageResource == secondCard.imageResource) {
            firstCard.isMatched = true
            secondCard.isMatched = true

            // Aumentar a pontuação e verificar a sequência
            score += 10 + (5 * streak)
            streak++
            Log.d("Game", "Par encontrado! Pontuação: $score, Sequencia: $streak")
            return true
        } else {
            streak = 0
            Log.d("Game", "Par incorreto! Pontuação: $score")
            return false
        }
    }

    fun resetStreak() {
        streak = 0
    }
}