package sortVisualizer.bar

import javafx.animation.TranslateTransition
import javafx.scene.shape.Rectangle
import javafx.util.Duration

// Represents a bar(element to be sorted) on the main screen
class Bar(private val value: Int) : Rectangle() {
    fun getValue() = value

    fun moveByX(x: Int): TranslateTransition {
        val tTrans = TranslateTransition()
        tTrans.node = this
        tTrans.duration = Duration.millis(100.0)
        tTrans.byX = x.toDouble()

        return tTrans
    }
}