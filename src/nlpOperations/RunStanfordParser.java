/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpOperations;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.trees.*;
import java.io.*;
import java.util.*;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.PTBTokenizer;
import expansion.ExpandedTerm;

public class RunStanfordParser {
    
    public static String englishDataUrl = "D:\\owl-ontology\\englishPCFG.ser.gz";
    public static LexicalizedParser lp = LexicalizedParser.loadModel(englishDataUrl,
                "-maxLength", "80", "-retainTmpSubcategories");

    public static String tagOperations(String sent) {
        String resultStr="";
        StringReader sr;
        PTBTokenizer tkzr; 
        WordStemmer ls = new WordStemmer();

        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(sent);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
        System.out.println("tokens: " + toks);
        resultStr+="tokens: " + toks+"\n\n";

        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

		
        // Get words, stemmed words and POS tags
        ArrayList<String> words = new ArrayList();
        ArrayList<String> stems = new ArrayList();
        ArrayList<String> tags = new ArrayList();

        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
            words.add(tw.word());
            tags.add(tw.tag());
        }

        // Get stems
        ls.visitTree(parse); // apply the stemmer to the tree
        for (TaggedWord tw : parse.taggedYield()) {
            stems.add(tw.word());
        }

        // Get dependency tree
        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        Collection tdl = gs.typedDependenciesCollapsed();

