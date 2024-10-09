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

    fun selectCard(card: Card): Boolean {
        val selectedCards = cards.filter { it.isFaceUp && !it.isMatched }

        // Se já houver duas cartas viradas, não faça nada
        if (selectedCards.size == 2) {
            return false
        }

        // Virar a carta
        card.isFaceUp = true

        // Verificar se formam um par
        if (selectedCards.size == 1) {
            val firstCard = selectedCards[0]
            if (firstCard.imageResource == card.imageResource) {
                // Par correto
                firstCard.isMatched = true
                card.isMatched = true

                // Aumentar a pontuação e verificar a sequência
                streak++
                score += 10 + (5 * streak)  // Ganha 10 pontos + bônus de 5 pontos por sequência
                return true
            } else {
                // Par incorreto, resetar a sequência
                streak = 0
            }
        }

        return false
    }
}