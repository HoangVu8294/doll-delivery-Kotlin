# doll-delivery-Kotlin

Objective
I am a nice old lady who delivers porcelain dolls on foot using my walker to residents in a neighborhood which does not sound spooky at all. Every day a nice young man who might be working for CIA or Satan, fills my handbag with porcelain dolls. He gives me a map of the neighborhood and an address of the neighbor to deliver the handbag of dolls to. The handbag is extremely heavy and I have a bad hip so I always try to minimize the walking distance from my beginning position to my destination. For most part, my job is to determine the shortest path to take to deliver my handbag of dolls. Great.

The most important task is writing a function in the Kotlin programming language when given:
•	a starting location
•	a target location
•	a list of edges where each edge is represented as a map and connects two locations
Produces the shortest path with distance which:
•	starts at the given starting location
•	ends at the given target location
The function has a signature like this:
fun dijkstra(startLocation: String, endLocation: String, edges: List<Map<String, Any>>): Map<String, Any>

where an edge is a Map with keys for startLocation, endLocation, and distance. The return value should be a Map with keys for distance and path. I have included a set of executable high-level tests for my solution along with a sample GUI app for testing multiple cases and mostly for enjoyment while testing for client (you).
 
Technologies 
•	IDE: IntelliJ IDEA 2016.3.5
•	Language: Kotlin
•	Libraries: 
	JavaFX lib       : GUI design and implementation
	TestNG           : Unit testing
	KotlinJavaRuntime: .jar file artifacts building
	JDK 1.8

Design
doll-delivery-Kotlin
Package	| Classes	      | Function
dd_gui	| alert	        | Used as an alert box
        | dd_app	      | Main class that handles all inputs from user
dd_map	| map_painter	  | Provides methods that draw the map and the path
        | map_processor |	Loads in map for user input. Currently, user can only pick between two maps 
dd_qa	  | original_map	| Tests the map that is provided through GitHub
	      | small_map	    | Tests a made up small map
