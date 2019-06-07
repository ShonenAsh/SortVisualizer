package sortVisualizer.view

import javafx.animation.SequentialTransition
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.stage.Stage
import javafx.util.StringConverter
import sortVisualizer.bar.Bar
import sortVisualizer.utils.BubbleSort
import sortVisualizer.utils.MergeSort
import sortVisualizer.utils.RandomBar
import sortVisualizer.utils.SortAbstract
import sortVisualizer.view.WindowDim.NO_OF_BARS
import sortVisualizer.view.WindowDim.WINDOW_HEIGHT
import sortVisualizer.view.WindowDim.WINDOW_WIDTH

class MainView : BorderPane() {

    companion object {
        private lateinit var abs: SortAbstract
        var compareWindowBoolean = false
    }

    private val barHBox = HBox()
    private val topBox = HBox()
    private val sortButton = Button("Sort")
    private val randomButton = Button("Randomize")
    private val compareButton = Button("Compare Sorts")
    private val sortOptions: ChoiceBox<SortAbstract>

    private lateinit var secondView: SecondView

    private var bars: Array<Bar>

    init {
        this.center = barHBox
        this.top = topBox

        this.bars = RandomBar.getRandomBar(NO_OF_BARS)
        this.sortOptions = ChoiceBox()
        topBox.children.add(sortButton)
        topBox.children.add(randomButton)
        topBox.children.add(sortOptions)
        topBox.children.add(compareButton)
        topBox.alignment = Pos.CENTER

        for (b in topBox.children) {
            HBox.setMargin(b, Insets(5.0, 5.0, 20.0, 5.0))
        }

        val absList = ArrayList<SortAbstract>()
        absList.add(MergeSort())
        absList.add(BubbleSort())

        barHBox.children.addAll(bars)
        barHBox.alignment = Pos.BOTTOM_LEFT

        // Doesn't work

        compareButton.setOnMouseClicked {
            compareWindowBoolean = true
            openNewWindow()
        }

        sortButton.setOnAction {
            sortButton.isDisable = true

            val sq = SequentialTransition()

            abs = sortOptions.selectionModel.selectedItem
            sq.children.addAll(abs.startSort(bars))

            sq.setOnFinished {
                randomButton.isDisable = false
                sortButton.isDisable = false
            }

            if (compareWindowBoolean) {
                secondView.startComparision()
            }

            sq.play()
        }

        randomButton.setOnAction {
            sortButton.isDisable = false
            barHBox.children.clear()

            bars = RandomBar.getRandomBar(NO_OF_BARS)

            if (compareWindowBoolean) {
                secondView.setBars(bars)
            }

            barHBox.children.addAll(bars)
        }

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

    private fun openNewWindow() {
        val secondStage = Stage()
        secondView = SecondView(bars)
        secondView.style = "-fx-background-color: #1c1c1c"

        secondStage.title = "2nd Sort Algorithm"
        secondStage.scene = Scene(secondView, WINDOW_WIDTH.toDouble(), WINDOW_HEIGHT.toDouble())
        secondStage.show()
    }
}



