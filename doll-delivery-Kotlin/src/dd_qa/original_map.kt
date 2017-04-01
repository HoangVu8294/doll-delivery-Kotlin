package dd_qa

/**
 * Created by Hoang on 3/16/2017.
 */

import the_nice_old_lady
import org.testng.annotations.BeforeMethod

import org.testng.Assert.*
import org.testng.annotations.Test

/**
 * Test for mechanism of the nice old lady for the most part
 * e.g:
 * ---Q: Does the old lady find shortest path between 2 locations at a time or the trip as a whole?
 * ---A: She finds the shortest path for the whole trip as a whole.
 * ---
 * ---Q: If the direct path to the neighbor is the shortest, would she choose it or choose the one that goes around?
 * ---A: Yes she does not go around the bush if direct-&-to-the-point is the best path.
 * ---
 * ---Q: Would she go over the rainbow?
 * ---A: No she wouldn't. She can not even walk well
 */
class original_map {
    // Create an old nice lady
    private var grandmaC = the_nice_old_lady("Grandma C")
    // Initialize map
    private var list_loc = listOf(
            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to 9),
            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Greg's casa", "distance" to 4),
            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Matt's pad", "distance" to 18),
            mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Brian's apartment", "distance" to 8),
            mapOf("startLocation" to "Brian's apartment", "endLocation" to "Wesley's condo", "distance" to 7),
            mapOf("startLocation" to "Brian's apartment", "endLocation" to "Cam's dwelling", "distance" to 17),
            mapOf("startLocation" to "Greg's casa", "endLocation" to "Cam's dwelling", "distance" to 13),
            mapOf("startLocation" to "Greg's casa", "endLocation" to "Mike's digs", "distance" to 19),
            mapOf("startLocation" to "Greg's casa", "endLocation" to "Matt's pad", "distance" to 14),
            mapOf("startLocation" to "Wesley's condo", "endLocation" to "Kirk's farm", "distance" to 10),
            mapOf("startLocation" to "Wesley's condo", "endLocation" to "Nathan's flat", "distance" to 11),
            mapOf("startLocation" to "Wesley's condo", "endLocation" to "Bryce's den", "distance" to 6),
            mapOf("startLocation" to "Matt's pad", "endLocation" to "Mark's crib", "distance" to 19),
            mapOf("startLocation" to "Matt's pad", "endLocation" to "Nathan's flat", "distance" to 15),
            mapOf("startLocation" to "Matt's pad", "endLocation" to "Craig's haunt", "distance" to 14),
            mapOf("startLocation" to "Mark's crib", "endLocation" to "Kirk's farm", "distance" to 9),
            mapOf("startLocation" to "Mark's crib", "endLocation" to "Nathan's flat", "distance" to 12),
            mapOf("startLocation" to "Bryce's den", "endLocation" to "Craig's haunt", "distance" to 10),
            mapOf("startLocation" to "Bryce's den", "endLocation" to "Mike's digs", "distance" to 9),
            mapOf("startLocation" to "Mike's digs", "endLocation" to "Cam's dwelling", "distance" to 20),
            mapOf("startLocation" to "Mike's digs", "endLocation" to "Nathan's flat", "distance" to 12),
            mapOf("startLocation" to "Cam's dwelling", "endLocation" to "Craig's haunt", "distance" to 18),
            mapOf("startLocation" to "Nathan's flat", "endLocation" to "Kirk's farm", "distance" to 3))
    private var path: Map<String, Any> = mapOf()

    /**
     * Original case
     * Path: Kruthika's abode to Craig's haunt
     * Expected distance: 31
     * Expected path: "Kruthika's abode => Brian's apartment => Wesley's condo => Bryce's den => Craig's haunt"
     */
    @Test
    fun test_original_case() {
        // Set up path info
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Craig's haunt",
                edges = list_loc)
        assertEquals(path["distance "], 31)
        var temp = "Kruthika's abode => Brian's apartment => Wesley's condo => Bryce's den => Craig's haunt"
        assertEquals(path["path "], temp)
    }

    /**
     * Test non-existed locations
     * Path: Kruthika's abode to Somewhere Over Rainbow
     * & then
     * Path: Somewhere Over Rainbow to Kruthika's abode
     * Expect path map to be empty
     * Found paths are null
     */
    @Test
    fun test_wrong_way() {
        // Set targetLocation to non-existed location
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Somewhere Over Rainbow",
                edges = list_loc)
        assertNull(path["path"])
        // Set startLocation to non-existed location
        path = grandmaC.dijkstra(
                startingLocation = "Somewhere Over Rainbow",
                targetLocation = "Kruthika's abode",
                edges = list_loc)
        assertNull(path["path"])
    }

    /**
     * Path: Kruthika's abode to direct neighbors
     * Expected: 8 path " Kruthika's abode => Brian's apartment"
     * Expected: 4 path " Kruthika's abode => Greg's casa"
     * Expected: 18 path "Kruthika's abode => Matt's pad"
     * Expected: 9 path "Kruthika's abode => Mark's crib"
     */
    @Test
    fun test_neightbor_cases() {
        // Set targetLocation to Brian's apartment
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Brian's apartment",
                edges = list_loc)
        assertEquals(path["distance "], 8)
        var temp = "Kruthika's abode => Brian's apartment"
        assertEquals(path["path "], temp)

        // Set targetLocation to Greg's casa
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Greg's casa",
                edges = list_loc)
        assertEquals(path["distance "], 4)
        temp = "Kruthika's abode => Greg's casa"
        assertEquals(path["path "], temp)

        // Set targetLocation to Matt's pad
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Matt's pad",
                edges = list_loc)
        assertEquals(path["distance "], 18)
        temp = "Kruthika's abode => Matt's pad"
        assertEquals(path["path "], temp)

        // Set targetLocation to Mark's crib
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Mark's crib",
                edges = list_loc)
        assertEquals(path["distance "], 9)
        temp = "Kruthika's abode => Mark's crib"
        assertEquals(path["path "], temp)
    }

    /**
     * Path: Kruthika's abode to Wesley's condo
     * Good algorithm will not choose shortest subatomic path
     * but choose shortest distance as a whole
     * Bad algorithm
     * --- Expected distance: 41
     * --- Expected path: "Kruthika's abode => Greg's casa => Cam's dwelling => Brian's apartment => Wesley's condo"
     * Good algorithm
     * --- Expected distance: 15
     * --- Expected path: "Kruthika's abode => Brian's apartment => Wesley's condo"
     */
    @Test
    fun test_subatomic_shortest() {
        // Set up path info
        path = grandmaC.dijkstra(
                startingLocation = "Kruthika's abode",
                targetLocation = "Wesley's condo",
                edges = list_loc)
        assertNotEquals(path["distance "], 41)
        var temp = "Kruthika's abode => Greg's casa => Cam's dwelling => Brian's apartment => Wesley's condo"
        assertNotEquals(path["path "], temp)
    }
}