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
import static ontologyLayer.DiseasesEncapsulate.owlModel;
import utils.Constants;

/**
 *
 * @author A
 */
public class AnatomyEncapsulate {
    
    private String label;
    private String name;
    private String definition;
    private String exactSyno;
    private String relatedSyno;
    
    private Vector subClasses;
    private Vector superClasses; 

    static OntModel owlModel;
    static HashMap anatomyMap=null;

    public static void loadOnt() {
        if (anatomyMap == null) {
            loadModel();
            buildModel();
        }else
            JOptionPane.showMessageDialog(null,"Anatomy Ontology already loaded");
    }

    public static String phraseMeanings(String phrseWords) {
        String returnVal = Constants.NO_VALUE;
        if (anatomyMap != null) {
            if (anatomyMap.keySet().contains(phrseWords)) {
                AnatomyEncapsulate termObj;
                termObj = (AnatomyEncapsulate) anatomyMap.get(phrseWords);
                returnVal = termObj.toString();
            }
        }

        return returnVal;
    }

    

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
   

    
    @Override
    public String toString() {
        return    "label is :" + this.label + "\n"
                + "name is :" + this.name + "\n"
                + "def is :" + this.definition + "\n"
                + "exactSyno is :" + this.exactSyno + "\n"
                + "relatedSyno is :" + this.relatedSyno + "\n";
              //  + "subClass is :" + subClass.getLabel();
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
            String fileName = "D:\\owl-ontology\\fma_3.2_owl_file\\fma3.2.owl";
            InputStream in = FileManager.get().open(fileName);

            owlModel.read(in, "OWL/XML");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildModel(){
        String def;
        String exactSyno;
        String relatedSyno;
        AnatomyEncapsulate sonClass = null;
        if (anatomyMap == null) {
            anatomyMap = new HashMap();
        }
        if (owlModel != null) {
            Iterator classes = owlModel.listClasses();
            while (classes.hasNext()) {
                def = "";
                exactSyno = "";
                relatedSyno = "";

                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                String className = cls.getLocalName();
                //System.out.println(cls.toString());

//                if (cls.getSubClass() != null) {
//                    sonClass = getSubClass(cls.getSubClass());
//                }
                Iterator prpos = cls.listDeclaredProperties();
                while (prpos.hasNext()) {
                    String attr_val = "no_value";
                    OntProperty onePro = (OntProperty) prpos.next();

                    //System.out.println("attrib is " + onePro.toString());
                    if (cls.getPropertyValue(onePro) != null) {
                        attr_val = cls.getPropertyValue(onePro).toString();
                    }
                       // System.out.println("attrbi:" +onePro.toString()+" value :"+attr_val);
                    //if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
//                        //System.out.println("attrib is " + onePro.toString());
//                        if (cls.getPropertyValue(onePro) != null) {
//                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
//                            def = cls.getPropertyValue(onePro).toString();
//                            String[] defArr = def.split("http://www.w3.org/2001/XMLSchema#string");
//                            def = defArr[0];
//                        }
//                    }
//                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
//                        //System.out.println("attrib is " + onePro.toString());
//                        if (cls.getPropertyValue(onePro) != null) {
//                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
//                            exactSyno = cls.getPropertyValue(onePro).toString();
//                            String[] exactSynoArr = exactSyno.split("http://www.w3.org/2001/XMLSchema#string");
//                            exactSyno = exactSynoArr[0];
//                        }
//                    }
//                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
//                        //System.out.println("attrib is " + onePro.toString());
//                        if (cls.getPropertyValue(onePro) != null) {
//                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
//                            relatedSyno = cls.getPropertyValue(onePro).toString();
//                            String[] relatedSynoArr = relatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
//                            relatedSyno = relatedSynoArr[0];
//                        }
//                    }
                }
                AnatomyEncapsulate anatomyObj = new AnatomyEncapsulate();
                anatomyObj.setLabel(classLabel);
                anatomyObj.setName(className);
                anatomyObj.setDefinition(def);
                anatomyObj.setExactSyno(exactSyno);
                anatomyObj.setRelatedSyno(relatedSyno);
                anatomyObj.setSubClasses(getSubClasses(cls));
                anatomyObj.setSuperClasses(getSuperClasses(cls));
                anatomyMap.put(classLabel, anatomyObj);
            }
        } else {
            System.out.println("model is null");
        }

        System.out.println("anatomy map size is " + anatomyMap.size());
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

            AnatomyEncapsulate subClassObj = new AnatomyEncapsulate();
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

            AnatomyEncapsulate superClassObj = new AnatomyEncapsulate();
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
        System.out.println("map size is     " + anatomyMap.size());
        System.out.println("done");
    }

}
