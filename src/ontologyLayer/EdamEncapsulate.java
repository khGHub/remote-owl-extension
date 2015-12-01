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
public class EdamEncapsulate {
    
    
    private String label;
    private String name;
    private String definition;
    private String exactSyno;
    private Vector subClasses;
    private Vector superClasses; 
    static OntModel owlModel;
    static HashMap edamMap = null;


    public static void loadOnt() {
        if (edamMap == null) {
            loadModel();
            buildModel();
        } else {
            JOptionPane.showMessageDialog(null, "Edam Ontology already loaded");
        }
    }

    public static String phraseMeanings(String phrseWords) {
        String returnVal = Constants.NO_VALUE;
        if (edamMap != null) {
            if (edamMap.keySet().contains(phrseWords)) {
                EdamEncapsulate termObj;
                termObj = (EdamEncapsulate) edamMap.get(phrseWords);
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
                +" relatedSyno is :" + this.exactSyno + "\n";
              //  + "subClass is :" + subClass.getLabel();
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
            String fileName = "D:\\owl-ontology\\EDAM.owl";
            InputStream in = FileManager.get().open(fileName);

            owlModel.read(in, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildModel() {
        String def;
        String exactSyno;
        String relatedSyno;
        
        if (edamMap == null) {
            edamMap = new HashMap();
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
                if ((cls.getLabel(null) == null) && (cls.getLocalName() == null)) {
                    continue;
                }
                
                Iterator prpos = cls.listDeclaredProperties();
                while (prpos.hasNext()) {

                    OntProperty onePro = (OntProperty) prpos.next();
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasDefinition")) {
                        //System.out.println("attrib is " + onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                            def = cls.getPropertyValue(onePro).toString();

                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                        //System.out.println("attrib is " + onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                            exactSyno = cls.getPropertyValue(onePro).toString();

                        }
                    }
                }
                EdamEncapsulate edamObj = new EdamEncapsulate();
                edamObj.setLabel(classLabel);
                edamObj.setName(className);
                edamObj.setDefinition(def);
                edamObj.setExactSyno(exactSyno);
                edamObj.setSubClasses(getSubClasses(cls));
                edamObj.setSuperClasses(getSuperClasses(cls));
                //edamObj.setSubClass(sonClass);
                edamMap.put(classLabel, edamObj);
                //System.out.println("\n");
                //System.out.println("\n");
                //System.out.println("\n");
            }
        } else {
            System.out.println("model is null");
        }

        System.out.println("edam map size is " + edamMap.size());
    }

    private static Vector getSubClasses(OntClass mainClass) {

        Vector subClasses = new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);
        //get list of sub classes 
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
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasDefinition")) {
                    //System.out.println("attrib is " + onePro.toString());
                    if (currentSubClass.getPropertyValue(onePro) != null) {
                        //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                        subClassdef = currentSubClass.getPropertyValue(onePro).toString();

                    }
                }
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                    //System.out.println("attrib is " + onePro.toString());
                    if (currentSubClass.getPropertyValue(onePro) != null) {
                        //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                        subClassExactSyno = currentSubClass.getPropertyValue(onePro).toString();

                    }
                }

            }

            EdamEncapsulate subClassObj = new EdamEncapsulate();
            subClassObj.setLabel(subClassLabel);
            subClassObj.setName(subClassName);
            subClassObj.setDefinition(subClassdef);
            subClassObj.setExactSyno(subClassExactSyno);

            subClasses.add(subClassObj);
        }

        return subClasses;
    }

    
    private static Vector getSuperClasses(OntClass mainClass) {

        Vector superClasses = new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);
        //get list of sub classes 
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
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasDefinition")) {
                    //System.out.println("attrib is " + onePro.toString());
                    if (currentSuperClass.getPropertyValue(onePro) != null) {
                        //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                        superClassdef = currentSuperClass.getPropertyValue(onePro).toString();

                    }
                }
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                    //System.out.println("attrib is " + onePro.toString());
                    if (currentSuperClass.getPropertyValue(onePro) != null) {
                        //System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
                        superClassExactSyno = currentSuperClass.getPropertyValue(onePro).toString();

                    }
                }

            }

            EdamEncapsulate superClassObj = new EdamEncapsulate();
            superClassObj.setLabel(superClassLabel);
            superClassObj.setName(superClassName);
            superClassObj.setDefinition(superClassdef);
            superClassObj.setExactSyno(superClassExactSyno);

            superClasses.add(superClassObj);
        }

        return superClasses;
    }

    public static void main(String[] args) {
        loadModel();
        buildModel();
        System.out.println("map size is  " + edamMap.size());
        System.out.println("done");
    }

}
