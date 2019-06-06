package sortVisualizer.utils

import sortVisualizer.bar.Bar
import sortVisualizer.view.WindowDim.BUTTON_HBOX_OFFSET
import sortVisualizer.view.WindowDim.WINDOW_HEIGHT
import sortVisualizer.view.WindowDim.WINDOW_WIDTH
import javafx.scene.paint.Color
import java.util.*

class RandomBar {
    companion object {
        fun getRandomBar(n: Int): Array<Bar> {

            // Create an array of random bars
            val barArr = Array(n) { Bar(1 + Random().nextInt(n)) }

            for ((i, value) in barArr.withIndex()) {
                value.x = (i * (WINDOW_WIDTH / n)).toDouble()
                value.fill = Color.WHITE
                setBarDim(value, barArr.size)
            }

            return barArr
        }

        fun getRandomBar(barArr: Array<Bar>): Array<Bar> {
            val bars = Array(barArr.size) { i -> Bar(barArr[i].getValue()) }

            for((i,value) in bars.withIndex()){
                value.x = (i * (WINDOW_WIDTH / barArr.size)).toDouble()
                value.fill = Color.WHITE
                setBarDim(value, barArr.size)
            }
            return bars
        }

        private fun setBarDim(bar: Bar, n: Int) {
            bar.height = ((((WINDOW_HEIGHT - BUTTON_HBOX_OFFSET) / n) * bar.getValue()).toDouble())
            bar.width = (WINDOW_WIDTH / n).toDouble()
        }
    }
}