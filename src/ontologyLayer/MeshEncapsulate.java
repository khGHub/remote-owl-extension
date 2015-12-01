/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologyLayer;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import static ontologyLayer.AnatomyEncapsulate.owlModel;
import utils.Constants;

public class MeshEncapsulate {
    
    private String label;
    private String name;
    private String definition;
    private String exactSyno;
    private String relatedSyno;
    private Vector subClasses;
    private Vector superClasses;

    static HashMap meshMap=null;
    static OntModel owlModel;
   

    public static String phraseMeanings(String phrseWords) {
        String returnVal = Constants.NO_VALUE;
        if (meshMap != null) {
            if (meshMap.keySet().contains(phrseWords)) {
                MeshEncapsulate termObj;
                termObj = (MeshEncapsulate) meshMap.get(phrseWords);
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
               // + "subClass is :" + subClass.getLabel();
    }
    
    public static void loadOnt() {
        if(meshMap==null)
        loadModel();
        else
            JOptionPane.showMessageDialog(null,"Mesh Ontology already loaded");

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
        if (meshMap == null) {
            meshMap = new HashMap();
        }

        owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        String fileName = "D:\\owl-ontology\\mesh.owl";
        InputStream in = FileManager.get().open(fileName);
        try {
            owlModel.read(in, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExtendedIterator<OntClass> iter = owlModel.listClasses();
        while (iter.hasNext()) {
            OntClass cls = (OntClass) iter.next();
            String classLabel = cls.getLabel(null);
            String className = cls.getLocalName();
            //System.out.println("classLabel  is " + classLabel + "  className is " + className);

           

//            Iterator prpos = cls.listDeclaredProperties();
//            while (prpos.hasNext()) {
//                OntProperty onePro = (OntProperty) prpos.next();
//                System.out.println("attrib is " + onePro.toString());
//                if (cls.getPropertyValue(onePro) != null) {
//                    System.out.println("def is  " + cls.getPropertyValue(onePro).toString());
//                    //def = cls.getPropertyValue(onePro).toString();
//                    //String[] defArr = def.split("http://www.w3.org/2001/XMLSchema#string");
//                    //def = defArr[0];
//                }
//            }
//            Iterator prpos2 = cls.listProperties();
//            while (prpos2.hasNext()) {
//                Statement onePro = (Statement) prpos2.next();
//                System.out.println("attrib is " + onePro.toString());
//                
//            }
            MeshEncapsulate meshObj = new MeshEncapsulate();
            meshObj.setLabel(classLabel);
            meshObj.setName(className);
            //meshObj.setSubClass(sonClass);
            meshMap.put(className, meshObj);

        }

        System.out.println("mesh model load done " + meshMap.size());
    }

    private static Vector getSubClasses(OntClass mainClass) {

        Vector subClasses = new Vector();
        String mainClassUri = mainClass.getURI();

        OntClass artefact = owlModel.getOntClass(mainClassUri);

        for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext();) {
            OntClass currentSubClass = i.next();
            String subClassLabel = currentSubClass.getLabel(null);
            String subClassName = currentSubClass.getLocalName();

            MeshEncapsulate subClassObj = new MeshEncapsulate();
            subClassObj.setLabel(subClassLabel);
            subClassObj.setName(subClassName);

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

            MeshEncapsulate superClassObj = new MeshEncapsulate();
            superClassObj.setLabel(superClassLabel);
            superClassObj.setName(superClassName);

            superClasses.add(superClassObj);
        }

        return superClasses;
    }

    public static void main(String[] args) {
        loadModel();
        System.out.println("done");
    }

}
