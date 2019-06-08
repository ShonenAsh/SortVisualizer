package sortVisualizer.utils

import javafx.animation.Transition
import javafx.scene.paint.Color
import sortVisualizer.bar.Bar

class QuickSort: SortAbstract() {
    companion object {
        private val PIVOT_COLOR = Color.DARKCYAN
    }
    private val trans: ArrayList<Transition> = ArrayList()


    override fun startSort(bars: Array<Bar>): ArrayList<Transition> {
        trans.clear()
        quickSort(bars,0,bars.size-1)
        trans.add(colorBar(bars,SORTED_COLOR))

        return trans
    }

    private fun quickSort(bars: Array<Bar>, low: Int, hi: Int) {
        if(low < hi){
            val q = partition(bars,low,hi)
            quickSort(bars,low,q-1)
            quickSort(bars, q+1,hi)
        }
    }

    private fun partition(bars: Array<Bar>, low: Int, hi: Int): Int {
        var i = low

        trans.add(colorBar(bars, PIVOT_COLOR,hi))

        for(j in low until hi){
            trans.add(colorBar(bars,SELECTED_COLOR,j))

            if(bars[j].getValue() < bars[hi].getValue()){
                trans.add(swapBar(bars,i,j))
                trans.add(colorBar(bars,INIT_COLOR,i))
                i++
            } else {
                trans.add(colorBar(bars,INIT_COLOR,j))
            }
        }
        trans.add(swapBar(bars,i,hi))
        trans.add(colorBar(bars,INIT_COLOR,i))

        return i
    }


}