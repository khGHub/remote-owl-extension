/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
import nlpOperations.RunStanfordParser;
import nlpOperations.StopWordList;
import ontologyLayer.WordNetManager;
import uk.ac.ebi.demo.ols.business.OLSClient;
import static uk.ac.ebi.demo.ols.business.OLSClient.getOntologies2;

/**
 *
 * @author A
 */
public class ConceptExpansion extends javax.swing.JFrame {
    
    private Vector originSent=new Vector();
    private Vector plagSent=new Vector();
    private Map orginalConcepts=new HashMap();
    private Map plagConcepts=new HashMap();
    OLSClient olsClient=null;

    /**
     * Creates new form ConceptExpansion
     */
    public ConceptExpansion() {
        initComponents();
        this.olsClient=new OLSClient();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        mainSplitter = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOriginInputPane = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPlagiarizedText = new javax.swing.JTextPane();
        btnExecute = new javax.swing.JButton();
        btnPhrases = new javax.swing.JButton();
        btnStemming = new javax.swing.JButton();
        btnExpansion = new javax.swing.JButton();
        btnTermHiligth = new javax.swing.JButton();
        btnShowConceptsW = new javax.swing.JButton();
        btnDetectPlagiarsm = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        settingMenu = new javax.swing.JMenu();
        loadOntologyMI = new javax.swing.JMenuItem();
        MultiOntoMI = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainSplitter.setDividerLocation(250);
        mainSplitter.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        txtOriginInputPane.setText("orginal text");
        jScrollPane1.setViewportView(txtOriginInputPane);

        mainSplitter.setTopComponent(jScrollPane1);

        txtPlagiarizedText.setText("plagiarized text");
        jScrollPane2.setViewportView(txtPlagiarizedText);

        mainSplitter.setRightComponent(jScrollPane2);

        btnExecute.setText("execute");
        btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });

        btnPhrases.setText("phrases & nouns");
        btnPhrases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhrasesActionPerformed(evt);
            }
        });

        btnStemming.setText("stemming");
        btnStemming.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStemmingActionPerformed(evt);
            }
        });

        btnExpansion.setText("expansion");
        btnExpansion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpansionActionPerformed(evt);
            }
        });

        btnTermHiligth.setText("terms");
        btnTermHiligth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermHiligthActionPerformed(evt);
            }
        });

        btnShowConceptsW.setText("show wiegths");
        btnShowConceptsW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowConceptsWActionPerformed(evt);
            }
        });

        btnDetectPlagiarsm.setText("detect plagiarism");
        btnDetectPlagiarsm.setToolTipText("");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        settingMenu.setLabel("Settings");

        loadOntologyMI.setLabel("load ontology");
        loadOntologyMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadOntologyMIActionPerformed(evt);
            }
        });
        settingMenu.add(loadOntologyMI);

        MultiOntoMI.setLabel("mulit ontology");
        MultiOntoMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MultiOntoMIActionPerformed(evt);
            }
        });
        settingMenu.add(MultiOntoMI);

        jMenuBar2.add(settingMenu);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainSplitter, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnExecute)
                .addGap(18, 18, 18)
                .addComponent(btnPhrases)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStemming)
                .addGap(18, 18, 18)
                .addComponent(btnExpansion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTermHiligth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowConceptsW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetectPlagiarsm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExecute)
                    .addComponent(btnPhrases)
                    .addComponent(btnStemming)
                    .addComponent(btnExpansion)
                    .addComponent(btnTermHiligth)
                    .addComponent(btnShowConceptsW)
                    .addComponent(btnDetectPlagiarsm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainSplitter, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
      /*
       steps are 
        1- get text sentences
        2- remove stop words / detect
        3- pos tagging for all words
        4- stemming
        5- detect nous / verbs
        6- detect related parts
       */
        
        String inputText=this.txtOriginInputPane.getText();
        Vector txtSentences = StopWordList.segmenttxt(inputText);
        String outPutStr="";
        for (int i = 0; i < txtSentences.size(); i++) {
                String oneSen = (String) txtSentences.get(i);
                Vector taggedSent = RunStanfordParser.taggingStemming(oneSen);
                oneSen = RunStanfordParser.tagOperations(oneSen);
                //oneSen=TagText.tagWords(oneSen);
               System.out.println(oneSen+"\n");
               outPutStr+="\n"+oneSen;
        }
        this.txtPlagiarizedText.setText(outPutStr);
        this.txtPlagiarizedText.repaint();
          
            
        
    }//GEN-LAST:event_btnExecuteActionPerformed

    private void btnPhrasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhrasesActionPerformed
        /*
        for orginial and plag text we will do the next 
       steps are 
        1- get text sentences
        2- tagging 
        3- stemming
        4- remove stop words / detect
        5- detect nous / verbs
        6- detect phrases
        */
        String outputStr="";
        String inputText=this.txtOriginInputPane.getText();
        Vector txtSentences = StopWordList.segmenttxt(inputText);
        this.originSent=txtSentences;
       
        for (int i = 0; i < txtSentences.size(); i++) {
            String oneSen = (String) txtSentences.get(i);
            if (!oneSen.equalsIgnoreCase("")) {
                Map nouns = RunStanfordParser.getNouns(oneSen);
                String phrases = RunStanfordParser.getPhrases(oneSen);

                //nouns = nouns.replaceAll("#", "  ");
                //String stemmedNouns = RunStanfordParser.sentStemming(nouns);

                outputStr += oneSen + "\n";
                outputStr += "$$$\n";
                outputStr += "nouns are :\n" + nouns + "\n";
                outputStr += "phrases are :\n" + phrases + "\n";
                outputStr += "after burn \n";
                //nouns=nounBurnning(stemmedNouns);
               // phrases=phraseBurnning(phrases);
                outputStr += "new nouns are :\n" + nouns + "\n";
                outputStr += "new phrases are :\n" + phrases + "\n";
                addConcepts(nouns,phrases);
                
            }
        }
        
        this.txtOriginInputPane.setText(outputStr);
        this.txtOriginInputPane.repaint();
        
        
        //now we will do the perv steps to the plag text
        String outputPlagStr="";
        String inputPlagText=this.txtPlagiarizedText.getText();
        Vector txtPlagSentences = StopWordList.segmenttxt(inputPlagText);
        this.plagSent=txtPlagSentences;
        for (int i = 0; i < txtPlagSentences.size(); i++) {
            String oneSen = (String) txtPlagSentences.get(i);
            if (!oneSen.equalsIgnoreCase("")) {
                Map nouns = RunStanfordParser.getNouns(oneSen);
                String phrases = RunStanfordParser.getPhrases(oneSen);

                //nouns = nouns.replaceAll("#", "  ");
                //String stemmedNouns = RunStanfordParser.sentStemming(nouns);

                outputPlagStr +=oneSen + "\n";
                outputPlagStr += "$$$\n";
                outputPlagStr += "nouns are :\n" + nouns + "\n";
                outputPlagStr += "phrases are :\n" + phrases + "\n";
                outputPlagStr += "after burn \n";
                //nouns=nounBurnning(stemmedNouns);
               // phrases=phraseBurnning(phrases);
                outputPlagStr += "new nouns are :\n" + nouns + "\n";
                outputPlagStr += "new phrases are :\n" + phrases + "\n";
                addPlagConcepts(nouns,phrases);
                
            }
        }
        
        this.txtPlagiarizedText.setText(outputPlagStr);
        this.txtPlagiarizedText.repaint();
          
    }//GEN-LAST:event_btnPhrasesActionPerformed

    private String nounBurnning(String tokens){
        String burnedSent="";
        StringTokenizer st=new StringTokenizer(tokens);
        while(st.hasMoreTokens()){
            String oneToken=st.nextToken();
            if( !burnedSent.contains(oneToken)){
                burnedSent+=" "+oneToken;
            }
        }        
        return burnedSent;
    }
    
     private String phraseBurnning(String tokens){
        String burnedSent="";
        StringTokenizer st=new StringTokenizer(tokens,"#");
        while(st.hasMoreTokens()){
            String oneToken=st.nextToken();
            if( !burnedSent.contains(oneToken)){
                burnedSent+=" "+oneToken;
            }
        }        
        return burnedSent;
    }
    
    private void btnStemmingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStemmingActionPerformed
        /*here only stem sent
          for the orginal text and plag text
          
        */
        String outputStr="";
        String inputText=this.txtOriginInputPane.getText();
        Vector txtSentences = StopWordList.segmenttxt(inputText);
       
        for (int i = 0; i < txtSentences.size(); i++){
                String oneSen = (String) txtSentences.get(i);
                String stemmedSent=RunStanfordParser.sentStemming(oneSen);
                outputStr+="setmmed sent is :\n"+stemmedSent+"\n";
        }
        this.txtOriginInputPane.setText(outputStr);
        this.txtOriginInputPane.repaint();
        
        //here we will do the prev steps to the plag text
        String outputPlagStr="";
        String inputPlagText=this.txtPlagiarizedText.getText();
        Vector txtPlagSentences = StopWordList.segmenttxt(inputPlagText);
       
        for (int i = 0; i < txtPlagSentences.size(); i++){
                String oneSen = (String) txtPlagSentences.get(i);
                String stemmedSent=RunStanfordParser.sentStemming(oneSen);
                outputPlagStr+="setmmed sent is :\n"+stemmedSent+"\n";
        }
        this.txtPlagiarizedText.setText(outputPlagStr);
        this.txtPlagiarizedText.repaint();
        
       
    }//GEN-LAST:event_btnStemmingActionPerformed

    private void btnExpansionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpansionActionPerformed
    /*
       here we will expand the concepts using web service 
        we will get 
        1-concept metaData
        2-concept childern
        3-concept parents
        4-concept relations
        
       we will expand the terms for the orginal text and plag text
    */
    //expand the orginal concepts    
    Iterator iter=orginalConcepts.keySet().iterator();
     while(iter.hasNext()){
        String currentConcept=(String)iter.next();
        Map conceptInfo=(Map) orginalConcepts.get(currentConcept); 
        String conceptPosition=conceptInfo.get("position").toString();
        String conceptWeight=conceptInfo.get("weight").toString();
        expandTerm(currentConcept,conceptPosition,conceptWeight);
    }
    //System.out.println("orginal text  expasion done");
    JOptionPane.showMessageDialog(null,"orginal text  expasion done");
    
    //expand the plag concepts
    Iterator plagIter=plagConcepts.keySet().iterator();
     while(plagIter.hasNext()){
         String currentPlagConcept=(String)plagIter.next();
         Map plagConceptInfo=(Map) plagConcepts.get(currentPlagConcept); 
         String plagConceptPosition=plagConceptInfo.get("position").toString();
         String plagConceptWeight=plagConceptInfo.get("weight").toString();
         expandPlagTerm(currentPlagConcept,plagConceptPosition,plagConceptWeight);
    }
    //System.out.println("orginal text  expasion done");
    JOptionPane.showMessageDialog(null,"plag text  expasion done");
        
    }//GEN-LAST:event_btnExpansionActionPerformed

    private void btnTermHiligthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermHiligthActionPerformed
        // TODO add your handling code here:
        //get origin text and sentences and concepts
       // String inputOriginText=this.txtOriginInputPane.getText();
       // inputOriginText=inputOriginText.split("$$$")[0].trim();
        Vector txtOriginSentences = this.originSent;
        //get plag text and sentences and concepts
        //String inputPlagText=this.txtPlagiarizedText.getText();
        //inputPlagText=inputPlagText.split("$$$")[0].trim();
        Vector txtPlagSentences = this.plagSent;
        TermHighlight th=new TermHighlight(txtOriginSentences,this.orginalConcepts,txtPlagSentences,this.plagConcepts);
        
    }//GEN-LAST:event_btnTermHiligthActionPerformed

    private void loadOntologyMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadOntologyMIActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null,"pressed");
        new OntologyLoader().setVisible(true);
        
    }//GEN-LAST:event_loadOntologyMIActionPerformed

    private void MultiOntoMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MultiOntoMIActionPerformed
        // TODO add your handling code here:
        new MultiOntology().setVisible(true);
    }//GEN-LAST:event_MultiOntoMIActionPerformed

    private void btnShowConceptsWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowConceptsWActionPerformed
        // TODO add your handling code here:
        Vector originConceptsW=generateConceptsW(this.orginalConcepts);
        Vector plagConceptsW=generateConceptsW(this.plagConcepts);
        ConceptWeights cw=new ConceptWeights(orginalConcepts,originConceptsW,plagConcepts,plagConceptsW);
        cw.setVisible(true);
    }//GEN-LAST:event_btnShowConceptsWActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConceptExpansion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConceptExpansion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConceptExpansion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConceptExpansion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Properties props= new Properties(System.getProperties());
         props.put("http.proxySet", "true");
         props.put("http.proxyHost", "10.1.8.66");     //the host and port need to be configured
         props.put("http.proxyPort", "3128");             //for your network environment.
         props.put("http.proxyUser", "khaled-omar");       //optional username
         props.put("http.proxyPassword", "654321");   //optional password
         Properties newprops = new Properties(props);
         System.setProperties(newprops);
         
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConceptExpansion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MultiOntoMI;
    private javax.swing.JButton btnDetectPlagiarsm;
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnExpansion;
    private javax.swing.JButton btnPhrases;
    private javax.swing.JButton btnShowConceptsW;
    private javax.swing.JButton btnStemming;
    private javax.swing.JButton btnTermHiligth;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem loadOntologyMI;
    private javax.swing.JSplitPane mainSplitter;
    private javax.swing.JMenu settingMenu;
    private javax.swing.JTextPane txtOriginInputPane;
    private javax.swing.JTextPane txtPlagiarizedText;
    // End of variables declaration//GEN-END:variables

    private void addConcepts(Map nouns, String phrases) {
        Iterator iter=nouns.keySet().iterator();
        while(iter.hasNext()){
            String conceptName=(String)iter.next();
            String conceptPosition=nouns.get(conceptName).toString();
            Map conceptInfo=new HashMap();
            conceptInfo.put("position",conceptPosition);
            conceptInfo.put("weight",1);
            this.orginalConcepts.put(conceptName,conceptInfo);
        }
        
        
        StringTokenizer sts=new StringTokenizer(phrases,"#");
        while(sts.hasMoreTokens()){
            String onePhrase=sts.nextToken();
            String [] onePharseArr=onePhrase.split(":");
            Map conceptInfo=new HashMap();
            conceptInfo.put("position",onePharseArr[1]);
            conceptInfo.put("weight",1);
            this.orginalConcepts.put(onePharseArr[0],conceptInfo);
        }
    }
    
    private void addPlagConcepts(Map nouns, String phrases) {
        Iterator iter=nouns.keySet().iterator();
        while(iter.hasNext()){
            String conceptName=(String)iter.next();
            String conceptPosition=nouns.get(conceptName).toString();
            Map conceptInfo=new HashMap();
            conceptInfo.put("position",conceptPosition);
            conceptInfo.put("weight",1);
            this.plagConcepts.put(conceptName,conceptInfo);
        }
        
        
        StringTokenizer sts=new StringTokenizer(phrases,"#");
        while(sts.hasMoreTokens()){
            String onePhrase=sts.nextToken();
            String [] onePharseArr=onePhrase.split(":");
            Map conceptInfo=new HashMap();
            conceptInfo.put("position",onePharseArr[1]);
            conceptInfo.put("weight",1);
            this.plagConcepts.put(onePharseArr[0],conceptInfo);
        }
    }

    private void expandTerm(String concept,String conceptPosition,String conceptWeight){
        /*
         first we will find the term ontology 
         then we will find the term by exact name to get it id 
         then we can get term meta data and term children and 
         term parents and term relations 
         */
       
       
        
        String termOntology = olsClient.geTermOntology(concept);
        //the term is contained into one medical ontology at least
        if (!termOntology.equals("")) {
            if( ! MultiOntology.multiOntology){
                HashMap allTermInfo = olsClient.getAllTermInfo(concept, conceptPosition,termOntology,conceptWeight);
                this.orginalConcepts.replace(concept, allTermInfo);
            }else
            {
                JOptionPane.showMessageDialog(null,"more than one ontology :"+termOntology+" concept is :"+concept);  
            }
         
        }
        else// the term is not contained into any of medical ontologies so we will search in wordnet about it
        {
           HashMap allTermInfo=WordNetManager.getAllTermInfo(concept, conceptPosition,"WN",conceptWeight);
           this.orginalConcepts.replace(concept, allTermInfo);
        }
    }
    
    
    private void expandPlagTerm(String concept,String conceptPosition,String conceptWeight){
        /*
         first we will find the term ontology 
         then we will find the term by exact name to get it id 
         then we can get term meta data and term children and 
         term parents and term relations 
         */
       
       
        
        String termOntology = olsClient.geTermOntology(concept);
        //the term is contained into one medical ontology at least
        if (!termOntology.equals("")) {
            if( ! MultiOntology.multiOntology){
                HashMap allTermInfo = olsClient.getAllTermInfo(concept, conceptPosition,termOntology,conceptWeight);
                this.plagConcepts.replace(concept, allTermInfo);
            }else
            {
                JOptionPane.showMessageDialog(null,"more than one ontology :"+termOntology+" concept is :"+concept);  
            }
         
        }
        else// the term is not contained into any of medical ontologies so we will search in wordnet about it
        {
           HashMap allTermInfo=WordNetManager.getAllTermInfo(concept, conceptPosition,"WN",conceptWeight);
           this.plagConcepts.replace(concept, allTermInfo);
        }
    }

    private Vector generateConceptsW(Map orginalConcepts){

        Vector conceptsW=new Vector();
        Iterator keysIter=orginalConcepts.keySet().iterator();
        while(keysIter.hasNext()){
            String conceptName=keysIter.next().toString();
            Map conceptInfo=(Map)orginalConcepts.get(conceptName);
            //get the concept Weight
            String conceptWeight=conceptInfo.get("weight").toString();
            String conceptOntology=conceptInfo.get("ontology").toString();
            //put the concept with its weight into vector
            conceptsW.add(conceptName+"_"+conceptWeight);
            //get concept parents Map or List
            Object conceptParents=conceptInfo.get("parents");
            if(conceptParents instanceof Map){
            Iterator parentsIter=((Map)conceptParents).keySet().iterator();
            while(parentsIter.hasNext()){
                    String parentConceptCode=parentsIter.next().toString();
                    String parentConceptName=((Map)conceptParents).get(parentConceptCode).toString();
                    System.out.println("parent key: "+parentConceptCode+"  value : "+parentConceptName);
                    conceptsW.add(parentConceptName+"_"+"0.5");
                }
            }
            else
            {
                List<String> parentsList=(List<String>) conceptParents;
                for (int i = 0; i < parentsList.size(); i++) {
                     System.out.println("parent entity is : "+parentsList.get(i));
                     conceptsW.add(parentsList.get(i)+"_"+"0.5");
                }
            }
            //get concepts childs Map or List
            Object conceptChilds=conceptInfo.get("childs");
            if(conceptChilds instanceof Map){
            Iterator childsIter=((Map)conceptChilds).keySet().iterator();
            while(childsIter.hasNext()){
                    String childConceptCode=childsIter.next().toString();
                    String childConceptName=((Map)conceptChilds).get(childConceptCode).toString();
                    System.out.println("child key: "+childConceptCode+"  value : "+childConceptName);
                    conceptsW.add(childConceptName+"_"+"0.5");
                }
            }
            else
            {
                List<String> childsList=(List<String>) conceptChilds;
                for (int i = 0; i < childsList.size(); i++) {
                     System.out.println("child entity is : "+childsList.get(i));
                     conceptsW.add(childsList.get(i)+"_"+"0.5");
                }
            }
            //get concepts relations Map or List
            Object conceptRelations=conceptInfo.get("relations");
            if(conceptRelations instanceof Map){
            Iterator relationsIter=((Map)conceptRelations).keySet().iterator();
            while(relationsIter.hasNext()){
                    String relationConceptCode=relationsIter.next().toString();
                    String relationType=((Map)conceptRelations).get(relationConceptCode).toString();
                    
                    if(relationType.equalsIgnoreCase("is_a")){
                       String relationConceptName=olsClient.getRelationTermById(relationConceptCode,conceptOntology);
                       System.out.println("is_a concept is  :");
                       conceptsW.add(relationConceptName+"_"+"0.5");
                    }
                    if(relationType.equalsIgnoreCase("part_of")){
                       String relationConceptName=olsClient.getRelationTermById(relationConceptCode,conceptOntology); 
                       System.out.println("is_a concept is  :");
                       conceptsW.add(relationConceptName+"_"+"0.5");
                    }
                    
                    System.out.println("relation key: "+relationConceptCode+"  relation val : "+relationType);
                }
            }
            else
            {
                List<String> relationsList=(List<String>) conceptRelations;
                for (int i = 0; i < relationsList.size(); i++) {
                    System.out.println("relation entity is : "+relationsList.get(i));
                     conceptsW.add(relationsList.get(i)+"_"+"0.5");
                }
            }
            
        }
        System.out.println("done");
        return conceptsW;
    }
}
