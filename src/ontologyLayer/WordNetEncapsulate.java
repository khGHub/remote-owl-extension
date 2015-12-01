/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologyLayer;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import javax.swing.JOptionPane;
import utils.Constants;

/**
 *
 * @author A
 */
public class WordNetEncapsulate {

    public static String phraseMeanings(String phrseWords) {
        String meaningStr = Constants.NO_VALUE;

        NounSynset nounSynset;
        NounSynset[] hyponyms;

        if (database != null) {

            Synset[] synsets = database.getSynsets(phrseWords, SynsetType.NOUN);
            
            for (int i = 0; i < synsets.length; i++) {
                nounSynset = (NounSynset) (synsets[i]);
                hyponyms = nounSynset.getHyponyms();
                meaningStr += nounSynset.getDefinition() + "\n";
                System.err.println(nounSynset.getWordForms()[0]
                        + ": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms");
                
                
            }
        }
        return meaningStr;

    }

    public static void loadOnt() {
        if(database==null)
        loadModel();
        else
            JOptionPane.showMessageDialog(null,"WordNet already loaded");
    }

    private String conceptName;
    private String conceptSynonyms;
    private String conceptHyponyms;

    public static WordNetDatabase database=null;

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public void setConceptSynonyms(String conceptSynonyms) {
        this.conceptSynonyms = conceptSynonyms;
    }

    public String getConceptName() {
        return conceptName;
    }

    public String getConceptSynonyms() {
        return conceptSynonyms;
    }

    public String getConceptHyponyms() {
        return conceptHyponyms;
    }

    public void setConceptHyponyms(String conceptHyponyms) {
        this.conceptHyponyms = conceptHyponyms;
    }

    public static void loadModel() {
        System.setProperty("wordnet.database.dir", "D:\\owl-lib\\dict\\");
        database = WordNetDatabase.getFileInstance();
        System.out.print("wordNet load done\n");

    }

    public static String termMeanings(String term) {
        String meaningStr = "";
        
        NounSynset nounSynset;
        NounSynset adjNounSynset;
        String definition="";
        NounSynset[] hyponyms;
        NounSynset[] topics;
        Synset[] topicsMembers;
        String [] WordForms;

        Synset[] synsets = database.getSynsets(term, SynsetType.NOUN);
        String[] adjSynsets = database.getBaseFormCandidates(term,SynsetType.ADJECTIVE);
        
        for (int i = 0; i < adjSynsets.length; i++) {
           
            
           System.out.println("adj def is :"+adjSynsets[i].toString()) ;
           
        }
        
        
        
        for (int i = 0; i < synsets.length; i++) {
            nounSynset = (NounSynset) (synsets[i]);
            
            definition= nounSynset.getDefinition() ;
            hyponyms = nounSynset.getHyponyms();
            topics=nounSynset.getTopics();
            topicsMembers=nounSynset.getTopicMembers();
            WordForms=nounSynset.getWordForms();
            
//            System.err.println(nounSynset.getWordForms()[0]
//                    + ": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms");
//            System.out.println("forms length is :"+nounSynset.getWordForms().length);
//            System.out.println("wordForms:"+nounSynset.getWordForms()[0]);
//            System.out.println("wordForms:"+nounSynset.getWordForms()[1]);
//            System.out.println("wordForms:"+nounSynset.getWordForms()[2]);
//            System.out.println("wordForms:"+nounSynset.getWordForms()[3]);
//            System.out.println("wordForms:"+nounSynset.getWordForms()[4]);
//            System.out.println("wordForms:"+nounSynset.getWordForms()[5]);
            
            System.out.println("noun def is :"+definition); 
            System.out.println("hyponyms");
            for (int j = 0; j <hyponyms.length; j++) {
               System.out.println("hyponyms["+j+"]"+ hyponyms[j].toString());
            }
            
            System.out.println("topics");
            for (int j = 0; j <topics.length; j++) {
               System.out.println("topics["+j+"]"+ topics[j].toString());
            }
            
            System.out.println("topicsMembers");
            for (int j = 0; j <topicsMembers.length; j++) {
               System.out.println("topicsMembers["+j+"]"+ topicsMembers[j].toString());
            }
            
            System.out.println("WordForms");
            for (int j = 0; j <WordForms.length; j++) {
               System.out.println("WordForms["+j+"]"+ WordForms[j].toString());
            }
            
            
            
        }
        return meaningStr;
    }

    public static void main(String[] args) {
        loadModel();
        termMeanings("normal");
    }

}