        // And print!
        System.out.println("words: " + words);
        System.out.println("POStags: " + tags);
        System.out.println("stemmedWords: " + stems);
        System.out.println("typedDependencies: " + tdl);
        resultStr+="words: " + words+"\n\n";
        resultStr+="POStags: " + tags+"\n\n";
        resultStr+="stemmedWords: " + stems+"\n\n";
        resultStr+="typedDependencies: " + tdl+"\n\n";
        
        

		
        TreePrint tp1 = new TreePrint("wordsAndTags,latexTree");
        tp1.printTree(parse);
        System.out.println(); // separate output lines
        return resultStr;

    }
    
     public static String sentStemming(Map sent) {
        String nounsStr="";
        Iterator iter=sent.keySet().iterator();
        while(iter.hasNext()){
         nounsStr+=" "+(String)iter.next();
        }
        
        String outputStr="";
        StringReader sr;
        PTBTokenizer tkzr; 
        WordStemmer ls = new WordStemmer();

        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(nounsStr);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
       

        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

		
        // Get words, stemmed words and POS tags
        ArrayList<String> words = new ArrayList();
        ArrayList<String> stems = new ArrayList();
        ArrayList<String> tags = new ArrayList();

        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
            words.add(tw.word());
            tags.add(tw.tag());
        }

        // Get stems
        ls.visitTree(parse); // apply the stemmer to the tree
        for (TaggedWord tw : parse.taggedYield()) {
            stems.add(tw.word());
        }
        for (int i = 0; i <stems.size(); i++) {
          outputStr+=stems.get(i)+" ";
        }
        return outputStr;
     }
     
     public static String sentStemming(String sent) {
        
        
        String outputStr="";
        StringReader sr;
        PTBTokenizer tkzr; 
        WordStemmer ls = new WordStemmer();

        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(sent);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
       

        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

		
        // Get words, stemmed words and POS tags
        ArrayList<String> words = new ArrayList();
        ArrayList<String> stems = new ArrayList();
        ArrayList<String> tags = new ArrayList();

        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
            words.add(tw.word());
            tags.add(tw.tag());
        }

        // Get stems
        ls.visitTree(parse); // apply the stemmer to the tree
        for (TaggedWord tw : parse.taggedYield()) {
            stems.add(tw.word());
        }
        for (int i = 0; i <stems.size(); i++) {
          outputStr+=stems.get(i)+" ";
        }
        return outputStr;
     }
    
     public static Vector taggingStemming(String sent) {
        Vector resVector=new Vector();
        String resultStr="";
        StringReader sr;
        PTBTokenizer tkzr; 
        WordStemmer ls = new WordStemmer();

        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(sent);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
        System.out.println("tokens: " + toks);
        resultStr+="tokens: " + toks+"\n\n";

        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

		
        // Get words, stemmed words and POS tags
        ArrayList<String> words = new ArrayList();
        ArrayList<String> stems = new ArrayList();
        ArrayList<String> tags = new ArrayList();

        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
            words.add(tw.word());
            tags.add(tw.tag());
        }

        // Get stems
        ls.visitTree(parse); // apply the stemmer to the tree
        for (TaggedWord tw : parse.taggedYield()) {
            stems.add(tw.word());
        }
         for (int i = 0; i <toks.size(); i++){
             ExpandedTerm expandedTerm=new ExpandedTerm();
             expandedTerm.setTermOriginWord(toks.get(i).toString());
             expandedTerm.setTermStemmedWord(stems.get(i));
             expandedTerm.setTermTag(tags.get(i));
             expandedTerm.setIsStopWord(StopWordList.isStopWord(stems.get(i)));
             
             resVector.add(expandedTerm);
         }

        // Get dependency tree
        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        Collection tdl = gs.typedDependenciesCollapsed();

        // And print!
        System.out.println("words: " + words);
        System.out.println("POStags: " + tags);
        System.out.println("stemmedWords: " + stems);
        System.out.println("typedDependencies: " + tdl);
        resultStr+="words: " + words+"\n\n";
        resultStr+="POStags: " + tags+"\n\n";
        resultStr+="stemmedWordsAndTags: " + stems+"\n\n";
        resultStr+="typedDependencies: " + tdl+"\n\n";
        
        

		
        TreePrint tp1 = new TreePrint("wordsAndTags,latexTree");
        tp1.printTree(parse);
        System.out.println(); // separate output lines
        return resVector;

    }

      public static Map getNouns(String sent) {
        String resultStr="";  
        StringReader sr;
        PTBTokenizer tkzr; 
        Map nouns=new HashMap();
                
        WordStemmer ls = new WordStemmer();
        ArrayList<String> stems = new ArrayList();

        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(sent);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

	// Get stems
        ls.visitTree(parse); 
        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
           
            if(tw.tag().startsWith("N")){
                int nounIndex=sent.indexOf(tw.word());
                resultStr+=tw.word()+":"+nounIndex+"#";
                nouns.put(tw.word(), nounIndex);
                
            }
            if(tw.tag().startsWith("JJ")){
                int adjIndex=sent.indexOf(tw.word());
                resultStr+=tw.word()+":"+adjIndex+"#";
                nouns.put(tw.word(), adjIndex);
                
                
            }
        }
        
    return nouns;

    }

    public static void main(String[] args) throws Exception {
        

        String fileToParse = "E:\\OWL\\test.txt";
        String englishDataUrl = "E:\\phd-project-tools\\q-system\\stanford-parser-full-2014-06-16\\stanford-parser-full-2014-06-16\\englishPCFG.ser.gz";
        LexicalizedParser lp = LexicalizedParser.loadModel(englishDataUrl,
                "-maxLength", "80", "-retainTmpSubcategories");

        // Call parser on files, and tokenize the contents
        FileInputStream fstream = new FileInputStream(fileToParse);
        DataInputStream in = new DataInputStream(fstream); // Get the object of DataInputStream
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringReader sr; // we need to re-read each line into its own reader because the tokenizer is over-complicated garbage
        PTBTokenizer tkzr; // tokenizer object
        WordStemmer ls = new WordStemmer(); // stemmer/lemmatizer object

        // Read File Line By Line
        String strLine;
        while ((strLine = br.readLine()) != null) {
            System.out.println("Tokenizing and Parsing: " + strLine); // print current line to console

            // do all the standard java over-complication to use the stanford parser tokenizer
            sr = new StringReader(strLine);
            tkzr = PTBTokenizer.newPTBTokenizer(sr);
            List toks = tkzr.tokenize();
            System.out.println("tokens: " + toks);

            Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

		// Output Option 1: Printing out various data by accessing it programmatically
            // Get words, stemmed words and POS tags
            ArrayList<String> words = new ArrayList();
            ArrayList<String> stems = new ArrayList();
            ArrayList<String> tags = new ArrayList();

            // Get words and Tags
            for (TaggedWord tw : parse.taggedYield()) {
                words.add(tw.word());
                tags.add(tw.tag());
            }

            // Get stems
            ls.visitTree(parse); // apply the stemmer to the tree
            for (TaggedWord tw : parse.taggedYield()) {
                stems.add(tw.word());
            }
            

            // Get dependency tree
            TreebankLanguagePack tlp = new PennTreebankLanguagePack();
            GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
            GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
            Collection tdl = gs.typedDependenciesCollapsed();

            // And print!
            System.out.println("words: " + words);
            System.out.println("POStags: " + tags);
            System.out.println("stemmedWordsAndTags: " + stems);
            System.out.println("typedDependencies: " + tdl);

		// Output Option 2: Printing out various data using TreePrint
	 	// Various TreePrint options
            //	    "penn", // constituency parse
            //	    "oneline",
            //	    rootLabelOnlyFormat,
            //	    "words",
            //	    "wordsAndTags", // unstemmed words and pos tags
            //	    "dependencies", // unlabeled dependency parse
            //	    "typedDependencies", // dependency parse
            //	    "typedDependenciesCollapsed",
            //	    "latexTree",
            //	    "collocations",
            //	    "semanticGraph"
		// Print using TreePrint with various options
//	 	TreePrint tp = new TreePrint("wordsAndTags,semanticGraph");
//	 	tp.printTree(parse);
//		System.out.println(); // separate output lines
            TreePrint tp1 = new TreePrint("wordsAndTags,latexTree");
            tp1.printTree(parse);
            System.out.println(); // separate output lines

//                TreePrint tp2 = new TreePrint("wordsAndTags,collocations");
//	 	tp2.printTree(parse);
//		System.out.println(); // separate output lines
//                
//                TreePrint tp3 = new TreePrint("wordsAndTags,dependencies");
//	 	tp3.printTree(parse);
//		System.out.println(); // separate output lines
        }

    }

   

    public static String getPhrases(String sent) {
       
   
        StringReader sr;
        PTBTokenizer tkzr; 
        WordStemmer ls = new WordStemmer();
        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(sent);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
        System.out.println("tokens: " + toks);
       

        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something
		
        // Get words, stemmed words and POS tags
        ArrayList<String> words = new ArrayList();
        ArrayList<String> stems = new ArrayList();
        ArrayList<String> tags = new ArrayList();

        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
            words.add(tw.word());
            tags.add(tw.tag());
        }
        // Get stems
        ls.visitTree(parse); // apply the stemmer to the tree
        for (TaggedWord tw : parse.taggedYield()) {
            stems.add(tw.word());
        }
        // Get dependency tree
        TreebankLanguagePack tlp = new PennTreebankLanguagePack();
        GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        Collection tdl = gs.typedDependenciesCollapsed();
        // And print!
        System.out.println("words: " + words);
        System.out.println("POStags: " + tags);
        System.out.println("stemmedWordsAndTags: " + stems);
        System.out.println("typedDependencies: " + tdl);
        /*
        dependecy mainpulation
        */
        // remove [ ]
        
        
       
        //tokenization 
        Object [] wordRelationsArr= tdl.toArray();
        //get nn,amod relations
        String requiredRelations="";
        for (int i = 0; i <wordRelationsArr.length; i++) {
            String oneRelation=wordRelationsArr[i].toString();
            if(oneRelation.trim().startsWith("nn")||(oneRelation.trim().startsWith("amod"))){
                requiredRelations+=oneRelation+"#";
            }
        }
        
        String phrases="";
        //get nn words
        String [] requiredRelationsArr=requiredRelations.split("#");
        for (int i = 0; i < requiredRelationsArr.length; i++) {
            String oneRelation=requiredRelationsArr[i];
            if(oneRelation.trim().startsWith("nn")){
                oneRelation=oneRelation.replace("(","");
                oneRelation=oneRelation.replace(")","");
                oneRelation=oneRelation.replace("nn","");
                String [] oneRelationArr=oneRelation.split(",");
                String w1=oneRelationArr[0].split("-")[0];
                String w2=oneRelationArr[1].split("-")[0];
                int phraseIndex=sent.indexOf(w2.trim()+" "+w1.trim());
                phrases+=w2.trim()+" "+w1.trim()+":"+phraseIndex+"#";
            }
        }
        //get amod words
        
        String [] requiredRelationsArr2=requiredRelations.split("#");
        for (int i = 0; i < requiredRelationsArr2.length; i++) {
            String oneRelation=requiredRelationsArr2[i];
            if(oneRelation.trim().startsWith("amod")){
                oneRelation=oneRelation.replace("(","");
                oneRelation=oneRelation.replace(")","");
                oneRelation=oneRelation.replace("amod","");
                String [] oneRelationArr=oneRelation.split(",");
                String w1=oneRelationArr[0].split("-")[0];
                String w2=oneRelationArr[1].split("-")[0];
                int phraseIndex=sent.indexOf(w2.trim()+" "+w1.trim());
                phrases+=w2.trim()+" "+w1.trim()+":"+phraseIndex+"#";
            }
        }
        
        
        
        System.out.println("phrases are  "+phrases); 
        
       
        TreePrint tp1 = new TreePrint("wordsAndTags,latexTree");
        tp1.printTree(parse);
        System.out.println(); // separate output lines
        return phrases;
    }
    
    

    public static String getVerbs(String oneSent) {
            String resultStr="";
        String englishDataUrl = "D:\\owl-ontology\\englishPCFG.ser.gz";
        LexicalizedParser lp = LexicalizedParser.loadModel(englishDataUrl,
                "-maxLength", "80", "-retainTmpSubcategories");

           
        StringReader sr;
        PTBTokenizer tkzr; 
        

        // do all the standard java over-complication to use the stanford parser tokenizer
        sr = new StringReader(oneSent);
        tkzr = PTBTokenizer.newPTBTokenizer(sr);
        List toks = tkzr.tokenize();
        Tree parse = (Tree) lp.apply(toks); // finally, we actually get to parse something

	
        // Get words and Tags
        for (TaggedWord tw : parse.taggedYield()) {
           
            if(tw.tag().startsWith("V")){
                resultStr+=tw.word()+"#";
            }
           
        }

        

        return resultStr;
    }

}
