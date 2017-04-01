package dd_map

/**
 * Created by Hoang on 3/17/2017.
 */

import javafx.concurrent.Task
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.media.AudioClip
import javafx.scene.paint.Color
import the_nice_old_lady
import javafx.scene.text.Font
import java.io.File
import java.util.*

class map_painter constructor(c: Canvas) {
    // Create an old nice lady
    private val grandmaC = the_nice_old_lady("Grandma C")
    // The canvas we paint on
    private val canvas = c
    private val g = canvas.graphicsContext2D
    private var map: MutableMap<String, DoubleArray> = mutableMapOf()

    /**
     * Display the solution path. Highlight in red
     * Change the text box for distance result and path details
     */
    fun paintPath(startingLocation: String, targetLocation: String, list_loc: List<Map<String,Any>>, rs: Button): Map<String, Any> {
        var solution = mapOf<String, Any>()
        if (list_loc.size > 0) {
            solution = grandmaC.dijkstra(startingLocation, targetLocation, list_loc)
            if (solution["path "] == null) return solution
            val laugh = AudioClip(File("laugh.mp3").toURI().toString())
            val path_locs = solution["path "].toString().split(" => ")
            var c = 255
            g.lineWidth = 3.0
            g.fill = Color.rgb(255, 10, 10)
            g.fillOval(map[startingLocation]!![0] - 8, map[startingLocation]!![1] - 8, 16.0, 16.0)
            val task = object : Task<Void>() {
                @Throws(Exception::class)
                override fun call(): Void? {
                    for (i in 0..path_locs.size - 2) {
                        Thread.sleep(1250)
                        val start_X = map[path_locs[i]]!![0]
                        val start_Y = map[path_locs[i]]!![1]
                        val end_X = map[path_locs[i + 1]]!![0]
                        val end_Y = map[path_locs[i + 1]]!![1]
                        g.stroke = Color.rgb(c, 10, 10)
                        g.strokeLine(start_X, start_Y, end_X, end_Y)
                        c -= 15
                    }
                    g.fill = Color.rgb(c, 10, 10)
                    g.fillOval(map[targetLocation]!![0] - 8, map[targetLocation]!![1] - 8, 16.0, 16.0)
                    laugh.play()
                    rs.isDisable = false
                    //cb.isDisable = false
                    return null
                }
            }
            Thread(task).start()

        }
        return solution
    }

    /**
     * Paint the original map with locations connected with lines
     */
    fun paintMap(list_loc: List<Map<String, Any>>) {
        if (list_loc.size > 0) {
            g.clearRect(0.0,0.0,800.0,800.0)
            // Temporarily store locations for further use
            var temp: Array<String?> = arrayOfNulls<String>(list_loc.count() + 1)
            var i = 0
            for (m in list_loc) {
                if (!temp.contains(m["startLocation"])) temp[i++] = m["startLocation"] as String
                if (!temp.contains(m["endLocation"])) temp[i++] = m["endLocation"] as String
            }
            val rand: Random = Random()
            for (i in 0..temp.size - 1) {
                val randomPos = rand.nextInt(temp.size)
                val tempLoc = temp[i]
                temp[i] = temp[randomPos]
                temp[randomPos] = tempLoc
            }
            // Count locations and pre-set the map of locations and their coordinates
            // Change @map
            g.fill = Color.BLACK
            var noOfLoc = 0.0
            for (loc in temp) {
                if (loc != null) {
                    noOfLoc++
                    map.put(loc, doubleArrayOf())
                }
            }
            // Fully set up map of locations and their coordinates
            // Somewhat randomize locations on the canvas
            val noOfLoc_eachRow = Math.ceil(Math.sqrt(noOfLoc))
            val noOfLoc_eachColumn = Math.ceil(noOfLoc/noOfLoc_eachRow)
            val x_dis = 760/noOfLoc_eachRow
            val y_dis = 760/noOfLoc_eachColumn
            var x = x_dis/2
            var y = y_dis/2
            for (loc in temp) {
                if (loc != null) {
                    var offSetX = rand.nextInt((x_dis/2 - 20).toInt()) - 20
                    if (offSetX % 2 == 0) offSetX = -offSetX
                    var offSetY = rand.nextInt((y_dis/2 - 20).toInt())
                    if (offSetY % 3 == 0) offSetY = -offSetY
                    map[loc] = doubleArrayOf(x + offSetX + 7.5, y + offSetY + 7.5)
                    g.fillOval(x + offSetX, y + offSetY, 15.0, 15.0)
                    g.font = Font.font("Chiller", 25.0)
                    g.fillText(loc, x + offSetX - loc.length * 1.5, y + offSetY - 5.0)
                    x += x_dis
                    if (x > 760) {
                        x = x_dis/2
                        y += y_dis
                    }
                }
            }
            g.stroke = Color.BLACK
            for (edge in list_loc) {
                g.setLineDashes(10.0)
                val start_X = map[edge["startLocation"]!!]!![0]
                val start_Y = map[edge["startLocation"]!!]!![1]
                val end_X = map[edge["endLocation"]!!]!![0]
                val end_Y = map[edge["endLocation"]!!]!![1]
                g.lineWidth = 1.0
                g.strokeLine(start_X, start_Y, end_X, end_Y)
            }
            g.setLineDashes(0.0)
        }
    }

    /**
     * Return details of the map
     */
    fun getDescription(list_loc: List<Map<String, Any>>): String {
        var details = ""
        if (list_loc.size > 0) {
            for (loc in list_loc) {
                details += loc["startLocation"].toString() + " => " + loc["endLocation"].toString()
                details += ": " + loc["distance"].toString() + "\n"
            }
        }
        return details
    }
}