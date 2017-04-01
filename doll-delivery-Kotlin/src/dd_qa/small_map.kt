package dd_qa

/**
 * Created by Hoang on 3/16/2017.
 */

import the_nice_old_lady
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Test for the input structure for the most part
 * e.g:
 * ---Q: Do users need to input all possible paths?
 * ---   Which means if there is a path between 2 locations, do users need to list the path twice?
 * ---A: No. The nice old lady can figure it out
 * ---
 * ---Q: Do users need to worry about input order for the path established between 2 locations?
 * ---A: No. Again
 * ---   the nice old lady can handle it
 * ---
 * ---Q: Does order of map in the list matter?
 * ---A: No.
 */
class small_map {
    // Create an old nice lady
    private var grandmaC = the_nice_old_lady("Grandma C")
    // Initialize map
    private var list_loc = listOf(
            mapOf("startLocation" to "A", "endLocation" to "B", "distance" to 7),
            mapOf("startLocation" to "A", "endLocation" to "C", "distance" to 9),
            mapOf("startLocation" to "A", "endLocation" to "F", "distance" to 14),
            mapOf("startLocation" to "B", "endLocation" to "C", "distance" to 10),
            mapOf("startLocation" to "B", "endLocation" to "D", "distance" to 15),
            mapOf("startLocation" to "C", "endLocation" to "D", "distance" to 11),
            mapOf("startLocation" to "C", "endLocation" to "F", "distance" to 2),
            mapOf("startLocation" to "D", "endLocation" to "E", "distance" to 6),
            mapOf("startLocation" to "E", "endLocation" to "F", "distance" to 9))
    private var path: Map<String, Any> = mapOf()

    /**
     * Test paths from A to others in the map
     * Expected: 7 path "A => B"
     * Expected: 9 path "A => C"
     * Expected: 20 path "A => C => D"
     * Expected: 20 path "A => C => F => E"
     */
    @Test
    fun test_from_A() {
        // Set up path from A to B
        path = grandmaC.dijkstra(
                startingLocation = "A",
                targetLocation = "B",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 7)
        var temp = "A => B"
        Assert.assertEquals(path["path "], temp)

        // Set up path from A to C
        path = grandmaC.dijkstra(
                startingLocation = "A",
                targetLocation = "C",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 9)
        temp = "A => C"
        Assert.assertEquals(path["path "], temp)

        // Set up path from A to D
        path = grandmaC.dijkstra(
                startingLocation = "A",
                targetLocation = "D",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 20)
        temp = "A => C => D"
        Assert.assertEquals(path["path "], temp)

        // Set up path from A to E
        path = grandmaC.dijkstra(
                startingLocation = "A",
                targetLocation = "E",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 20)
        temp = "A => C => F => E"
        Assert.assertEquals(path["path "], temp)
    }

    /**
     * Test paths from A to F which is A's neighbor
     * Good algorithm wont pick direct path to the neighbor
     * but compute all and pick the shortest
     * Bad algorithm
     * --- Expected distance: 14
     * --- Expected path: " A => F"
     * Good algorithm
     * --- Expected distance: 11
     * --- Expected path: " A => c => F"
     */
    @Test
    fun test_from_A_tricky() {
        // Set up path from A to F
        path = grandmaC.dijkstra(
                startingLocation = "A",
                targetLocation = "F",
                edges = list_loc)
        Assert.assertNotEquals(path["distance "], 14)
        var temp = "A => F"
        Assert.assertNotEquals(path["path "], temp)
    }

    /**
     * Test path from B to others in the map
     * Expected: 10 path " B => C"
     * Expected: 15 path " B => D"
     * Expected: 21 path " B => D => E"
     * Expected: 12 path " B => C => F"
     */
    @Test
    fun test_from_B() {
        // Set up path B to C
        path = grandmaC.dijkstra(
                startingLocation = "B",
                targetLocation = "C",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 10)
        var temp = "B => C"
        Assert.assertEquals(path["path "], temp)

        // Set up path B to D
        path = grandmaC.dijkstra(
                startingLocation = "B",
                targetLocation = "D",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 15)
        temp = "B => D"
        Assert.assertEquals(path["path "], temp)

        // Set up path B to E
        path = grandmaC.dijkstra(
                startingLocation = "B",
                targetLocation = "E",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 21)
        temp = "B => C => F => E"
        Assert.assertEquals(path["path "], temp)

        // Set up path B to F
        path = grandmaC.dijkstra(
                startingLocation = "B",
                targetLocation = "F",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 12)
        temp = "B => C => F"
        Assert.assertEquals(path["path "], temp)
    }

    /**
     * Test path from C to others in the map
     * Expected: 11 path " C => D"
     * Expected: 11 path " C => F => E"
     * Expected: 2 path " C => F"
     */
    @Test
    fun test_from_C() {
        // Set up path C to D
        path = grandmaC.dijkstra(
                startingLocation = "C",
                targetLocation = "D",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 11)
        var temp = "C => D"
        Assert.assertEquals(path["path "], temp)

        // Set up path C to E
        path = grandmaC.dijkstra(
                startingLocation = "C",
                targetLocation = "E",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 11)
        temp = "C => F => E"
        Assert.assertEquals(path["path "], temp)

        // Set up path C to F
        path = grandmaC.dijkstra(
                startingLocation = "C",
                targetLocation = "F",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 2)
        temp = "C => F"
        Assert.assertEquals(path["path "], temp)
    }

    /**
     * Test path from D to others in the map
     * Expected: 6 path " D => E"
     * Expected: 13 path " D => C => F"
     */
    @Test
    fun test_from_D() {
        // Set up path D to E
        path = grandmaC.dijkstra(
                startingLocation = "D",
                targetLocation = "E",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 6)
        var temp = "D => E"
        Assert.assertEquals(path["path "], temp)

        // Set up path C to E
        path = grandmaC.dijkstra(
                startingLocation = "D",
                targetLocation = "F",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 13)
        temp = "D => C => F"
        Assert.assertEquals(path["path "], temp)
    }

    /**
     * Test path from E to others in the map
     * Expected: 9 path " E => F"
     */
    @Test
    fun test_from_E() {
        // Set up path E to F
        path = grandmaC.dijkstra(
                startingLocation = "E",
                targetLocation = "F",
                edges = list_loc)
        Assert.assertEquals(path["distance "], 9)
        var temp = "E => F"
        Assert.assertEquals(path["path "], temp)
    }
}