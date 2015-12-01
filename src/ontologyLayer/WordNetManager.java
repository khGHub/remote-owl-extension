package ontologyLayer;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.VerbSynset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ontologyLayer.WordNetEncapsulate.database;
import static ontologyLayer.WordNetEncapsulate.loadModel;
import static ontologyLayer.WordNetEncapsulate.termMeanings;

/**
 *
 * Usage example
 * 
 * WordNetManager.init("your path for wordnet-home/dict");
 *       String word = "fly";
 *       List<String> childs = WordNetManager.getChilds(word);
 *       List<String> synonyms = WordNetManager.getSynonyms(word);
 *       List<String> parents = WordNetManager.getParents(word);
 *       List<String> relatedWords = WordNetManager.getRelatedWords(word);
 *       String def = WordNetManager.getFirstDefinition(word);
 * 
 * 
 * @author al.hasan.fadel@gmail.com
 */
public class WordNetManager {

    private static WordNetDatabase database;
    /****
     * Should be called before calling any other function
     * @param dictPath 
     */
    
    static{
        init("");
    }
    
    public static void init(String dictPath) {
        //System.setProperty("wordnet.database.dir", dictPath);
        //database = WordNetDatabase.getFileInstance();
         System.setProperty("wordnet.database.dir", "D:\\owl-lib\\dict\\");
         database = WordNetDatabase.getFileInstance();
         System.out.print("wordNet load done\n");
    }

    public static List<String> getSynonyms(String word) {
        List<String> synonyms = new ArrayList<>();
        Synset[] nounSynsets = database.getSynsets(word, SynsetType.NOUN, true);
        for (Synset synset : nounSynsets) {
            String[] forms = synset.getWordForms();
            for (String form : forms) {
                if(!synonyms.contains(form))
                synonyms.add(form);
            }
        }
        Synset[] verbSynsets = database.getSynsets(word, SynsetType.VERB, true);
        for (Synset synset : verbSynsets) {
            String[] forms = synset.getWordForms();
            for (String form : forms) {
                if(!synonyms.contains(form))
                synonyms.add(form);
            }
        }
        return synonyms;
    }

    public static List<String> getChilds(String word) {
        List<String> childs = new ArrayList<>();
        Synset[] nounSynsets = database.getSynsets(word, SynsetType.NOUN, true);
        Synset[] hyponyms;
        String[] forms;
        for (Synset synset : nounSynsets) {
            hyponyms = ((NounSynset) synset).getHyponyms();
            for (Synset hyponym : hyponyms) {
                forms = hyponym.getWordForms();
                for (String form : forms) {
                if(!childs.contains(form))
                    childs.add(form);
                }
            }
        }
        Synset[] verbSynsets = database.getSynsets(word, SynsetType.VERB, true);
        Synset[] troponyms;
        for (Synset synset : verbSynsets) {
            troponyms = ((VerbSynset) synset).getTroponyms();
            for (Synset troponym : troponyms) {
                forms = troponym.getWordForms();
                for (String form : forms) {
                if(!childs.contains(form))
                    childs.add(form);
                }
            }
        }
        return childs;
    }

    public static List<String> getParents(String word) {
        List<String> psrents = new ArrayList<>();
        Synset[] nounSynsets = database.getSynsets(word, SynsetType.NOUN, true);
        Synset[] hypernyms;
        String[] forms;
        for (Synset synset : nounSynsets) {
            hypernyms = ((NounSynset) synset).getHypernyms();
            for (Synset hypernym : hypernyms) {
                forms = hypernym.getWordForms();
                for (String form : forms) {
                if(!psrents.contains(form))
                    psrents.add(form);
                }
            }
        }
        Synset[] verbSynsets = database.getSynsets(word, SynsetType.VERB, true);

        for (Synset synset : verbSynsets) {
            hypernyms = ((VerbSynset) synset).getHypernyms();
            for (Synset hypernym : hypernyms) {
                forms = hypernym.getWordForms();
                for (String form : forms) {
                if(!psrents.contains(form))
                    psrents.add(form);
                }
            }
        }
        return psrents;
    }

    public static List<String> getRelatedWords(String word) {
        List<String> relatedWords = new ArrayList<>();
        Synset[] nounSynsets = database.getSynsets(word, SynsetType.NOUN, true);
        Synset[] partHolonyms;
        Synset[] meronyms;
        String[] forms;
        for (Synset synset : nounSynsets) {
            partHolonyms = ((NounSynset) synset).getPartHolonyms();
            meronyms = ((NounSynset) synset).getPartMeronyms();
            for (Synset partHolonym : partHolonyms) {
                forms = partHolonym.getWordForms();
                for (String form : forms) {
                if(!relatedWords.contains(form))
                    relatedWords.add(form);
                }
            }
            for (Synset myronym : meronyms) {
                forms = myronym.getWordForms();
                for (String form : forms) {
                if(!relatedWords.contains(form))
                    relatedWords.add(form);
                }
            }
        }

        Synset[] verbSynsets = database.getSynsets(word, SynsetType.VERB, true);
        Synset[] outcomes;
        Synset[] entailments;
        for (Synset synset : verbSynsets) {
            outcomes = ((VerbSynset) synset).getOutcomes();
            entailments = ((VerbSynset) synset).getEntailments();
            for (Synset outcome : outcomes) {
                forms = outcome.getWordForms();
                for (String form : forms) {
                if(!relatedWords.contains(form))
                    relatedWords.add(form);
                }
            }
            for (Synset entailment : entailments) {
                forms = entailment.getWordForms();
                for (String form : forms) {
                if(!relatedWords.contains(form))
                    relatedWords.add(form);
                }
            }
        }
        return relatedWords;
    }

    public static String getFirstDefinition(String word){
        Synset[] nounSynsets = database.getSynsets(word, SynsetType.NOUN, true);
        if (nounSynsets.length > 0) {
            return nounSynsets[0].getDefinition();
        } else {
            Synset[] verbSynsets = database.getSynsets(word, SynsetType.VERB, true);
            if (verbSynsets.length > 0) {
                return verbSynsets[0].getDefinition();
            }
        }
        return null;
    }
    
    public static HashMap getAllTermInfo(String termName,String conceptPosition,String termOntology,String conceptWeight){

            HashMap infoMap=new HashMap();
            
            //get term childs,parents,relations,metadata
            List<String> parentsList=getParents(termName);
            List<String> relationsList=getRelatedWords(termName);
            List<String> childsList=getChilds(termName);
            String definition=getFirstDefinition(termName);
          
            infoMap.put("weight",conceptWeight);
            infoMap.put("position",conceptPosition);
            infoMap.put("ontology","WN");
            infoMap.put("parents",parentsList);
            infoMap.put("childs",childsList);
            infoMap.put("relations",relationsList);
            infoMap.put("metaData",definition);
            System.out.println("done");
            
            return infoMap;
    }
    
    public static void main(String[] args) {
      
        getFirstDefinition("normal");
    }

}
