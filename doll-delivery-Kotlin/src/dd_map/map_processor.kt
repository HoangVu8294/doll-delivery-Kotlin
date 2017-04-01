package dd_map

/**
 * Created by Hoang on 3/19/2017.
 */

class map_processor() {

    private var KGB = listOf(
            mapOf("startLocation" to "A", "endLocation" to "B", "distance" to 7),
            mapOf("startLocation" to "A", "endLocation" to "C", "distance" to 9),
            mapOf("startLocation" to "A", "endLocation" to "F", "distance" to 14),
            mapOf("startLocation" to "B", "endLocation" to "C", "distance" to 10),
            mapOf("startLocation" to "B", "endLocation" to "D", "distance" to 15),
            mapOf("startLocation" to "C", "endLocation" to "D", "distance" to 11),
            mapOf("startLocation" to "C", "endLocation" to "F", "distance" to 2),
            mapOf("startLocation" to "D", "endLocation" to "E", "distance" to 6),
            mapOf("startLocation" to "E", "endLocation" to "F", "distance" to 9))
    
    private val dudesHouse = listOf(
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

    public fun getLocList(name: String): List<Map<String, Any>> {
        if (name == "A Bunch of Dudes' Houses") return dudesHouse
        else if (name == "KGB Agents' Houses") return KGB
        else return listOf()
    }
}