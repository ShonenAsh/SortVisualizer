package sortVisualizer.app

import sortVisualizer.view.MainView
import sortVisualizer.view.WindowDim.WINDOW_HEIGHT
import sortVisualizer.view.WindowDim.WINDOW_WIDTH
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class MyApp : Application() {
    override fun start(mainStage: Stage) {
        val mainView = MainView(mainStage)
        mainView.style = "-fx-background-color: #1c1c1c"

        mainStage.title = "Visual Sort"
        mainStage.scene = Scene(mainView,
                WINDOW_WIDTH.toDouble(),
                WINDOW_HEIGHT.toDouble())
        mainStage.x = 200.0
        mainStage.y = 200.0
        mainStage.show()

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MyApp::class.java, *args)
        }
    }
}