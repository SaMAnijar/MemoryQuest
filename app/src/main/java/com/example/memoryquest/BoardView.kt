import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.GridLayout
import com.example.memoryquest.R
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.sqrt

class BoardView(private val context: Context, private val gameManager: Game) {

    private lateinit var buttons: List<Button>
    private var selectedCards = mutableListOf<Int>()

    var onScoreUpdated: ((Int) -> Unit)? = null  // Callback para atualizar a pontuação na MainActivity

    fun createBoardView(gridLayout: GridLayout) {
        gridLayout.removeAllViews()

        val cards = gameManager.getCards()
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        val totalCards = cards.size
        val columnCount = ceil(sqrt(totalCards.toDouble())).toInt()
        val rowCount = ceil(totalCards.toDouble() / columnCount).toInt()

        gridLayout.columnCount = columnCount
        gridLayout.rowCount = rowCount

        val buttonSize = (min(screenWidth / columnCount, screenHeight / rowCount) * 0.8).toInt()
        val margin = 32

        buttons = cards.mapIndexed { index, _ ->
            Button(context).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = buttonSize - 2 * margin
                    height = buttonSize - 2 * margin
                    setMargins(margin, margin, margin, margin)
                }
                setBackgroundResource(R.drawable.ic_card_back)

                setOnClickListener {
                    onCardClicked(index)
                }
                gridLayout.addView(this)
            }
        }
    }

    private fun onCardClicked(index: Int) {
        val card = gameManager.getCards()[index]

        if (card.isFaceUp || card.isMatched || selectedCards.size >= 2) {
            return
        }

        gameManager.flipCard(card)
        updateButtonState(index, false)

        selectedCards.add(index)

        if (selectedCards.size == 2) {
            val firstIndex = selectedCards[0]
            val secondIndex = selectedCards[1]

            val isMatch = gameManager.validateCards(firstIndex, secondIndex)

            if (isMatch) {
                // Atualizar a pontuação e notificar a Activity
                onScoreUpdated?.invoke(gameManager.score)

                selectedCards.clear()
            } else {
                gameManager.resetStreak()
                Handler(Looper.getMainLooper()).postDelayed({
                    gameManager.getCards()[firstIndex].isFaceUp = false
                    gameManager.getCards()[secondIndex].isFaceUp = false
                    updateButtonState(firstIndex, false)
                    updateButtonState(secondIndex, false)
                    selectedCards.clear()
                }, 500)
            }
        }
    }

    private fun updateButtonState(index: Int, matched: Boolean) {
        val card = gameManager.getCards()[index]
        val button = buttons[index]

        if (card.isFaceUp) {
            button.setBackgroundResource(card.imageResource)
        } else {
            button.setBackgroundResource(R.drawable.ic_card_back)
        }
    }
}
