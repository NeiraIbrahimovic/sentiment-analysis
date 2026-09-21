package portfolio.algorithms;

/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here.>
 *
 * Note external code may reduce your score but appropriate citation is required
 * to avoid academic integrity violations. Please see the Course Syllabus as
 * well as the university code of academic integrity:
 *
 * Signed,
 * Author: Neira Ibrahimovic
 * Date: 2024-02-11
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {   

    /*
     * Implement this method in Part 1
     */
    public static List<Sentence> readFile(String filename) {
        
    	//Initialize list to store sentences from the text file
    	List<Sentence> sentences = new ArrayList<>(); 
    	
    	//EDGE CASE: If a null input is passed, return an empty list
        if (filename == null) {
        	return sentences;
        }
        
        //Create a Pattern to match the valid lines given the regular expression
        Pattern validLinePattern = Pattern.compile("^(?<score>[+-]?[0-2])\\s(?<text>.*)$"); 
        
        //Open the file for reading
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { 
            
        	//Initialize a String to hold the line currently being read
        	String line;
        	
        	//Read each line from the file if the line isn't null
            while ((line = reader.readLine()) != null) { 
            	
            	//Check if the line matches the valid line pattern (accounts for edge case where invalid lines are ignored)
                Matcher matcher = validLinePattern.matcher(line); 
                //If the line is valid
                if (matcher.matches()) { 
                	//Extract the score by retrieving the substring matched with the group "score" from our regular expression
                    int score = Integer.parseInt(matcher.group("score")); 
                    //Extract the text by retrieving the substring matched with the group "text" from our regular expression
                    String text = matcher.group("text"); 
                    
                    //Add the Sentence object with the score and text to the list
                    sentences.add(new Sentence(score, text)); 
                }
            }
        } 
        
        //Return an empty list if an error occurs
        catch (IOException e) {
        	return new ArrayList<>();   
        }
        
        //Return the list of sentences
        return sentences; 
    }

    /*
     * Implement this method in Part 2
     */
    public static List<String> allOccurrences(List<Sentence> sentences) {
        
    	//Initialize an empty list to store words
    	List<String> words = new ArrayList<>(); 
    	
    	//EDGE CASE:If the input parameter is null, return an empty list
        if (sentences == null) return words; 

        //Iterate over each sentence in the list of sentences
        for (Sentence sentence : sentences) { 
        	
        	//EDGE CASE: Ignore null sentence objects
            if (sentence == null || sentence.getText() == null) continue;
            
            //Split the sentence into words by splitting on whitespace
            String[] wordsFromSentence = sentence.getText().split("\\s+"); 
            
            //Iterate over each word
            for (String word : wordsFromSentence) { 
            	
            	//EDGE CASE: Check if the word is valid
                if (isValidWord(word)) { 
                	//If the word is valid, add the lowercase word to the list of words
                    words.add(word.toLowerCase()); 
                }
            }
        }
        
        //Return the list of words
        return words; 
    }

    /*
     * Helper method to check whether a word is valid.
     * A valid word is a token starting with one letter. Any additional characters may be letters 
     * or any other non-whitespace character.
     * 
     * @param word the String to check
     * @return true if the word is a valid word, false otherwise
     */
    private static boolean isValidWord(String word) {
		
    	//Check if the token starts with a letter
    	return word != null && !word.isEmpty() && Character.isLetter(word.charAt(0)); 
	}

	/*
     * Implement this method in Part 3
     */
    public static Set<String> uniqueWords(List<Sentence> sentences) {
       	
    	//Initialize an empty set to store words
    	Set<String> uniqueWords = new HashSet<>(); 
    	
    	//EDGE CASE:If the input parameter is null, return an empty list
        if (sentences == null) return uniqueWords; 

        //Iterate over each sentence in the list of sentences
        for (Sentence sentence : sentences) { 
        	
        	//EDGE CASE: Ignore null sentence objects
            if (sentence == null || sentence.getText() == null) continue;
            
            //Split the sentence into words by splitting on whitespace
            String[] wordsFromSentence = sentence.getText().split("\\s+"); 
            
            //Iterate over each word
            for (String word : wordsFromSentence) { 
            	
            	//EDGE CASE: Check if the word is valid
                if (isValidWord(word)) { 
                	//If the word is valid, add the lowercase word to the set of words
                    uniqueWords.add(word.toLowerCase()); 
                }
            }
        }
        
        //Return the set of unique words
        return uniqueWords; 
    }

    /*
     * Implement this method in Part 4
     */
    public static Map<String, ObservationTally> wordTallies(List<Sentence> sentences) {
        
    	//Initialize an empty map to store words
    	Map<String, ObservationTally> wordTallies = new HashMap<>();
    	
    	//EDGE CASE:If the input parameter is null, return an empty map
        if (sentences == null) return wordTallies; 
        
        //Iterate over each sentence in the list of sentences
        for (Sentence sentence : sentences) { 
        	
        //EDGE CASE: Ignore null sentence objects
        if (sentence == null || sentence.getText() == null) continue;
    	
        //Split the sentence into words by splitting on whitespace
        String[] wordsFromSentence = sentence.getText().split("\\s+"); 
        
        //Iterate over each word
        for (String word : wordsFromSentence) { 
        	
        	//Make the word lowercase
        	word = word.toLowerCase();
        	
        	//EDGE CASE: Check if the word is valid
            if (isValidWord(word)) { 
            	
            	//Initialize the obervsation tally if it is not present in the HashMap
            	wordTallies.putIfAbsent(word, new ObservationTally()); 
            	
            	//Increase the score of the tally by the score of the sentence 
                wordTallies.get(word).increaseTotal(sentence.getScore()); 
            	
            	}
        	}
        }
        	
        //Return the map of word tallies
        return wordTallies;
        }
            

    /*
     * Implement this method in Part 5
     */
    public static Map<String, Double> calculateScores(Map<String, ObservationTally> tallies) {
        
    	//Initialize an empty map to store scores
    	Map<String, Double> scores = new HashMap<>();
    	
    	//EDGE CASE:If the input parameter is null, return an empty map
        if (tallies == null) return scores;
        
        //Iterate through each key in the HashMap and put the key and associated score in the new HashMap
        tallies.forEach((key, value) -> scores.put(key, value.calculateScore()));
    	
    	//Return the map of scores
    	return scores;
    }

    /*
     * Implement this method in Part 6
     */
    public static double calculateSentenceScore(Map<String, Double> wordScores, String text) {
        
    	//EDGE CASE:If the input map is null or empty, or the statement text is null or empty, return 0
        if (wordScores == null || wordScores.isEmpty() || text == null || text.isEmpty()) return 0; 
    	
    	//Initialize an int variable to store the sum of the scores
        double scoreSum = 0.0;
        
        //Initialize an int variable to store the count of words
        int count = 0;
      
        //Split the input text into words based on whitespace
        String[] wordsFromInputText = text.split("\\s+"); 
        
        //Iterate over each word from the input text
        for (String word : wordsFromInputText) { 
        	
        	//EDGE CASE: Check if the word is valid
            if (isValidWord(word)) { 
            	
            	//Increase the count
        		count ++;
            	
            	//Convert the word to lowercase
            	word = word.toLowerCase();
            	
            	//Check if the HashMap contains the word 
            	if (wordScores.containsKey(word)){
            		
            		//Add the word's score to the sum of scores
            		scoreSum += wordScores.get(word);
            		}
        	
        	}
        }
        
        //Return the average sentence score if the number of words is greater than 0. Otherwise, return 0.
        return count > 0 ? scoreSum/count : 0;
    }

    /*
     * You do not need to modify this code but can use it for testing your program!
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify the name of the input file");
            return;
        }
        String filename = args[0];

        System.out.printf("Processing input from \"%s\".\n", filename);
        List<Sentence> sentences = Analyzer.readFile(filename);
        System.out.printf("%5d sentences read.\n", sentences.size());

        var aWords = allOccurrences(sentences);
        System.out.printf("%8d total words found.\n", aWords.size());

        Set<String> uWords = Analyzer.uniqueWords(sentences);
        System.out.printf("%8d unique words found.\n", uWords.size());

        Map<String, ObservationTally> tallies = wordTallies(sentences);
        System.out.printf("This should be the same number as the unique word count: %8d.\n", tallies.size());

        Map<String, Double> wordScores = Analyzer.calculateScores(tallies);

        String scoreAnother = "yes";
        Scanner in = new Scanner(System.in);
        while (scoreAnother.equals("yes")) {
            System.out.print("Please enter a sentence: ");
            System.out.flush();
            String sentence = in.nextLine();
            double score = Analyzer.calculateSentenceScore(wordScores, sentence);
            System.out.println("The sentiment score is " + score);

            boolean gotValidResponse = false;
            while (!gotValidResponse) {
                System.out.print("\nWould you like to score another sentence [yes/no]: ");
                System.out.flush();

                scoreAnother = in.nextLine().toLowerCase();
                switch (scoreAnother) {
                    case "yes":
                    case "no":
                        gotValidResponse = true;
                        break;
                    default:
                        System.out.println("Invalid response: " + scoreAnother);
                        gotValidResponse = false;
                }
            }
        }
        in.close();
    }
    
}
