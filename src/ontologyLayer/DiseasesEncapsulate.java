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
import utils.Constants;

/**
 *
 * @author A
 */
public class DiseasesEncapsulate {
    
    private String label;
    private String name;
    private String definition;
    private String exactSyno;
    private String relatedSyno;
    private Vector subClasses;
    private Vector superClasses; 

   

    static OntModel owlModel;
    static HashMap diseasesMap = null;

    public static String phraseMeanings(String phrseWords) {
        String returnVal = Constants.NO_VALUE;
        if (diseasesMap != null) {
            //1- the phrase is the same of the class lable
            if (diseasesMap.keySet().contains(phrseWords)) {
                DiseasesEncapsulate termObj;
                termObj = (DiseasesEncapsulate) diseasesMap.get(phrseWords);
                returnVal = termObj.toString();
                return returnVal;
            }
            
            //2- the phrase is part of class label
            Iterator iter=diseasesMap.keySet().iterator();
            while(iter.hasNext()){
                String currentClassLabel=(String) iter.next();
                if(currentClassLabel.contains(phrseWords)){
                    DiseasesEncapsulate termObj;
                    termObj = (DiseasesEncapsulate) diseasesMap.get(currentClassLabel);
                    returnVal = termObj.toString();
                    return returnVal;
                }
            }
        }
        return returnVal;
    }

    private static void printModel() {
        Iterator iter=diseasesMap.keySet().iterator();
        while(iter.hasNext()){
         DiseasesEncapsulate dis=(DiseasesEncapsulate)   diseasesMap.get(iter.next());
         System.out.println(dis);
         System.out.println("\n");
        }
    }

    

    public Vector getSuperClasses() {
        return superClasses;
    }

    public void setSuperClasses(Vector superClasses) {
        this.superClasses = superClasses;
    }
    
    public Vector  getSubClasses() {
        return subClasses;
    }
    
    public void setSubClasses(Vector subClasses) {
        this.subClasses = subClasses;
    }
    

