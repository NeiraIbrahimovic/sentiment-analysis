package portfolio.algorithms;

/*
 ***** Important!  Please Read! *****
 *
 *  - Do NOT remove any of the existing import statements
 *  - Do NOT import additional junit packages 
 *  - You MAY add in other non-junit packages as needed
 * 
 *  - Do NOT remove any of the existing test methods or change their name
 *  - You MAY add additional test methods. If you do, they should all pass
 * 
 *  - ALL of your assert test cases within each test method MUST pass, otherwise the 
 *        autograder will fail that test method
 *  - You MUST write the require number of assert test cases in each test method, 
 *        otherwise the autograder will fail that test method
 *  - You MAY write more than the required number of assert test cases as long as they all pass
 * 
 *  - All of your assert test cases within a method must be related to the method they are meant to test
 *  - All of your assert test cases within a method must be distinct and non-trivial
 *  - Your test cases should reflect the method requirements in the homework instruction specification
 * 
 *  - Your assert test cases will be reviewed by the course instructors and they may take off
 *        points if your assert test cases to do not meet the requirements
 */

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class AnalyzerTest {
	
	@Test
	void testReadFile() {
		/* 
		 * TODO Write at least 3 assert test cases that test your 'readFile' method
		 * Review the homework instructions and write assert test related the this methods specification
		 * All 3 assert statements MUST pass.
		 */
		
		//TEST 1: VALID FILE
		
		//Read in text file
		// Resolve the fixture from the recovered Eclipse project layout.
		List<Sentence> sentences1 = Analyzer.readFile("src/reviews.txt");
		
		//Ensure List of sentences is not empty
        assertFalse(sentences1.isEmpty(), "The array of sentences should not be empty after reading the file.");
        
        //TEST 2: NULL FILE
        
        //Read in null file
        List<Sentence> sentences2 = Analyzer.readFile(null);
        
        //Ensure List of sentences is empty because file was null
        assertTrue(sentences2.isEmpty(), "The array of sentences should be empty after reading a null file.");
		
        //TEST 3: INVALID FORMAT FILE
        
        //Read in a file with invalid format
        List<Sentence> sentences3 = Analyzer.readFile("invalid_reviews.txt");
        
        //Ensure List of sentences is empty because file is invalid
        assertTrue(sentences3.isEmpty(), "The array of sentences should be empty after reading an invalid file.");
	}

	@Test
	void testAllOccurrences() {
		/* 
		 * TODO Write at least 3 assert test cases that test your 'allOccurrences' method
		 * Review the homework instructions and write assert test related the this methods specification
		 * All 3 assert statements MUST pass.
		 */

		//TEST 1: VALID SENTENCE
		
		//Create a list of sentences 
		List<Sentence> sentences4 = new ArrayList<>();;
		sentences4.add(new Sentence(2, "Java Class"));
		sentences4.add(new Sentence(-1, "Late night homework"));
		
		//Call the method to get all occurrences of words
        List<String> words = Analyzer.allOccurrences(sentences4);
        assertEquals(Arrays.asList("java", "class", "late", "night", "homework"), words, 
        		"A list of words in the array should be returned when calling the allOccurrences method.");
        
        //TEST 2: NULL SENTENCE
        
        //Ensure that calling the method on a null sentence returns an empty list
        assertTrue(Analyzer.allOccurrences(null).isEmpty(), 
        		"The allOccurrences method shall return an empty string when the input is null.");
        
        //TEST 3: EMPTY LIST INPUT
        
        //Ensure that an empty list is returned when calling the allOccurrences method on a list with no sentences
        assertTrue(Analyzer.allOccurrences(new ArrayList<>()).isEmpty(), 
        		"An empty list shall be returned by the allOccurrences method when an empty string is used as input");
	}

	@Test
	void testUniqueWords() {
		/* 
		 * TODO Write at least 3 assert test cases that test your 'uniqueWords' method
		 * Review the homework instructions and write assert test related the this methods specification
		 * All 3 assert statements MUST pass.
		 */

		//TEST 1: VALID SENTENCE
		
		//Create a list of sentences 
				List<Sentence> sentences5 = new ArrayList<>();
				sentences5.add(new Sentence(2, "Java Class"));
				sentences5.add(new Sentence(-1, "Late night homework"));
				
				//Call the method to get all unique occurrences of words
		        Set<String> words = Analyzer.uniqueWords(sentences5);
		        
		        //Ensure a set of unique words is returned
		        assertEquals(Set.of("java", "class", "late", "night", "homework"), words, 
		        		"A list of unique words in the set should be returned when calling the uniqueWords method.");
		        
		        
				List<Sentence> sentences6 = new ArrayList<>();;
				sentences6.add(new Sentence(-2, "Java Java Class Class"));
				sentences6.add(new Sentence(-1, "Late late night homework"));
				
				//Call the method to get all unique occurrences of words
		        Set<String> words2 = Analyzer.uniqueWords(sentences5);
		        
		        //Ensure a set of unique words is returned
		        assertEquals(Set.of("java", "class", "late", "night", "homework"), words2, 
		        		"A list of unique words in the set should be returned when calling the uniqueWords method.");
		        
		  //TEST 2: NULL SENTENCE
		        
		    //Ensure a null input returns an empty list
		    assertTrue(Analyzer.uniqueWords(null).isEmpty());
		    
		 //TEST 3: EMPTY LIST INPUT
		    
		    //Ensure an empty list is returned if an empty list is passed as an input
		    assertTrue(Analyzer.uniqueWords(new ArrayList<>()).isEmpty());
	}

	@Test
	void testWordTallies() {
		/* 
		 * TODO Write at least 3 assert test cases that test your 'wordTallies' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 3 assert statements MUST pass.
		 */

		//TEST 1: VALID SENTENCE
        
		//Create a list of sentences
		List<Sentence> sentences6 = new ArrayList<>();
		sentences6.add(new Sentence(-1, "Today is a good day"));
		sentences6.add(new Sentence(-1, "Good day today"));
		
		//Call the method to get the word tallies on the list of sentences
        Map<String, ObservationTally> tallies = Analyzer.wordTallies(sentences6);
        
        //Ensure the HashMap contains the word from the list of sentences
        assertTrue(tallies.containsKey("good"));
        //Ensure the count for the word is accurate
        assertEquals(2, tallies.get("good").getCount());
        //Ensure the total for the word is accurate
        assertEquals(-2, tallies.get("good").getTotal());
        
    	//Create a list of sentences
  		List<Sentence> sentences7 = new ArrayList<>();
  		sentences7.add(new Sentence(2, "Life is great"));
  		sentences7.add(new Sentence(2, "Life is beautiful"));
  		sentences7.add(new Sentence(-1, "Today is raining"));
  		
  		
  		//Call the method to get the word tallies on the list of sentences
          Map<String, ObservationTally> tallies2 = Analyzer.wordTallies(sentences7);
          
         //Ensure the HashMap contains the word from the list of sentences
          assertTrue(tallies2.containsKey("life"));
         //Ensure the count for the word is accurate 
          assertEquals(2, tallies2.get("life").getCount());
         //Ensure the total for the word is accurate 
          assertEquals(4, tallies2.get("life").getTotal());
          
        //Ensure the HashMap contains the word from the list of sentences
          assertTrue(tallies2.containsKey("is"));
         //Ensure the count for the word is accurate 
          assertEquals(3, tallies2.get("is").getCount());
        //Ensure the total for the word is accurate 
          assertEquals(3, tallies2.get("is").getTotal());
        
        //TEST 2: NULL INPUT
      
        //Ensure an empty map is returned if a null input is passed 
	    assertTrue(Analyzer.wordTallies(null).isEmpty());
	    
        
	    //TEST 3: EMPTY LIST INPUT
	  
        //Ensure an empty map is returned if an empty list is passed as an input
	    assertTrue(Analyzer.wordTallies(new ArrayList<>()).isEmpty());
	          
        
	}

	@Test
	void testCalculateScores() {
		/* 
		 * TODO Write at least 3 assert test cases that test your 'calculateScores' method
		 * Review the homework instructions and write assert test related the this methods specification
		 * All 3 assert statements MUST pass.
		 */

		//TEST 1: VALID TALLIES
		
		//Create a HashMap with words and observation tallies
		Map<String, ObservationTally> originalTallies = new HashMap<>();
		
		//Add values to the HashMap
		originalTallies.put("word1", new ObservationTally(2, 4));
		originalTallies.put("word2", new ObservationTally(4, -1));
        
        //Call the method to calculate scores
        Map<String, Double> averageTallies = Analyzer.calculateScores(originalTallies);
        
        //Ensure the scores are accurate for the words
        assertEquals(2.0, averageTallies.get("word1"));
        assertEquals(-0.25, averageTallies.get("word2"));
        
        //TEST 2: ORIGINAL MAP MUST NOT BE MODIFIED
        
        //Ensure the values in the original map are not modified
        assertEquals(2, originalTallies.get("word1").getCount());
        assertEquals(4, originalTallies.get("word1").getTotal());
        
        //TEST 3: NULL MAP
        
        //Ensure the method returns an empty map if the input is null
        assertTrue(Analyzer.calculateScores(null).isEmpty());
        
        //TEST 4: EMPTY INPUT
        
        //Ensure the method returns an empty map if the input is empty
        assertTrue(Analyzer.calculateScores(new HashMap<>()).isEmpty());   
	}

	@Test
	void testCalculateSentenceScore() {
		/* 
		 * TODO Write at least 3 assert test cases that test your 'calculateSentenceScore' method
		 * Review the homework instructions and write assert test related the this methods specification
		 * All 3 assert statements MUST pass.
		 */

		//TEST 1: VALID INPUT
		
		//Create a map with words and scores
        Map<String, Double> scores = Map.of("happy", 1.0, "day", 0.5);
        
        //Call the method with input text that includes all the words in the map
        double result = Analyzer.calculateSentenceScore(scores, "happy day");
        
        //Ensure the output sentiment score is the arithmetic mean for all the valid words that come from the input Map
        assertEquals(0.75, result);
        
        //Create a map with words and scores
        Map<String, Double> scores2 = Map.of("happy", 1.0, "day", 0.5, "great", 0.5);
        
        //Call the method with input text that includes all the words in the map
        double result2 = Analyzer.calculateSentenceScore(scores2, "happy day");
        
        //Ensure the output sentiment score is the arithmetic mean for all the valid words that come from the input Map
        assertEquals(0.75, result2);
        
        
        //TEST 2: NULL INPUT
        
        //Create a map with words and scores
        Map<String, Double> scores3 = Map.of("happy", 1.0, "day", 0.5);
        
        //Ensure the method returns 0 if the input is null
        assertEquals(0, Analyzer.calculateSentenceScore(scores3, null));
        
        //TEST 3: EMPTY INPUT
        
        //Create a map with words and scores
        Map<String, Double> scores4 = Map.of("happy", 1.0, "day", 0.5);
        
        //Ensure the method returns 0 if the input is empty
        assertEquals(0, Analyzer.calculateSentenceScore(scores4, ""));
        
        //TEST 4: SOME VALID WORDS
        
        //Create a map with words and scores
        Map<String, Double> scores5 = Map.of("happy", 1.0, "day", 0.5);
        
        //Ensure the method returns the correct score if there are some valid words
        assertEquals(0.25, Analyzer.calculateSentenceScore(scores5, "happy was beautiful non"));
        
        //TEST 5: DUPLICATE WORDS
        
        //Create a map with words and scores
        Map<String, Double> scores6 = Map.of("happy", 1.0, "day", 0.5);
        
        //Ensure the method includes duplicate words in the input statement text
        assertEquals(0.8, Analyzer.calculateSentenceScore(scores6, "happy day day happy happy"));
        
        //TEST 6: NO VALID WORDS
        
        //Create a map with words and scores
        Map<String, Double> scores7 = Map.of("happy", 1.0, "day", 0.5);
        
        //Ensure the method returns 0 if there are no valid words
        assertEquals(0, Analyzer.calculateSentenceScore(scores7, "was beautiful non"));
	}
}
