package sortVisualizer.utils

import javafx.animation.ParallelTransition
import javafx.animation.Transition
import sortVisualizer.bar.Bar

class InsertionSort : SortAbstract() {
    private val trans = ArrayList<Transition>()


    override fun startSort(bars: Array<Bar>): ArrayList<Transition> {
        trans.clear()

        for (i in 1 until bars.size) {
            var j = i - 1
            val key = bars[i]

            val pt = ParallelTransition()

            trans.add(colorBar(bars, SELECTED_COLOR, i))

            while (j >= 0 && bars[j].getValue() > key.getValue()) {
                pt.children.add(bars[j].moveByX(barWidth))
                bars[j + 1] = bars[j]
                j--
            }

            bars[j + 1] = key

            pt.children.add(key.moveByX(barWidth * (j + 1 - i)))
            trans.add(pt)
            trans.add(colorBar(bars, INIT_COLOR, j + 1))

        }

        trans.add(colorBar(bars, SORTED_COLOR))

        return trans
    }
}