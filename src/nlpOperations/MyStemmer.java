/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nlpOperations;




import edu.stanford.nlp.ling.*; 
import edu.stanford.nlp.ling.CoreAnnotations.*;  
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;
import java.util.*;

public class MyStemmer
{
    public static void main(String[] args)
    {
        String text="this is a test question";
        Properties props = new Properties(); 
        props.put("annotators", "tokenize, ssplit, pos, lemma"); 
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props, false);
        
        Annotation document = pipeline.process(text);  

        for(CoreMap sentence: document.get(SentencesAnnotation.class))
        {    
            for(CoreLabel token: sentence.get(TokensAnnotation.class))
            {       
                String word = token.get(TextAnnotation.class);      
                String lemma = token.get(LemmaAnnotation.class); 
                System.out.println("lemmatized version :" + lemma);
            }
        }
    }
    
    public static String sentLemma(String inputSent){
        
        String stemmedSent="";
        Properties props = new Properties(); 
        props.put("annotators", "tokenize, ssplit, pos, lemma"); 
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props, false);
        
        Annotation document = pipeline.process(inputSent);  

        for(CoreMap sentence: document.get(SentencesAnnotation.class))
        {    
            for(CoreLabel token: sentence.get(TokensAnnotation.class))
            {       
                String word = token.get(TextAnnotation.class);      
                String lemma = token.get(LemmaAnnotation.class); 
                System.out.println("lemmatized version :" + lemma);
                stemmedSent+=" "+lemma;
            }
        }
        return stemmedSent;
    }
}
