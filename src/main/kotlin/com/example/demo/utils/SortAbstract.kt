package com.example.demo.utils

import com.example.demo.bar.Bar
import com.example.demo.view.WindowDim.NO_OF_BARS
import com.example.demo.view.WindowDim.WINDOW_WIDTH
import javafx.animation.FillTransition
import javafx.animation.ParallelTransition
import javafx.animation.Transition
import javafx.scene.paint.Color
import javafx.util.Duration

abstract class SortAbstract {
    val INIT_COLOR = Color.WHITE!!
    val SELECTED_COLOR = Color.GRAY!!
    val SORTED_COLOR = Color.LIGHTBLUE!!

    companion object {
        // small unit of width w.r.t to the window width
        @JvmStatic
        val s = WINDOW_WIDTH / NO_OF_BARS
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

        parallelT.children.addAll(bars[i].moveByX(s * (j - i)), bars[j].moveByX((-1 * s * (j - i))))

        val temp = bars[i]
        bars[i] = bars[j]
        bars[j] = temp

        return parallelT
    }

    abstract fun startSort(bars: Array<Bar>): ArrayList<Transition>
}


