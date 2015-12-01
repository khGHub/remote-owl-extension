/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package docDescription;

import java.util.HashMap;

/**
 *
 * @author A
 */
public class RichSent {
    
    private HashMap richTerms;
    private HashMap richPhrases;

    public HashMap getRichPhrases() {
        return richPhrases;
    }

    public void setRichPhrases(HashMap richPhrases) {
        this.richPhrases = richPhrases;
    }

    public HashMap getRichTerms() {
        return richTerms;
    }

    public void setRichTerms(HashMap richTerms) {
        this.richTerms = richTerms;
    }
    
    
    public RichSent(){
        richTerms=new HashMap();    
        richPhrases=new HashMap();    
    }
    
}