    public static void loadOnt() {
        if (diseasesMap == null) {
            loadModel();
            buildModel();
        }else
            JOptionPane.showMessageDialog(null,"Disease Ontology already loaded");

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
            String fileName = "D:\\owl-ontology\\doid.owl";
            InputStream in = FileManager.get().open(fileName);

            owlModel.read(in, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void buildModel() {
        if (diseasesMap == null) {
            diseasesMap = new HashMap();
        }

        String def;
        String exactSyno;
        String relatedSyno;
        DiseasesEncapsulate sonClass = null;
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
                        //System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                            def = cls.getPropertyValue(onePro).toString();
                            String[] defArr = def.split("http://www.w3.org/2001/XMLSchema#string");
                            def = defArr[0];
                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                        //System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                            exactSyno = cls.getPropertyValue(onePro).toString();
                            String[] exactSynoArr = exactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                            exactSyno = exactSynoArr[0];
                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                        //System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                            relatedSyno = cls.getPropertyValue(onePro).toString();
                            String[] relatedSynoArr = relatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                            relatedSyno = relatedSynoArr[0];
                        }
                    }
                }
                DiseasesEncapsulate disObj = new DiseasesEncapsulate();
                disObj.setLabel(classLabel);
                disObj.setName(className);
                disObj.setDefinition(def);
                disObj.setExactSyno(exactSyno);
                disObj.setRelatedSyno(relatedSyno);
                disObj.setSubClasses(getSubClasses(cls));
                disObj.setSuperClasses(getSuperClasses(cls));
                diseasesMap.put(classLabel, disObj);
            }
        } else {
            System.out.println("model is null");
        }

        System.out.println("disease map size is " + diseasesMap.size());
    }

    public static String termMeaning(String oneToken) {
        String returnVal = Constants.NO_VALUE;
        if (diseasesMap.keySet().contains(oneToken)) {
            DiseasesEncapsulate termObj = (DiseasesEncapsulate) diseasesMap.get(oneToken);
            returnVal = termObj.toString();
        }
        return returnVal;
    }

    public static String phrseMeaning(String phraseWords) {
        String returnVal = Constants.NO_VALUE;
        if (diseasesMap.keySet().contains(phraseWords)) {
            DiseasesEncapsulate termObj = (DiseasesEncapsulate) diseasesMap.get(phraseWords);
            returnVal = termObj.toString();
        }
        return returnVal;
    }

    @Override
    public String toString() {
//        return    "label is :" + this.label + "\n"
//                + "name is :" + this.name + "\n"
//                + "def is :" + this.definition + "\n"
//                + "exactSyno is :" + this.exactSyno + "\n"
//                + "relatedSyno is :" + this.relatedSyno + "\n"
//                + "subClass is :" + subClass.getLabel();
        return "";
    }

    private static Vector getSubClasses(OntClass mainClass) {
        Vector subClasses=new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);
        //get list of sub classes 
        for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext();) {
            OntClass currentSubClass = i.next();
            //System.err.println(currentSubClass.getLabel(null));
            //JOptionPane.showMessageDialog(null, "sub class  lable is:" + currentSubClass.getLabel(null));

            String subClassLabel = currentSubClass.getLabel(null);
            String subClassName = currentSubClass.getLocalName();
            String subClassdef = "";
            String subClassExactSyno = "";
            String subClassRelatedSyno = "";

            Iterator prpos = currentSubClass.listDeclaredProperties();
            while (prpos.hasNext()) {
                OntProperty onePro = (OntProperty) prpos.next();
                if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                    //System.out.println("attrib is "+onePro.toString());
                    if (currentSubClass.getPropertyValue(onePro) != null) {
                        //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                        subClassdef = currentSubClass.getPropertyValue(onePro).toString();
                        String[] defArr = subClassdef.split("http://www.w3.org/2001/XMLSchema#string");
                        subClassdef = defArr[0];
                    }
                }
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                    //System.out.println("attrib is "+onePro.toString());
                    if (currentSubClass.getPropertyValue(onePro) != null) {
                        // System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                        subClassExactSyno = currentSubClass.getPropertyValue(onePro).toString();
                        String[] defArr = subClassExactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                        subClassExactSyno = defArr[0];
                    }
                }
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                    // System.out.println("attrib is "+onePro.toString());
                    if (currentSubClass.getPropertyValue(onePro) != null) {
                        // System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                        subClassRelatedSyno = currentSubClass.getPropertyValue(onePro).toString();
                        String[] defArr = subClassRelatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                        subClassRelatedSyno = defArr[0];
                    }
                }
            }

            DiseasesEncapsulate subClassObj = new DiseasesEncapsulate();
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
        Vector superClasses=new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);
        //get list of sub classes 
        for (Iterator<OntClass> i = artefact.listSuperClasses(); i.hasNext();) {
            OntClass currentSuperClass = i.next();
            //System.err.println(currentSuperClass.getLabel(null));
            //JOptionPane.showMessageDialog(null, "sub class  lable is:" + currentSuperClass.getLabel(null));

            String superClassLabel = currentSuperClass.getLabel(null);
            String superClassName = currentSuperClass.getLocalName();
            String superClassdef = "";
            String superClassExactSyno = "";
            String superClassRelatedSyno = "";

            Iterator prpos = currentSuperClass.listDeclaredProperties();
            while (prpos.hasNext()) {
                OntProperty onePro = (OntProperty) prpos.next();
                if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                    //System.out.println("attrib is "+onePro.toString());
                    if (currentSuperClass.getPropertyValue(onePro) != null) {
                        //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                        superClassdef = currentSuperClass.getPropertyValue(onePro).toString();
                        String[] defArr = superClassdef.split("http://www.w3.org/2001/XMLSchema#string");
                        superClassdef = defArr[0];
                    }
                }
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                    //System.out.println("attrib is "+onePro.toString());
                    if (currentSuperClass.getPropertyValue(onePro) != null) {
                        // System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                        superClassExactSyno = currentSuperClass.getPropertyValue(onePro).toString();
                        String[] defArr = superClassExactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                        superClassExactSyno = defArr[0];
                    }
                }
                if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                    // System.out.println("attrib is "+onePro.toString());
                    if (currentSuperClass.getPropertyValue(onePro) != null) {
                        // System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                        superClassRelatedSyno = currentSuperClass.getPropertyValue(onePro).toString();
                        String[] defArr = superClassRelatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                        superClassRelatedSyno = defArr[0];
                    }
                }
            }

            DiseasesEncapsulate superClassObj = new DiseasesEncapsulate();
            superClassObj.setLabel(superClassLabel);
            superClassObj.setName(superClassName);
            superClassObj.setDefinition(superClassdef);
            superClassObj.setExactSyno(superClassExactSyno);
            superClassObj.setRelatedSyno(superClassRelatedSyno);
            
            superClasses.add(superClassObj);
        }

        return superClasses;
    }


    
    public static void testbuildModel() {
        if (diseasesMap == null) {
            diseasesMap = new HashMap();
        }

        String def;
        String exactSyno;
        String relatedSyno;
        DiseasesEncapsulate sonClass = null;
        if (owlModel != null) {
            Iterator classes = owlModel.listClasses();
            ////////////////////////////////////////////
//            JOptionPane.showMessageDialog(null,"begin test");
//            OntClass artefact = owlModel.getOntClass("http://purl.obolibrary.org/obo/DOID_0050339");
//            for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext(); ) {
//                    OntClass c = i.next();
//                    System.err.println(c.getLabel(null));
//            }
//            JOptionPane.showMessageDialog(null,"end test");
            ////////////////////////////////////////////
         
            while (classes.hasNext()) {
                def = "";
                exactSyno = "";
                relatedSyno = "";

                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                String className = cls.getLocalName();
                String classUri =cls.getURI();
                if (classLabel.equalsIgnoreCase("commensal bacterial infectious disease")) {
                    JOptionPane.showMessageDialog(null, "ok lable is  " + classLabel);
                    JOptionPane.showMessageDialog(null, "ok uri is  " +cls.getURI());
                    
                    ///////////
                        OntClass artefact = owlModel.getOntClass(classUri);
                     
                        for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext(); ) {
                        OntClass c = i.next();
                        System.err.println(c.getLabel(null));
                        JOptionPane.showMessageDialog(null, "sub class  lable is:"+c.getLabel(null));
                        }
                        for (Iterator<OntClass> i = artefact.listSuperClasses(); i.hasNext(); ) {
                        OntClass c = i.next();
                        System.err.println(c.getLabel(null));
                        JOptionPane.showMessageDialog(null, "super class  lable is:"+c.getLabel(null));
                        }
                    //////////
                    if (cls.getSubClass() != null) {
                        JOptionPane.showMessageDialog(null, "sub class  lable is  " + cls.getSubClass().getLabel(null));
                    }
                    if (cls.getSuperClass() != null) {
                        JOptionPane.showMessageDialog(null, "super class  lable is  " + cls.getSuperClass().getLabel(null));
                    }
                    if (cls.getSameAs() != null) {
                        JOptionPane.showMessageDialog(null, " class same as is  " + cls.getSameAs().toString());
                    }else
                        JOptionPane.showMessageDialog(null, " class same  is null ");

                    
                }
//                if (cls.getSubClass() != null) {
//                        sonClass = getSubClass(cls.getSubClass());
//                    }

                Iterator prpos = cls.listDeclaredProperties();
                while (prpos.hasNext()) {
                    OntProperty onePro = (OntProperty) prpos.next();
                    
                    if (onePro.toString().equalsIgnoreCase("http://purl.obolibrary.org/obo/IAO_0000115")) {
                        //System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                            def = cls.getPropertyValue(onePro).toString();
                            String[] defArr = def.split("http://www.w3.org/2001/XMLSchema#string");
                            def = defArr[0];
                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym")) {
                        //System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                            exactSyno = cls.getPropertyValue(onePro).toString();
                            String[] exactSynoArr = exactSyno.split("http://www.w3.org/2001/XMLSchema#string");
                            exactSyno = exactSynoArr[0];
                        }
                    }
                    if (onePro.toString().equalsIgnoreCase("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym")) {
                        //System.out.println("attrib is "+onePro.toString());
                        if (cls.getPropertyValue(onePro) != null) {
                            //System.out.println("def is  "+cls.getPropertyValue(onePro).toString());
                            relatedSyno = cls.getPropertyValue(onePro).toString();
                            String[] relatedSynoArr = relatedSyno.split("http://www.w3.org/2001/XMLSchema#string");
                            relatedSyno = relatedSynoArr[0];
                        }
                    }
                }
                DiseasesEncapsulate disObj = new DiseasesEncapsulate();
                disObj.setLabel(classLabel);
                disObj.setName(className);
                disObj.setDefinition(def);
                disObj.setExactSyno(exactSyno);
                disObj.setRelatedSyno(relatedSyno);
             //   disObj.setSubClass(sonClass);
                diseasesMap.put(classLabel, disObj);
            }
        } else {
            System.out.println("model is null");
        }

        System.out.println("disease map size is " + diseasesMap.size());
    }


    public static void main(String[] args) {
        loadModel();
        buildModel();
//        printModel();
//        String mean=phraseMeanings("neoplasia");
//        System.out.println("done " +mean);
//        loadModel();
 //       testbuildModel();
      
    }
    
    
    
    
    

}
