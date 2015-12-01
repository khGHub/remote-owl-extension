/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import docDescription.DomainDocObject;
import docDescription.PublicDocObject;
import gui.MainInterface;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import nlpOperations.MyStemmer;
import nlpOperations.RunStanfordParser;
import nlpOperations.StopWordList;
import ontologyLayer.OntologyManager;

/**
 *
 * @author A
 */
public class Goperations {

    public static void GeneralOper(JTextPane inputTxtPane, String operationName, String ontologyNames) {

        if (operationName.equals(Constants.SEGMENTATION)) {
            String inputTxt=inputTxtPane.getText();
            Vector txtSentences = StopWordList.segmenttxt(inputTxt);
            String allSen = "";
            for (int i = 0; i < txtSentences.size(); i++) {
                String oneSen = (String) txtSentences.get(i);
                allSen += oneSen + "\n";
            }
            inputTxtPane.setText(allSen);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

        if (operationName.equals(Constants.S_W_REMOVE)) {
            String allSent = inputTxtPane.getText();
            String[] senArr = allSent.split("\n");
            String newSenWithoutSP = "";
            for (int i = 0; i < senArr.length; i++) {
                String oneSen = (String) senArr[i];
                oneSen = StopWordList.removeSP(oneSen);
                newSenWithoutSP += oneSen + "\n";
            }
            inputTxtPane.setText(newSenWithoutSP);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

        if (operationName.equals(Constants.TAGGING)) {
            String allSent = inputTxtPane.getText();
            String[] senArr = allSent.split("\n");
            String newTaggedSent = "";
            for (int i = 0; i < senArr.length; i++) {
                String oneSen = (String) senArr[i];
                oneSen = RunStanfordParser.tagOperations(oneSen);
                //oneSen=TagText.tagWords(oneSen);
                newTaggedSent += oneSen + "\n";
            }
            inputTxtPane.setText(newTaggedSent);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

        if (operationName.equals(Constants.STEMMING)) {

            String allSent = inputTxtPane.getText();
            String[] senArr = allSent.split("\n");
            String newStemmedSen = "";
            for (int i = 0; i < senArr.length; i++) {
                String oneSen = (String) senArr[i];
                oneSen = MyStemmer.sentLemma(oneSen);
                newStemmedSen += oneSen + "\n";
            }
            inputTxtPane.setText(newStemmedSen);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

        if (operationName.equals(Constants.TREM_DETECTION)) {

            String allSent = inputTxtPane.getText();
            String[] senArr = allSent.split("\n");
            String newSenWithTerms = "";
            for (int i = 0; i < senArr.length; i++) {
                String oneSen = (String) senArr[i];
                oneSen = TermAnnotation.loadTermsFromOntologys(oneSen, ontologyNames);
                JOptionPane.showMessageDialog(null, "new meaning " + oneSen);
                newSenWithTerms += oneSen + "\n";
            }
            inputTxtPane.setText(newSenWithTerms);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

        if (operationName.equals(Constants.PHRASE_DETECTION)) {

            String allSent = inputTxtPane.getText();
            String[] senArr = allSent.split("\n");
            String newSenWithTerms = "";
            for (int i = 0; i < senArr.length; i++) {
                String oneSen = (String) senArr[i];
                oneSen = TermAnnotation.loadPhraseFromOntologys(oneSen, ontologyNames);
                JOptionPane.showMessageDialog(null, "new meaning " + oneSen);
                newSenWithTerms += oneSen + "\n";
            }
            inputTxtPane.setText(newSenWithTerms);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

        if (operationName.equals(Constants.SEMANTIC_MEANING)) {
            /**
             * the steps are : 1-detect sentences 2-detect nouns and adjectives
             * 3-detect phrases 4-detect verbs 5-find terms meanings form
             * ontology
             */
            String sentNouns = null;
            String sentPhrases = null;
            String sentVerbs = null;
            String allSent = inputTxtPane.getText();
            //1-detect text sentences
            Vector segmantedTxt = StopWordList.segmenttxt(allSent);

            for (int i = 0; i < segmantedTxt.size(); i++) {
                String currentSent = (String) segmantedTxt.get(i);
                if (!currentSent.trim().equalsIgnoreCase("")) {
                    //2- detect nous,adjectives 
                    sentNouns = "";//RunStanfordParser.getNouns(currentSent);
                    //3-detect phrases
                    sentPhrases = RunStanfordParser.getPhrases(currentSent);
                    //4-detect verbs
                    sentVerbs = RunStanfordParser.getVerbs(currentSent);

                    //5-detect semantic meaning of nous,adj,phrases
                    String[] nounsArr = sentNouns.split("#");
                    String[] phraseArr = sentPhrases.split("#");
                    String[] verbsArr = sentVerbs.split("#");
                    System.out.println("begin nouns \n\n");

                    for (String currentNoun : nounsArr) {
                        HashMap semanticNounMean = OntologyManager.findSemanticMeaning(currentNoun);
                        Object[] keysArr = semanticNounMean.keySet().toArray();
                        System.out.println("Noun is " + currentNoun + "  meaning are below ");
                        for (int J = 0; J < keysArr.length; J++) {
                            String currentNounMeaning = (String) semanticNounMean.get((String) keysArr[J]);
                            if (!currentNounMeaning.equals(Constants.NO_VALUE)) {
                                System.out.println((String) keysArr[J] + ":::" + currentNounMeaning);
                                updateDocIndex(currentNoun, currentSent, (String) keysArr[J], currentNounMeaning);
                                MainInterface.doc1Des.getDorcRichNouns().put(currentNoun + "#" + keysArr[J], currentNounMeaning);
                            }
                        }
                        System.out.println("\n\n");
                    }
                    for (String currentPhrase : phraseArr) {
                        HashMap semanticPhraseMean = OntologyManager.findSemanticMeaning(currentPhrase);
                        Object[] keysArr = semanticPhraseMean.keySet().toArray();
                        System.out.println("phrase is " + currentPhrase + "  meaning are below ");
                        for (int h = 0; h < keysArr.length; h++) {
                            String currentPhraseMeaning = (String) semanticPhraseMean.get((String) keysArr[h]);
                            if (!currentPhraseMeaning.equals(Constants.NO_VALUE)) {
                                System.out.println((String) keysArr[h] + ":::" + currentPhraseMeaning);
                                MainInterface.doc1Des.getDocRichPhrases().put(currentPhrase + "#" + keysArr[h], currentPhraseMeaning);
                                updateDocIndex(currentPhrase, currentSent, (String) keysArr[h], currentPhraseMeaning);

                            }
                        }
                        System.out.println("\n\n");
                    }
                    for (String currentVerb : verbsArr) {
                        HashMap semanticVerbMean = OntologyManager.findSemanticMeaning(currentVerb);
                        Object[] keysArr = semanticVerbMean.keySet().toArray();
                        System.out.println("verb is " + currentVerb + "  meaning are below ");
                        for (int k = 0; k < keysArr.length; k++) {
                            String currentVerbMeaning = (String) semanticVerbMean.get((String) keysArr[k]);
                            if (!currentVerbMeaning.equals(Constants.NO_VALUE)) {
                                System.out.println((String) keysArr[k] + ":::" + currentVerbMeaning);
                                MainInterface.doc1Des.getDocRichVerbs().put(currentVerb + "#" + keysArr[k], currentVerbMeaning);
                            }
                        }
                        System.out.println("\n\n");
                    }
                }
            }
            System.out.println("\n\n");
        }

        if (operationName.equals(Constants.NOUNS)) {
            String textForNames = inputTxtPane.getText();
            String namesOnly = "";//RunStanfordParser.getNouns(textForNames);
            inputTxtPane.setText(namesOnly);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }
        if (operationName.equals(Constants.VERBS)) {
            String textForVerbs = inputTxtPane.getText();
            String verbsOnly = new TagText().getTextVerbs(textForVerbs);
            inputTxtPane.setText(verbsOnly);
            inputTxtPane.validate();
            inputTxtPane.repaint();
        }

    }

    private static void updateDocIndex(String concept, String containSent, String ontology, String currentNounMeaning) {

//        public String toString() {
//        return    "label is :" + this.label + "\n"
//                + "name is :" + this.name + "\n"
//                + "def is :" + this.definition + "\n"
//                + "exactSyno is :" + this.exactSyno + "\n"
//                + "relatedSyno is :" + this.relatedSyno + "\n"
//                + "subClass is :" + subClass.getLabel();
//    }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (ontology.equalsIgnoreCase(OntologyConstants.WORDNET_ONTOLOGY)) {

            PublicDocObject publicDocObj = new PublicDocObject();
            publicDocObj.setLabel(concept);
            publicDocObj.setDef(currentNounMeaning);
            publicDocObj.setContainSent(containSent);
            publicDocObj.setOntology(OntologyConstants.WORDNET_ONTOLOGY);
            publicDocObj.setTf(publicDocObj.getTf() + 1);
            if (!MainInterface.doc1Des.getPublicDocIndex().containsKey(concept)) {
                MainInterface.doc1Des.getPublicDocIndex().put(concept, publicDocObj);
            } else {
                updatePublicConceptValue(concept, publicDocObj);
            }
        } else {
            String[] objArr = currentNounMeaning.split("\n");

            DomainDocObject docObj = new DomainDocObject();
            docObj.setLabel(objArr[0]);
            docObj.setName(objArr[1]);
            docObj.setDef(objArr[2]);
            docObj.setExactSyno(objArr[3]);
            docObj.setRelatedSyno(objArr[4]);
            docObj.setContainSent(containSent);
            docObj.setOntology(ontology);
            docObj.setTf(docObj.getTf() + 1);

            DomainDocObject sunDocObj = new DomainDocObject();
            sunDocObj.setLabel(objArr[5]);
            docObj.setSubClass(sunDocObj);
            if (!MainInterface.doc1Des.getDominDocIndex().containsKey(concept)) {
                MainInterface.doc1Des.getDominDocIndex().put(concept, docObj);
            } else {
                updateDomainConceptValue(concept, docObj);
            }
        }

        addOntologyIndexValue(concept, containSent, ontology, currentNounMeaning);
    }

    private static void updatePublicConceptValue(String concept, PublicDocObject publicDocObj) {
        PublicDocObject oldValue = (PublicDocObject) MainInterface.doc1Des.getPublicDocIndex().remove(concept);
        String oldContainedSent = oldValue.getContainSent();
        String newContainedSent = publicDocObj.getContainSent();
        String updateContainedSent = "";
        if (oldContainedSent.trim().equalsIgnoreCase(newContainedSent.trim())) {
            updateContainedSent = oldContainedSent;
        } else {
            updateContainedSent = oldContainedSent + "#" + newContainedSent;
        }

        int oldTf = oldValue.getTf();
        oldValue.setContainSent(updateContainedSent);
        oldValue.setTf(++oldTf);

        MainInterface.doc1Des.getPublicDocIndex().put(concept, oldValue);
    }

    private static void updateDomainConceptValue(String concept, DomainDocObject docObj) {
        DomainDocObject oldValue = (DomainDocObject) MainInterface.doc1Des.getDominDocIndex().remove(concept);
        String oldContainedSent = oldValue.getContainSent();
        String newContainedSent = docObj.getContainSent();
        String updateContainedSent = "";
        if (oldContainedSent.trim().equalsIgnoreCase(newContainedSent.trim())) {
            updateContainedSent = oldContainedSent;
        } else {
            updateContainedSent = oldContainedSent + "#" + newContainedSent;
        }

        int oldTf = oldValue.getTf();
        oldValue.setContainSent(updateContainedSent);
        oldValue.setTf(++oldTf);

        MainInterface.doc1Des.getDominDocIndex().put(concept, oldValue);

    }

    private static void addOntologyIndexValue(String concept, String containSent, String ontology, String currentNounMeaning) {

        if (ontology.equalsIgnoreCase(OntologyConstants.WORDNET_ONTOLOGY)) {

            PublicDocObject publicDocObj = new PublicDocObject();
            publicDocObj.setLabel(concept);
            publicDocObj.setDef(currentNounMeaning);
            publicDocObj.setContainSent(containSent);
            publicDocObj.setOntology(OntologyConstants.WORDNET_ONTOLOGY);
            publicDocObj.setTf(publicDocObj.getTf() + 1);

            if (!MainInterface.doc1Des.getOntologyIndex().containsKey(OntologyConstants.WORDNET_ONTOLOGY)) {
                //this is the first obj in the ontology index to this ontology type
                Vector publicOntVector = new Vector();
                publicOntVector.add(publicDocObj);
                MainInterface.doc1Des.getOntologyIndex().put(OntologyConstants.WORDNET_ONTOLOGY, publicOntVector);
            } else {
                //this is another object belongs to the current ontology
                updateOntologyIndexValue(OntologyConstants.WORDNET_ONTOLOGY, publicDocObj);
            }
        } else {
            String[] objArr = currentNounMeaning.split("\n");
            DomainDocObject dominDocObj = new DomainDocObject();
            dominDocObj.setLabel(objArr[0]);
            dominDocObj.setName(objArr[1]);
            dominDocObj.setDef(objArr[2]);
            dominDocObj.setExactSyno(objArr[3]);
            dominDocObj.setRelatedSyno(objArr[4]);
            dominDocObj.setContainSent(containSent);
            dominDocObj.setOntology(ontology);
            dominDocObj.setTf(dominDocObj.getTf() + 1);

            DomainDocObject sunDocObj = new DomainDocObject();
            sunDocObj.setLabel(objArr[5]);
            dominDocObj.setSubClass(sunDocObj);

            if (!MainInterface.doc1Des.getOntologyIndex().containsKey(ontology)) {
                //this is the first obj in the ontology index to this ontology type
                Vector dominOntVector = new Vector();
                dominOntVector.add(dominDocObj);
                MainInterface.doc1Des.getOntologyIndex().put(ontology, dominOntVector);
            } else {
                updateOntologyIndexValue(ontology, dominDocObj);
                // System.out.println();
            }
        }
    }

    private static void updateOntologyIndexValue(String ontology, PublicDocObject newPublicDocObj) {
        Vector publicOntVect = (Vector) MainInterface.doc1Des.getOntologyIndex().remove(ontology);
        publicOntVect.add(newPublicDocObj);
        MainInterface.doc1Des.getOntologyIndex().put(ontology, publicOntVect);
    }

    private static void updateOntologyIndexValue(String ontology, DomainDocObject newDominDocObj) {
        Vector domainOntVect = (Vector) MainInterface.doc1Des.getOntologyIndex().remove(ontology);
        domainOntVect.add(newDominDocObj);
        MainInterface.doc1Des.getOntologyIndex().put(ontology, domainOntVect);
    }

}
