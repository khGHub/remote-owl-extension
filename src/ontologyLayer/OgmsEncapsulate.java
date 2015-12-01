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
import static ontologyLayer.MeshEncapsulate.owlModel;
import utils.Constants;

/**
 *
 * @author A
 */
public class OgmsEncapsulate {
    
    private String label;
    private String name;
    private String definition;
    private String exactSyno;
    private String relatedSyno;
    private Vector subClasses;
    private Vector superClasses;

    static OntModel owlModel;
    static HashMap ogmsMap=null;

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
    

    public static void loadOnt() {
        if(ogmsMap==null){
        loadModel();
        buildModel();
        }
        else
            JOptionPane.showMessageDialog(null,"OGMS ontology already loaded");
            

    }

    public static String phraseMeanings(String phrseWords) {
        String returnVal = Constants.NO_VALUE;
        if (ogmsMap != null) {
            if (ogmsMap.keySet().contains(phrseWords)) {
                OgmsEncapsulate termObj;
                termObj = (OgmsEncapsulate) ogmsMap.get(phrseWords);
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
             //   + "subClass is :" + subClass.getLabel();
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
            String fileName = "D:\\owl-ontology\\ogms.owl";
            InputStream in = FileManager.get().open(fileName);

            owlModel.read(in, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildModel() {
        if (ogmsMap == null) {
            ogmsMap = new HashMap();
        }
        String def;
       
        if (owlModel != null) {
            Iterator classes = owlModel.listClasses();
            while (classes.hasNext()) {
                def = "";
                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                String className = cls.getLocalName();

               

                Iterator prpos = cls.listDeclaredProperties();
                while (prpos.hasNext()) {
                    OntProperty onePro = (OntProperty) prpos.next();
                    if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                        // System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            def = cls.getPropertyValue(onePro).toString();
                        }
                    }

                }
                OgmsEncapsulate ogmsObj = new OgmsEncapsulate();
                ogmsObj.setLabel(classLabel);
                ogmsObj.setName(className);
                ogmsObj.setDefinition(def);
                ogmsObj.setSubClasses(getSubClasses(cls));
                ogmsObj.setSuperClasses(getSuperClasses(cls));
                ogmsMap.put(classLabel, ogmsObj);
            }
        } else {
            System.out.println("model is null");
        }

        System.out.println("ogms map size is " + ogmsMap.size());
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

            Iterator prpos = currentSubClass.listDeclaredProperties();
            while (prpos.hasNext()) {
                OntProperty onePro = (OntProperty) prpos.next();
                if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                    //System.out.println("attrib is "+onePro.toString());
                    if (currentSubClass.getPropertyValue(onePro) != null) {
                        subClassdef = currentSubClass.getPropertyValue(onePro).toString();
                    }
                }
            }
            OgmsEncapsulate subClassObj = new OgmsEncapsulate();
            subClassObj.setLabel(subClassLabel);
            subClassObj.setName(subClassName);
            subClassObj.setDefinition(subClassdef);

            subClasses.add(subClassObj);

        }
        return subClasses;
    }
    
    private static Vector getSuperClasses(OntClass mainClass){

        Vector superClasses = new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);

        for (Iterator<OntClass> i = artefact.listSuperClasses(); i.hasNext();) {
            OntClass currentSuperClass = i.next();
            String superClassLabel = currentSuperClass.getLabel(null);
            String superClassName = currentSuperClass.getLocalName();
            String superClassdef = "";

            Iterator prpos = currentSuperClass.listDeclaredProperties();
            while (prpos.hasNext()) {
                OntProperty onePro = (OntProperty) prpos.next();
                if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                    //System.out.println("attrib is "+onePro.toString());
                    if (currentSuperClass.getPropertyValue(onePro) != null) {
                        superClassdef = currentSuperClass.getPropertyValue(onePro).toString();
                    }
                }
            }
            OgmsEncapsulate superClassObj = new OgmsEncapsulate();
            superClassObj.setLabel(superClassLabel);
            superClassObj.setName(superClassName);
            superClassObj.setDefinition(superClassdef);

            superClasses.add(superClassObj);

        }
        return superClasses;
    }


    public static void main(String[] args) {

        buildModel();

        System.out.println("done");
    }

}
