package com.example.demo.view

import com.example.demo.bar.Bar
import com.example.demo.utils.BubbleSort
import com.example.demo.utils.MergeSort
import com.example.demo.utils.RandomBar
import com.example.demo.utils.SortAbstract
import javafx.animation.SequentialTransition
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ChoiceBox
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.util.StringConverter

class SecondView(barArr: Array<Bar>) : BorderPane() {

    companion object {
        private lateinit var abs: SortAbstract
    }

    private val barHBox = HBox()
    private val topBox = HBox()
    private val sortOptions: ChoiceBox<SortAbstract>
    private var bars: Array<Bar>

    init {
        this.center = barHBox
        this.top = topBox
        this.bars = RandomBar.getRandomBar(barArr)
        this.sortOptions = ChoiceBox()
        topBox.children.add(sortOptions)
        topBox.alignment = Pos.CENTER

        for (b in topBox.children) {
            HBox.setMargin(b, Insets(5.0, 5.0, 20.0, 5.0))
        }

        val absList = ArrayList<SortAbstract>()
        absList.add(MergeSort())
        absList.add(BubbleSort())

        barHBox.children.addAll(bars)
        barHBox.alignment = Pos.BOTTOM_LEFT

        sortOptions.items = FXCollections.observableArrayList(absList)

        sortOptions.selectionModel.select(0)

        sortOptions.converter = object : StringConverter<SortAbstract>() {
            override fun toString(abstractSort: SortAbstract?): String {
                return if (abstractSort == null) {
                    ""
                } else {
                    abstractSort::class.simpleName!!
                }
            }

            override fun fromString(s: String): SortAbstract? {
                return null
            }
        }
    }

    fun startComparision() {
        val sq = SequentialTransition()

        abs = sortOptions.selectionModel.selectedItem
        sq.children.addAll(abs.startSort(bars))

        sq.play()
        println("2nd window Thread = ${Thread.currentThread().id})")
    }

    fun setBars(newBars: Array<Bar>) {
        barHBox.children.clear()
        bars = RandomBar.getRandomBar(newBars)
        barHBox.children.addAll(bars)
    }
}



