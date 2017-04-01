package dd_gui

/**
 * Created by Hoang on 3/20/2017.
 */

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.scene.control.Button
import javafx.scene.effect.DropShadow
import javafx.scene.layout.VBox
import javafx.scene.media.AudioClip
import javafx.scene.Scene
import java.io.File

class alert() {
    val ok = Button("Ok")
    val stage = Stage()

    fun display(n: Int, message: String) {
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.title = "Wrong Turn " + n + ". Available now in theater!!!"
        stage.minWidth = 550.0
        stage.minHeight = 200.0
        // Set up the text message
        val mess = Text(message)
        mess.font = Font.font("Chiller", FontWeight.BOLD, 25.0)
        // Set up and style-up our button
        ok.minWidth = 100.0
        ok.font = Font.font("Chiller", FontWeight.EXTRA_BOLD, 25.0)
        ok.textFill = Color.GHOSTWHITE
        ok.style = "-fx-background-color: linear-gradient(#828080, #050404);"

        val vbox = VBox(20.0)
        vbox.alignment = Pos.CENTER
        vbox.children.addAll(mess, ok)

        val scene = Scene(vbox)
        stage.scene = scene
        listentoOk()
        stage.showAndWait()
    }

    fun listentoOk() {
        // Some nice tune
        val hover = AudioClip(File("hover.wav").toURI().toString())
        // Drop shadow for the button
        var ds = DropShadow()
        ok.setOnMouseEntered {
            hover.play()
            ok.style = "-fx-background-color: linear-gradient(#050404, #828080);"
            ok.effect = ds
        }
        ok.setOnMouseExited {
            ok.style = "-fx-background-color: linear-gradient(#828080, #050404);"
            ok.effect = null
        }
        ok.setOnAction {
            stage.close()
        }
    }
}