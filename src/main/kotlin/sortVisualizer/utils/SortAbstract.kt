package sortVisualizer.utils

import sortVisualizer.bar.Bar
import sortVisualizer.view.WindowDim.NO_OF_BARS
import sortVisualizer.view.WindowDim.WINDOW_WIDTH
import javafx.animation.FillTransition
import javafx.animation.ParallelTransition
import javafx.animation.Transition
import javafx.scene.paint.Color
import javafx.util.Duration

abstract class SortAbstract {
    val INIT_COLOR = Color.WHITE!!
    val SELECTED_COLOR = Color.CRIMSON!!
    val SORTED_COLOR = Color.LIGHTBLUE!!

    companion object {
        @JvmStatic
        val barWidth = WINDOW_WIDTH / NO_OF_BARS
    }

    fun colorBar(bars: Array<Bar>, col: Color, vararg a: Int): ParallelTransition {
        val parallelT = ParallelTransition()
        for (i in a) {
            val fillT = FillTransition()
            fillT.shape = bars[i]
            fillT.toValue = col
            fillT.duration = Duration.millis(10.0)
            parallelT.children.add(fillT)
        }
        return parallelT
    }

    fun colorBar(bars: Array<Bar>, col: Color): ParallelTransition {
        val parallelT = ParallelTransition()
        for (i in bars) {
            val fillT = FillTransition()
            fillT.shape = i
            fillT.toValue = col
            fillT.duration = Duration.millis(10.0)
            parallelT.children.add(fillT)
        }
        return parallelT
    }

    fun swapBar(bars: Array<Bar>, i: Int, j: Int): ParallelTransition {
        val parallelT = ParallelTransition()

        parallelT.children.addAll(bars[i].moveByX(barWidth * (j - i)), bars[j].moveByX((-1 * barWidth * (j - i))))

        val temp = bars[i]
        bars[i] = bars[j]
        bars[j] = temp

        return parallelT
    }

    abstract fun startSort(bars: Array<Bar>): ArrayList<Transition>
}


