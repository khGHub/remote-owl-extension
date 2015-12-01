/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package docDescription;

/**
 *
 * @author Administrator
 */
public class DomainDocObject {
    
    private String name;
    private String label;
    private String def;
    private String exactSyno;
    private String relatedSyno;
    private String containSent;
    private String ontology;
    private int tf=0;
    private DomainDocObject subClass;
    
    

    public DomainDocObject getSubClass() {
        return subClass;
    }

    public void setSubClass(DomainDocObject subClass) {
        this.subClass = subClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getExactSyno() {
        return exactSyno;
    }

    public void setExactSyno(String exactSyno) {
        this.exactSyno = exactSyno;
    }

    public String getRelatedSyno() {
        return relatedSyno;
    }

    public void setRelatedSyno(String relatedSyno) {
        this.relatedSyno = relatedSyno;
    }

    public String getContainSent() {
        return containSent;
    }

    public void setContainSent(String containSent) {
        this.containSent = containSent;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }
    
    
}
