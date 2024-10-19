import android.util.Log
import com.example.memoryquest.Card

class Game(private val cardImages: List<Int>) {

    private val cards = mutableListOf<Card>()
    var score: Int = 0
    private var streak: Int = 0
    private var matchedPairs: Int = 0  // Contador de pares encontrados

    fun initializeGame() {
        // Inicializar as cartas duplicadas e embaralhá-las
        val cardList = cardImages.flatMap { imageResource ->
            listOf(Card(imageResource), Card(imageResource))
        }.shuffled()

        cards.clear()
        cards.addAll(cardList)
        matchedPairs = 0  // Reiniciar os pares encontrados ao iniciar o jogo
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
            onPairMatched()  // Chamar o método ao encontrar um par
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

    // Método que será chamado sempre que um par for encontrado
    fun onPairMatched() {
        matchedPairs++  // Incrementar o contador de pares encontrados
    }

    // Verifica se todos os pares foram encontrados
    fun isComplete(): Boolean {
        // Se o número de pares encontrados for igual ao número de pares de cartas
        return matchedPairs == cardImages.size
    }
}