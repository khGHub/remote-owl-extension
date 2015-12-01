/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 *
 * @author A
 */
class TagText {
    
    static MaxentTagger tagger;
    static 
    {
        String taggerPath="D:\\owl-ontology\\models\\wsj-0-18-left3words-distsim.tagger";
        tagger = new MaxentTagger(taggerPath);
    }

    public static String tag(String oneSen) {
       
        String tagged = tagger.tagString(oneSen);
        // Output the result    
        System.out.println(tagged);
        return tagged;
    }
    
      public static String tagWords(String oneSen) {
       String taggedSent="";
       String[] tokens = oneSen.split(" ");
       for(int i=0;i<tokens.length;i++){
            String tagged = tagger.tagString(tokens[i]);
            taggedSent+=tagged+"\n";
            System.out.println(tagged);
        }
       return taggedSent;
    }

    
    
  
    String getTextVerbs(String textForVerbs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getTextNames(String textForNames) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
