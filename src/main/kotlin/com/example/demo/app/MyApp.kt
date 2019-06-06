package com.example.demo.app

import com.example.demo.view.MainView
import com.example.demo.view.WindowDim.WINDOW_HEIGHT
import com.example.demo.view.WindowDim.WINDOW_WIDTH
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class MyApp : Application() {
    override fun start(mainStage: Stage) {
        val mainView = MainView()
        mainView.style = "-fx-background-color: #1c1c1c"

        mainStage.title = "Visual Sort"
        mainStage.scene = Scene(mainView,
                WINDOW_WIDTH.toDouble(),
                WINDOW_HEIGHT.toDouble())
        mainStage.show()

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MyApp::class.java, *args)
        }
    }
}