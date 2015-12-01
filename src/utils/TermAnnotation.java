/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static gui.MainInterface.executeOper;
import static gui.MainInterface.ontologyNames;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import ontologyLayer.AnatomyEncapsulate;
import ontologyLayer.DiseasesEncapsulate;
import ontologyLayer.EdamEncapsulate;
import ontologyLayer.GenoEncapsulate;
import ontologyLayer.MeshEncapsulate;
import ontologyLayer.OgmsEncapsulate;
import ontologyLayer.WordNetEncapsulate;

/**
 *
 * @author A
 */
public class TermAnnotation {

    public static String findTerms(String sent) {

        return "";
    }

    public static String loadTermsFromOntologys(String fullSent, String ontologyNames) {
        String fullSentMeaning="";
        StringTokenizer str = new StringTokenizer(fullSent);
        while (str.hasMoreTokens()) {
            String oneToken = str.nextToken();
            String[] ontologyArr = ontologyNames.split("\t");
            for (String oneOntologyName : ontologyArr) {
                if (!"".equals(oneOntologyName)) {
                    String fullTermMeaning=findTerm(oneToken, oneOntologyName);
                    fullSentMeaning+=fullTermMeaning+"\n";
                    JOptionPane.showMessageDialog(null,fullTermMeaning);
                }
            }
        }
        return fullSentMeaning;
    }
    
    public static String loadPhraseFromOntologys(String fullPhrase, String ontologyNames) {
        String fullSentMeaning="";
            String[] ontologyArr = ontologyNames.split("\t");
            for (String oneOntologyName : ontologyArr) {
                if (!"".equals(oneOntologyName)) {
                    String fullTermMeaning=findPhrase(fullPhrase, oneOntologyName);
                    fullSentMeaning+=fullTermMeaning+"\n";
                    JOptionPane.showMessageDialog(null,fullTermMeaning);
                }
            }
        
        return fullSentMeaning;
    }

    private static String findTerm(String oneToken, String oneOntologyName) {
        String fullMeaning="";
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.WORDNET_ONTOLOGY)){
           fullMeaning= WordNetEncapsulate.termMeanings(oneToken);
        }
         if(oneOntologyName.equalsIgnoreCase(OntologyConstants.GENO_ONTOLOGY)){
            fullMeaning=GenoEncapsulate.termMeaning(oneToken);
        }
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.DISEASE_ONTOLOGY)){
            fullMeaning=DiseasesEncapsulate.termMeaning(oneToken);
        }
         return fullMeaning;
    }
    
    private static String findPhrase(String phrseWords, String oneOntologyName) {
        String fullMeaning="";
        
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.WORDNET_ONTOLOGY)){
           fullMeaning= WordNetEncapsulate.phraseMeanings(phrseWords);
        }
         if(oneOntologyName.equalsIgnoreCase(OntologyConstants.GENO_ONTOLOGY)){
            fullMeaning=GenoEncapsulate.phraseMeanings(phrseWords);
        }
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.DISEASE_ONTOLOGY)){
            fullMeaning=DiseasesEncapsulate.phraseMeanings(phrseWords);
        }
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.OGMS_ONTOLOGY)){
           fullMeaning= OgmsEncapsulate.phraseMeanings(phrseWords);
        }
        
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.MESH_ONTOLOGY)){
           fullMeaning= MeshEncapsulate.phraseMeanings(phrseWords);
        }
        
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.EDAM_ONTOLOGY)){
           fullMeaning= EdamEncapsulate.phraseMeanings(phrseWords);
        }
        
        if(oneOntologyName.equalsIgnoreCase(OntologyConstants.ANATOMY_ONTOLOGY)){
           fullMeaning= AnatomyEncapsulate.phraseMeanings(phrseWords);
        }
        
         return fullMeaning;
    }

}
