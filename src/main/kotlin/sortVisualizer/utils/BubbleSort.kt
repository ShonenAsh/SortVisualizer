package sortVisualizer.utils

import javafx.animation.Transition
import sortVisualizer.bar.Bar

class BubbleSort : SortAbstract() {

    private var swapped: Boolean = false
    private val trans = ArrayList<Transition>()

    override fun startSort(bars: Array<Bar>): ArrayList<Transition> {
        trans.clear()
        bubbleSort(bars)
        this.trans.add(colorBar(bars, SORTED_COLOR))
        return this.trans
    }

    private fun barCompare(bars: Array<Bar>, a: Int, b: Int): ArrayList<Transition> {
        val transitions = ArrayList<Transition>()

        transitions.add(colorBar(bars, SELECTED_COLOR, a, b))

        if (bars[a].getValue() > bars[b].getValue()) {
            transitions.add(swapBar(bars, a, b))
            swapped = true
        }

        transitions.add(colorBar(bars, INIT_COLOR, a, b))
        return transitions

    }

    private fun bubbleSort(bars: Array<Bar>) {
        for (i in 0..(bars.size - 1)) {
            swapped = false
            for (j in 0 until (bars.size - 1 - i)) {
                this.trans.addAll(barCompare(bars, j, j + 1))
            }

            if (!swapped)
                break
        }
    }

}