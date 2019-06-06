package com.example.demo.utils

import com.example.demo.bar.Bar
import javafx.animation.ParallelTransition
import javafx.animation.Transition
import java.util.*

class MergeSort : SortAbstract() {

    private lateinit var temp: Array<Bar?>

    override fun startSort(bars: Array<Bar>): ArrayList<Transition> {
        val transitions = ArrayList<Transition>()

        this.temp = arrayOfNulls(bars.size)

        transitions.addAll(mergeSort(bars, 0, bars.size - 1))

        transitions.add(colorBar(bars, SORTED_COLOR))

        return transitions
    }

    private fun merge(bars: Array<Bar>, p: Int, q: Int, r: Int): java.util.ArrayList<Transition> {
        val trans = java.util.ArrayList<Transition>()

        val tempList = emptyArray<Bar>()

        for (i in p..r) {
            temp[i] = bars[i]
            tempList.plus(temp[i]!!)
        }

        var i = p
        var j = q + 1
        var k = p

        while (i <= q && j <= r) {
            if (temp[i]!!.getValue() <= temp[j]!!.getValue()) {
                bars[k++] = temp[i++]!!
            } else {
                bars[k++] = temp[j++]!!
            }
        }

        while (i <= q) {
            bars[k++] = temp[i++]!!
        }

        while (j <= r) {
            bars[k++] = temp[j++]!!
        }

        trans.add(colorBar(tempList, SELECTED_COLOR))

        val pt = ParallelTransition()

        for (x in p..r) {
            for (y in p..r) {
                if (temp[x] == bars[y]) {
                    pt.children.add(temp[x]!!.moveByX(s * (y - x)))
                }
            }
        }

        trans.add(pt)
        trans.add(colorBar(tempList, SELECTED_COLOR))

        return trans
    }

    private fun mergeSort(arr: Array<Bar>, p: Int, r: Int): ArrayList<Transition> {
        val transitions = java.util.ArrayList<Transition>()

        if (p < r) {
            val q = (p + r) / 2
            transitions.addAll(mergeSort(arr, p, q))
            transitions.addAll(mergeSort(arr, q + 1, r))
            transitions.addAll(merge(arr, p, q, r))
            println("Current Thread = ${Thread.currentThread().id})")
        }

        return transitions
    }
}