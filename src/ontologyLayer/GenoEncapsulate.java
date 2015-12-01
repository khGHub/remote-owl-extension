/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologyLayer;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import static ontologyLayer.AnatomyEncapsulate.owlModel;
import utils.Constants;

/**
 *
 * @author A
 */
public class GenoEncapsulate {
    
    private String label;
    private String name;
    private String definition;
    private String exactSyno;
    private String relatedSyno;
    private Vector subClasses;
    private Vector superClasses; 

    static OntModel owlModel;
    static HashMap genoMap=null;
    

    public Vector getSubClasses() {
        return subClasses;
    }

    public void setSubClasses(Vector subClasses) {
        this.subClasses = subClasses;
    }

    public Vector getSuperClasses() {
        return superClasses;
    }

    public void setSuperClasses(Vector superClasses) {
        this.superClasses = superClasses;
    }
    

    public static String termMeaning(String oneToken) {
        String returnVal = Constants.NO_VALUE;
        if (genoMap.keySet().contains(oneToken)) {
            JOptionPane.showMessageDialog(null, "the first option befor get obj");
            GenoEncapsulate termObj = (GenoEncapsulate) genoMap.get(oneToken);
            returnVal = termObj.toString();
        }

        Iterator iter = genoMap.keySet().iterator();
        while (iter.hasNext()) {
            String genoObjLabel = (String) iter.next();
            if (genoObjLabel != null) {
                if (genoObjLabel.contains(oneToken)) {
                    GenoEncapsulate termObj = (GenoEncapsulate) genoMap.get(genoObjLabel);
                    //JOptionPane.showMessageDialog(null, "the second option");
                    returnVal = termObj.toString();
                    break;
                }
            }
        }
        return returnVal;
    }

    public static String phraseMeanings(String phrseWords) {
        String returnVal = Constants.NO_VALUE;
        if (genoMap != null) {
            if (genoMap.keySet().contains(phrseWords)) {
                GenoEncapsulate termObj;
                termObj = (GenoEncapsulate) genoMap.get(phrseWords);
                returnVal = termObj.toString();
            }
        }
        return returnVal;
    }

    

    @Override
    public String toString() {
        return    "label is :" + this.label + "\n"
                + "name is :" + this.name + "\n"
                + "def is :" + this.definition + "\n"
                + "exactSyno is :" + this.exactSyno + "\n"
                + "relatedSyno is :" + this.relatedSyno + "\n";
               // + "subClass is :" + subClass.getLabel();
    }

    public static void loadOnt() {
        if(genoMap==null){
        loadModel();
        buildModel();
        }
        else
            JOptionPane.showMessageDialog(null,"Geno Ontology already loaded");

    }

    

    public String getRelatedSyno() {
        return relatedSyno;
    }

    public void setRelatedSyno(String relatedSyno) {
        this.relatedSyno = relatedSyno;
    }

    public String getExactSyno() {
        return exactSyno;
    }

