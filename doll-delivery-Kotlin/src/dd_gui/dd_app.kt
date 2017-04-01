package dd_gui

/**
 * Created by Hoang on 3/16/2017.
 */

import dd_map.map_painter
import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.scene.text.Font
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.layout.*
import javafx.scene.media.AudioClip
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import java.io.File
import javafx.collections.FXCollections
import javafx.scene.control.ChoiceBox
import dd_map.map_processor
import the_nice_old_lady


/**
 * Test app for nice-old-lady app.delivery services
 */
class dd_app : Application() {
    private val tada = BorderPane()
    private var deliver_button = Button("Deliver")
    private var reset_button = Button("Reset")
    private val from = TextField()
    private val to = TextField()
    private val distance = Text("Distance: ")
    private val path = Text("Path: ")
    private val canvas = Canvas()
    private val mp = map_painter(canvas)
    private var stage: Stage = Stage()
    // Drop shadow for the button
    private var ds = DropShadow()
    // Some nice tune
    private val ding = AudioClip(File("ding.wav").toURI().toString())
    private val hover = AudioClip(File("hover.wav").toURI().toString())
    private var map_choices = ChoiceBox(FXCollections.observableArrayList(
            "A Bunch of Dudes' Houses", "KGB Agents' Houses"))
    private val map_details = Text()
    private var list_loc = listOf<Map<String, Any>>()
    private var n = 1

    @Throws(Exception::class)
    override fun start(stage: Stage) {
        this.stage = stage
        // Set up pane for display stuff
        tada.setPrefSize(1200.0, 1000.0)

        setupQBox()
        setupPBox()
        setupCanvas()

        var map_Name = ""
        mp.paintMap(list_loc)
        // Show case the GUI
        stage.scene = Scene(tada, Color.ANTIQUEWHITE)
        stage.title = "Old Lady Delivery Services Inc.";
        stage.isResizable = false
        stage.show()
        // Play some nice tune
        val opening = AudioClip((File("opening.wav").toURI().toString()))
        opening.play()
        listentoDeliverB()
        listentoResetB()
        listentoMapChoices()
    }

    fun setupQBox() {
        val vBox = VBox()
        // Text for labelling startingLocation and targetLocation
        val loc = Text("At")
        loc.font = Font.font("Chiller", FontWeight.BOLD, 25.0)
        val des = Text("\"Client\"")
        des.font = Font.font("Chiller", FontWeight.BOLD, 25.0)
        // Set minimum width for input text
        from.minWidth = 200.0
        to.minWidth = 200.0
        // Style-up buttons
        // @diverey_Button
        deliver_button.font = Font.font("Chiller", FontWeight.EXTRA_BOLD, 25.0)
        deliver_button.textFill = Color.GHOSTWHITE
        deliver_button.style = "-fx-background-color: linear-gradient(#f91b1b, #b20808);"
        // @reset_Button
        reset_button.font = Font.font("Chiller", FontWeight.EXTRA_BOLD, 25.0)
        reset_button.textFill = Color.GHOSTWHITE
        reset_button.style = "-fx-background-color: linear-gradient(#828080, #050404);"
        val hBox = HBox(20.0)
        hBox.padding = Insets(10.0,10.0,10.0,10.0)
        hBox.alignment = Pos.CENTER
        // Add all elements to vBox
        hBox.children.addAll(loc, from, des, to, deliver_button, reset_button)
        tada.top = hBox
    }

    fun setupCanvas() {
        val hBox = HBox()
        // Seperator left and right
        var sep = Separator()
        sep.maxHeight = 850.0
        sep.orientation = Orientation.VERTICAL
        canvas.width = 800.0
        canvas.height = 800.0
        val vBox = VBox(20.0)
        val intro = Text("Map")
        intro.font = Font.font("Chiller", FontWeight.BOLD, 35.0)
        map_choices.style = "-fx-font-family: \"Chiller\"; -fx-font-size: 25px;" +
                "-fx-background-color: linear-gradient(#f8f8ff, #afafaf);"
        map_details.text = mp.getDescription(list_loc)
        map_details.font = Font.font("Chiller", FontWeight.BOLD, 25.0)
        vBox.alignment = Pos.CENTER
        vBox.children.addAll(intro, map_choices, map_details)
        hBox.alignment = Pos.CENTER
        hBox.children.addAll(canvas, sep, vBox)
        tada.center = hBox
    }

    fun setupPBox() {
        // Text for labelling startingLocation and targetLocation
        distance.font = Font.font("Chiller", FontWeight.BOLD, 25.0)
        path.font = Font.font("Chiller", FontWeight.BOLD, 25.0)
        // Set minimum width for input text
        val hBox = HBox(20.0)
        hBox.padding = Insets(10.0,10.0,10.0,10.0)
        hBox.alignment = Pos.CENTER
        // Add all elements to vBox
        hBox.children.addAll(distance, path)
        tada.bottom = hBox
    }

    fun listentoDeliverB() {
        deliver_button.setOnMouseEntered {
            hover.play()
            deliver_button.style = "-fx-background-color: linear-gradient(#b20808, #f91b1b);"
            deliver_button.effect = ds
        }
        deliver_button.setOnMouseExited {
            deliver_button.style = "-fx-background-color: linear-gradient(#f91b1b, #b20808);"
            deliver_button.effect = null
        }
        deliver_button.setOnMouseClicked {
            from.isDisable = true
            to.isDisable = true
            deliver_button.isDisable = true
            reset_button.isDisable = true
            val map: Map<String, Any> = mp.paintPath(from.text, to.text, list_loc, reset_button)
            if (map.size > 0) {
                distance.text = "Distance: " + map["distance "].toString()
                path.text = "Path: " + map["path "] as String
            }
            else {
                reset_button.isDisable = false
                var mess = "Invalid "
                if (the_nice_old_lady("GoogleMap").invalidStart(from.text, list_loc)) {
                    mess += "start"
                    if (the_nice_old_lady("GoogleMap").invalidTarget(to.text, list_loc)) mess += " and target"
                } else mess += "target"
                mess += ". They are probably protected by the CIA"
                alert().display(n++, mess)
                from.isDisable = false
                to.isDisable = false
                deliver_button.isDisable = false
                distance.text = "Distance: n/a"
                path.text = "Path: NOT FOUND!"
            }
        }
    }

    fun listentoResetB() {
        reset_button.setOnMouseEntered {
            hover.play()
            reset_button.style = "-fx-background-color: linear-gradient(#050404, #828080);"
            reset_button.effect = ds
        }
        reset_button.setOnMouseExited {
            reset_button.style = "-fx-background-color: linear-gradient(#828080, #050404);"
            reset_button.effect = null
        }
        reset_button.setOnMouseClicked {
            ding.play()
            from.isDisable = false
            to.isDisable = false
            deliver_button.isDisable = false
            from.text = ""
            to.text = ""
            distance.text = "Distance: "
            path.text = "Path: "
            mp.paintMap(list_loc)
            map_details.text = mp.getDescription(list_loc)
        }
    }

    fun listentoMapChoices() {
        map_choices.setOnMouseClicked {
            hover.play()
        }
        map_choices.getSelectionModel().selectedItemProperty().addListener { observable, oldValue, newValue ->
            list_loc = map_processor().getLocList(newValue)
            mp.paintMap(list_loc)
            map_details.text = mp.getDescription(list_loc)
            hover.play()
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            launch(dd_app::class.java)
        }
    }
}