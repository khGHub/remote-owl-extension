/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package docDescription;

import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class DocDes {
    
    private HashMap richSent;
    private HashMap docRichPhrases;
    private HashMap dorcRichNouns;
    private HashMap docRichVerbs;
    
    private HashMap dominDocIndex;
    private HashMap publicDocIndex;
    private HashMap ontologyIndex;

    public HashMap getOntologyIndex() {
        return ontologyIndex;
    }

    public void setOntologyIndex(HashMap ontologyIndex) {
        this.ontologyIndex = ontologyIndex;
    }
    

    public HashMap getDominDocIndex() {
        return dominDocIndex;
    }

    public void setDominDocIndex(HashMap dominDocIndex) {
        this.dominDocIndex = dominDocIndex;
    }

    public HashMap getPublicDocIndex() {
        return publicDocIndex;
    }

    public void setPublicDocIndex(HashMap publicDocIndex) {
        this.publicDocIndex = publicDocIndex;
    }

   

    public HashMap getDocRichVerbs() {
        return docRichVerbs;
    }

    public void setDocRichVerbs(HashMap docRichVerbs) {
        this.docRichVerbs = docRichVerbs;
    }

    public HashMap getDorcRichNouns() {
        return dorcRichNouns;
    }

    public void setDorcRichNouns(HashMap dorcRichNouns) {
        this.dorcRichNouns = dorcRichNouns;
    }

    public HashMap getDocRichPhrases() {
        return docRichPhrases;
    }

    public void setDocRichPhrases(HashMap docRichPhrases) {
        this.docRichPhrases = docRichPhrases;
    }

    public HashMap getRichSent() {
        return richSent;
    }

    public void setRichSent(HashMap richSent) {
        this.richSent = richSent;
    }
    
    public DocDes(){
        
        richSent=new HashMap();
        docRichPhrases=new HashMap();
        dorcRichNouns=new HashMap();
        docRichVerbs=new HashMap();
        dominDocIndex=new HashMap();
        publicDocIndex=new HashMap();
        ontologyIndex=new HashMap();
       
    }
    
    
}
