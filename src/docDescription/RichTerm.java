/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package docDescription;

/**
 *
 * @author A
 */
public class RichTerm {
    
    private String termOriginWord;
    private String termStemmedWord;
    private String termTag;
    private int termPos;
    private String termDef;
    private String termRelatedSyno;
    private String termExactSyno;

    public int getTermPos() {
        return termPos;
    }

    public void setTermPos(int termPos) {
        this.termPos = termPos;
    }
    
    

    public String getTermOriginWord() {
        return termOriginWord;
    }

    public void setTermOriginWord(String termOriginWord) {
        this.termOriginWord = termOriginWord;
    }

    public String getTermStemmedWord() {
        return termStemmedWord;
    }

    public void setTermStemmedWord(String termStemmedWord) {
        this.termStemmedWord = termStemmedWord;
    }

    public String getTermTag() {
        return termTag;
    }

    public void setTermTag(String termTag) {
        this.termTag = termTag;
    }

    public String getTermDef() {
        return termDef;
    }

    public void setTermDef(String termDef) {
        this.termDef = termDef;
    }

    public String getTermRelatedSyno() {
        return termRelatedSyno;
    }

    public void setTermRelatedSyno(String termRelatedSyno) {
        this.termRelatedSyno = termRelatedSyno;
    }

    public String getTermExactSyno() {
        return termExactSyno;
    }

    public void setTermExactSyno(String termExactSyno) {
        this.termExactSyno = termExactSyno;
    }

    public String getTermSubTerm() {
        return termSubTerm;
    }

    public void setTermSubTerm(String termSubTerm) {
        this.termSubTerm = termSubTerm;
    }
    private String termSubTerm;
    
    
}
