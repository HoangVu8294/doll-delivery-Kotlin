import javafx.application.Preloader.ProgressNotification
import com.sun.javafx.application.LauncherImpl.notifyPreloader

/**
 * Created by Hoang on 3/15/2017.
 */

public class the_nice_old_lady constructor(firstName: String) {
    private var name = firstName

    public fun dijkstra(startingLocation: String, targetLocation: String, edges: List<Map<String, Any>>): Map<String, Any> {
        // Return null if startingLocation or targetLocation does not exist
        if (invalidStart(startingLocation, edges)) return mapOf()
        if (invalidTarget(targetLocation, edges)) return mapOf()
        // Current location = startLocation
        var loc = startingLocation
        // Distance travelled = 0
        var d = 0
        // A mutable map that keep track of un-visited locations
        var uLoc: MutableMap<String, Int> = mutableMapOf()
        uLoc.put(loc, d)
        // An array keeps track of visited locations
        var vLoc: Array<String?> = arrayOfNulls<String>(edges.count() + 1)
        // A path tracker
        var tracker: MutableMap<String, String> = mutableMapOf()
        var i = 0;

        // Dijkstra algorithm
        while (true) {
            if (uLoc.count() == 0) break
            loc = uLoc.minBy { it.value }!!.key
            d = uLoc.minBy { it.value }!!.value
            if (loc == targetLocation) break
            uLoc.remove(loc)
            vLoc[i++] = loc
            for (m in edges) {
                if (m["startLocation"] == loc || m["endLocation"] == loc) {
                    var to = m["endLocation"] as String
                    if (m["endLocation"] == loc) to = m["startLocation"] as String
                    if (!vLoc.contains(to)) {11111
                        var temp = d + m["distance"] as Int
                        if (!uLoc.containsKey(to)) {
                            uLoc.put(to, temp)
                            tracker.put(to, loc)
                        }
                        else if (uLoc.containsKey(to) && uLoc[to]!! > temp) {
                            uLoc[to] = temp
                            tracker[to] = loc
                        }
                    }
                }
            }
        }

        // Start from the target location
        var path = loc
        // Trace back the path until start location
        while (loc != startingLocation) {
            loc = tracker[loc] as String
            path = loc + " => " + path
        }
        return mapOf("distance " to d, "path " to path)
    }

    fun invalidStart(startingLocation: String, edges: List<Map<String, Any>>): Boolean {
        for (m in edges) {
            if (m["startLocation"] == startingLocation || m["endLocation"] == startingLocation) return false
        }
        return true
    }

    fun invalidTarget(targetLocation: String, edges: List<Map<String, Any>>): Boolean {
        for (m in edges) {
            if (m["startLocation"] == targetLocation || m["endLocation"] == targetLocation) return false
        }
        return true
    }
}
/* For testing purpose
fun main(args: Array<String>) {
    var list_loc = listOf(
            mapOf("startLocation" to "A", "endLocation" to "B", "distance" to 7),
            mapOf("startLocation" to "A", "endLocation" to "C", "distance" to 9),
            mapOf("startLocation" to "A", "endLocation" to "F", "distance" to 14),
            mapOf("startLocation" to "B", "endLocation" to "C", "distance" to 10),
            mapOf("startLocation" to "B", "endLocation" to "D", "distance" to 15),
            mapOf("startLocation" to "C", "endLocation" to "D", "distance" to 11),
            mapOf("startLocation" to "C", "endLocation" to "F", "distance" to 2),
            mapOf("startLocation" to "D", "endLocation" to "E", "distance" to 6),
            mapOf("startLocation" to "E", "endLocation" to "F", "distance" to 9))

    var grandmaC = doll_delivery.Main_Classes.nice_old_lady("Grandma C")
    var path = grandmaC.dijkstra(startingLocation = "A", targetLocation = "E", edges = list_loc)
    println(path)
}
*/