    public void setExactSyno(String exactSyno) {
        this.exactSyno = exactSyno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getLabel() {
        return label;
    }

    public String getDefinition() {
        return definition;
    }

    public static void loadModel() {
        owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        try {
            String fileName = "D:\\owl-ontology\\go.owl";
            InputStream in = FileManager.get().open(fileName);

            owlModel.read(in, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("load model done");
    }

    public static void buildModel() {
        if (genoMap == null) {
            genoMap = new HashMap();
        }
        String def;
        String exactSyno;
        String relatedSyno;
       
        if (owlModel != null) {
            Iterator classes = owlModel.listClasses();
            while (classes.hasNext()) {
                def = "";
                exactSyno = "";
                relatedSyno = "";

                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                String className = cls.getLocalName();

                

                Iterator prpos = cls.listDeclaredProperties();
                while (prpos.hasNext()) {
                    OntProperty onePro = (OntProperty) prpos.next();
                    if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                        //System.out.println("attrib is " + onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                            def = cls.getPropertyValue(onePro).toString();
                            String[] defArr = def.split("http://www.w3.org/2001/XMLSchema#string");
                            def = defArr[0];
                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                        //System.out.println("attrib is " + onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                            exactSyno = cls.getPropertyValue(onePro).toString();
                            String[] exactSynoArr = exactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                            exactSyno = exactSynoArr[0];
                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                        //System.out.println("attrib is " + onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                            relatedSyno = cls.getPropertyValue(onePro).toString();
                            String[] relatedSynoArr = relatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                            relatedSyno = relatedSynoArr[0];
                        }
                    }
                }
                GenoEncapsulate genoObj = new GenoEncapsulate();
                genoObj.setLabel(classLabel);
                genoObj.setName(className);
                genoObj.setDefinition(def);
                genoObj.setExactSyno(exactSyno);
                genoObj.setRelatedSyno(relatedSyno);
                genoObj.setSubClasses(getSubClasses(cls));
                genoMap.put(classLabel, genoObj);
            }
        } else {
            System.out.println("model is null");
        }

        System.out.println("geno map size is " + genoMap.size());
    }

    private static Vector getSubClasses(OntClass mainClass) {

        Vector subClasses = new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);

        for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext();) {
            OntClass currentSubClass = i.next();
            String subClassLabel = currentSubClass.getLabel(null);
            String subClassName = currentSubClass.getLocalName();
            String subClassdef = "";
            String subClassExactSyno = "";
            String subClassRelatedSyno = "";
            
            Iterator prpos = currentSubClass.listDeclaredProperties();
            
            while (prpos.hasNext()) {
            OntProperty onePro = (OntProperty) prpos.next();
            if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                //System.out.println("attrib is " + onePro.toString());
                if (currentSubClass.getPropertyValue(onePro) != null) {
                    //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                    subClassdef = currentSubClass.getPropertyValue(onePro).toString();
                    String[] defArr = subClassdef.split("http://www.w3.org/2001/XMLSchema#string");
                    subClassdef = defArr[0];
                }
            }
            if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                // System.out.println("attrib is " + onePro.toString());
                if (currentSubClass.getPropertyValue(onePro) != null) {
                    // System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                    subClassExactSyno = currentSubClass.getPropertyValue(onePro).toString();
                    String[] exactSynoArr = subClassExactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                    subClassExactSyno = exactSynoArr[0];
                }
            }
            if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                //System.out.println("attrib is " + onePro.toString());
                if (currentSubClass.getPropertyValue(onePro) != null) {
                    //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                    subClassRelatedSyno = currentSubClass.getPropertyValue(onePro).toString();
                    String[] relatedSynoArr = subClassRelatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                    subClassRelatedSyno = relatedSynoArr[0];
                }
            }
        }

        GenoEncapsulate subClassObj = new GenoEncapsulate();
        subClassObj.setLabel(subClassLabel);
        subClassObj.setName(subClassName);
        subClassObj.setDefinition(subClassdef);
        subClassObj.setExactSyno(subClassExactSyno);
        subClassObj.setRelatedSyno(subClassRelatedSyno);

        subClasses.add(subClassObj);
    }
        return subClasses;
    }
    
    
    private static Vector getSuperClasses(OntClass mainClass) {

        Vector superClasses = new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);

        for (Iterator<OntClass> i = artefact.listSuperClasses(); i.hasNext();) {
            OntClass currentSuperClass = i.next();
            String superClassLabel = currentSuperClass.getLabel(null);
            String superClassName = currentSuperClass.getLocalName();
            String superClassdef = "";
            String superClassExactSyno = "";
            String superClassRelatedSyno = "";
            
            Iterator prpos = currentSuperClass.listDeclaredProperties();
            
            while (prpos.hasNext()) {
            OntProperty onePro = (OntProperty) prpos.next();
            if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                //System.out.println("attrib is " + onePro.toString());
                if (currentSuperClass.getPropertyValue(onePro) != null) {
                    //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                    superClassdef = currentSuperClass.getPropertyValue(onePro).toString();
                    String[] defArr = superClassdef.split("http://www.w3.org/2001/XMLSchema#string");
                    superClassdef = defArr[0];
                }
            }
            if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                // System.out.println("attrib is " + onePro.toString());
                if (currentSuperClass.getPropertyValue(onePro) != null) {
                    // System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                    superClassExactSyno = currentSuperClass.getPropertyValue(onePro).toString();
                    String[] exactSynoArr = superClassExactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                    superClassExactSyno = exactSynoArr[0];
                }
            }
            if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                //System.out.println("attrib is " + onePro.toString());
                if (currentSuperClass.getPropertyValue(onePro) != null) {
                    //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                    superClassRelatedSyno = currentSuperClass.getPropertyValue(onePro).toString();
                    String[] relatedSynoArr = superClassRelatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                    superClassRelatedSyno = relatedSynoArr[0];
                }
            }
        }

        GenoEncapsulate superClassObj = new GenoEncapsulate();
        superClassObj.setLabel(superClassLabel);
        superClassObj.setName(superClassName);
        superClassObj.setDefinition(superClassdef);
        superClassObj.setExactSyno(superClassExactSyno);
        superClassObj.setRelatedSyno(superClassRelatedSyno);

        superClasses.add(superClassObj);
    }
        return superClasses;
    }

    public static void main(String[] args) {
        loadModel();
        buildModel();
        System.out.println("done");
    }

}
