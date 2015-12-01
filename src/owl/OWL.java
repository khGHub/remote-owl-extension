/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.vocabulary.XSD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import static owl.Bloggers.inputFileName;

/**
 *
 * @author A
 */
public class OWL {

    /**
     * @param args the command line arguments
     */
    static String personURI = "http://somewhere/JohnSmith";
    static String fullName = "John Smith";

    static String fileName = "E:\\OWL\\src\\owl\\medicalVillageOntology.owl";
    static String fileName2 = "E:\\phd-project-tools\\Medical-Ontology\\go.owl";
    static String fileName3 = "E:\\phd-project-tools\\Medical-Ontology\\khaled\\mesh.owl";

    public static void main(String[] args) {
testtt();
        //getOntologyModel(fileName3);
        //loadModel(fileName2);
        //saveOntology2Txt(fileName);
         //String finalpropStr="###########";
         //finalpropStr=finalpropStr.replaceAll("# *","o");
        
       // System.out.println(finalpropStr);
    }
    
    public static void testtt(){
         OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
         OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, null);

   // String NS = "http://www.abc.com/abcportal/abc.owl" + "#";


            InputStream in = FileManager.get().open(fileName3);
            try {
                base.read(in, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
    ExtendedIterator<OntClass> iter = base.listClasses();

    while ( iter.hasNext()){
        System.out.println(iter.next());
    }
    }
    
    public static OntModel getOntologyModel(String ontoFile) {
        OntModel owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        try {
            InputStream in = FileManager.get().open(ontoFile);
            try {
                owlModel.read(in, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String c2Name = "http://org.snu.bike/MeSH#aerobic_bacteria";
            OntClass cc = owlModel.getOntClass(c2Name);
            System.out.println("class name  is  " + cc.getLocalName() + "   " + cc.toString() + "\n");
            Iterator prpos = cc.listDeclaredProperties();
            Iterator iter5 = cc.listProperties();

            //cc.getProperty()
            // =cc.getProperty();
//                while(prpos.hasNext()){
//                    OntProperty onePro=(OntProperty) prpos.next();
//                    System.out.println(onePro.toString()+"  value is  "+ cc.getPropertyValue(onePro));
//                }
            while (iter5.hasNext()) {
                Statement onePro = (Statement) iter5.next();
                // RDFNode vv=cc.getPropertyValue((Property) onePro);
                String propStr = onePro.toString();
                String[] propStrArr = propStr.split(",");
                System.out.println(propStrArr[2]);
                System.out.println("\n");
            }

//            while (classes.hasNext()) {
//
//                OntClass cls = (OntClass) classes.next();
//                String classNameSp = cls.getNameSpace();
////                Iterator prpos=cls.listDeclaredProperties();
////                while(prpos.hasNext()){
////                    OntProperty onePro=(OntProperty) prpos.next();
////                    System.out.println(onePro.toString());
////                }
//                System.out.println("Class name space is " + classNameSp+"   "+cls.getLocalName()+"   "+cls.getURI());
//                System.out.println("\n");
//            }
            ///////////////////////////////////////
        } catch (JenaException je) {
            System.err.println("ERROR" + je.getMessage());
            je.printStackTrace();
            System.exit(0);
        }
        return owlModel;
    }

    public static void loadModel(String fileName) {
        OntModel owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        try {
            InputStream in = FileManager.get().open(fileName);
            try {
                owlModel.read(in, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int classSize = owlModel.listClasses().toList().size();
            System.out.println("gene size is " + classSize);
            Iterator classes = owlModel.listClasses();
            if (classes.hasNext()) {
                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                String className=cls.getLocalName();
                String classDef=getClassDefStr(cls);
                System.out.println("class  classLabel is  "+classLabel+"  className  "+className+"  classDef "+classDef);
            }
            if (classes.hasNext()) {
                 OntClass cls = (OntClass) classes.next();
                 System.out.println("class  is  "+cls.toString());
            }
            if (classes.hasNext()) {
                 OntClass cls = (OntClass) classes.next();
                 System.out.println("class  is  "+cls.toString());
            }
            if (classes.hasNext()) {
                 OntClass cls = (OntClass) classes.next();
                 System.out.println("class  is  "+cls.toString());
            }
            if (classes.hasNext()) {
                 OntClass cls = (OntClass) classes.next();
                 System.out.println("class  is  "+cls.toString());
            }
        } catch (JenaException je) {
            System.err.println("ERROR" + je.getMessage());
            je.printStackTrace();
            System.exit(0);
        }
    }

    public static OntModel getOntologyClassByName(String className) {
        
        try {
            OntModel owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
            InputStream in = FileManager.get().open(OWL.fileName);
            try {
                owlModel.read(in, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Iterator classes = owlModel.listClasses();
            while (classes.hasNext()) {
                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                if (classLabel != null && classLabel.contains(className)) {
                    System.out.println("class name  is  " + cls.getLocalName() + "\n");
                    System.out.println("class name space  is  " + cls.getNameSpace() + "\n");
                    System.out.println("class string is  " + cls.toString() + "\n");
                    System.out.println("begin properties");
                    getClassDef(cls);
                }
            }

        } catch (JenaException je) {
            System.err.println("ERROR" + je.getMessage());
            je.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static void saveOntology2Txt(String onFileName) {
        String  cons="MSH   disease_ontology  UMLS_CUI   OMIM   SNOMEDCT_2010_1_31   NCI  ICD9CM";
        try {
            File file = new File("E:\\phd-project-tools\\Medical-Ontology\\diseases.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            ////////////////////////////////////////////
            OntModel owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
            InputStream in = FileManager.get().open(onFileName);
            try {
                owlModel.read(in, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Iterator classes = owlModel.listClasses();
            while (classes.hasNext()) {
                OntClass cls = (OntClass) classes.next();
                String classLabel = cls.getLabel(null);
                if(classLabel!=null){
                String className=cls.getLocalName();
                
                if(className.trim().startsWith("DOID"))
                    className="";
                
                String classDef=getClassDefStr(cls);
                String classInfo=classLabel+"##"+className+"##"+classDef;
                classInfo=classInfo.replaceAll("##", "#");
                bw.write(classInfo); 
                bw.newLine();
                }
                 
            }

            ////////////////////////////////////////////
            
            
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getClassDef(OntClass cls) {
        String finalpropStr="";
        String wStr="";
        Iterator propIter = cls.listProperties();
        while (propIter.hasNext()) {
                Statement onePro = (Statement) propIter.next();
                String propStr = onePro.toString();
                
                String[] propStrArr = propStr.split(",");
                String newStr=propStrArr[2];
                String[] propStrArr2 = newStr.split("http://www.w3.org/2001/XMLSchema#string");
                if(propStrArr2.length > 1){
                    String temp=propStrArr2[0].trim();
                    if(! temp.contains("DOID")){
                    wStr=propStrArr2[0];
                    finalpropStr+=wStr+"##";
                    }
                }
                
            }
          System.out.println("final str is  "+finalpropStr.replace("^^",""));
         
        

    }
    
     public static String getClassDefStr(OntClass cls) {
        
        String finalpropStr="";
        String wStr="";
        Iterator propIter = cls.listProperties();
        while (propIter.hasNext()) {
                Statement onePro = (Statement) propIter.next();
                String propStr = onePro.toString();
                
                String[] propStrArr = propStr.split(",");
                String newStr=propStrArr[2];
                String[] propStrArr2 = newStr.split("http://www.w3.org/2001/XMLSchema#string");
                if(propStrArr2.length > 1){
                    String temp=propStrArr2[0].trim();
                    wStr=propStrArr2[0];
                    finalpropStr+=burnProp(wStr)+"#";
                    
                }
                
            }
          //System.out.println("final str is  "+finalpropStr.replace("^^",""));
         
         
         
        return finalpropStr.replace("^^","");
       
    }

    private static String burnProp(String wStr) {
        
        String resutlStr="";
        if(wStr.trim().contains("SNOMEDCT"))
                wStr="";
        if(wStr.trim().contains("DOID"))
                wStr="";
        if(wStr.trim().contains("disease_ontology"))
                wStr="";
        if(wStr.trim().contains("OMIM"))
                wStr="";
        if(wStr.trim().contains("UMLS_CUI"))
                wStr="";
        if(wStr.trim().contains("MSH"))
                wStr="";
        if(wStr.trim().contains("NCI"))
                wStr="";
        if(wStr.trim().contains("ICD9CM"))
                wStr="";
        
        resutlStr=wStr;
        return resutlStr;
    }

    public void test() {
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        Resource johnSmith = model.createResource(personURI);

        // add the property
        johnSmith.addProperty(VCARD.FN, fullName);

        // list the statements in the Model
        StmtIterator iter = model.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
        }

    }

    public static void queryMedicalOwl(String name) {
        // Create an empty in-memory model
        Model model = ModelFactory.createDefaultModel();
        // use the FileManager to open the bloggers RDF graph from the filesystem
        InputStream in = FileManager.get().open(fileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }
        // read the RDF/XML file
        model.read(in, "");

        // Create a new query
        String queryString = ""
                + "PREFIX doid: <http://purl.obolibrary.org/obo/doid#>"
                + "PREFIX obo: <http://purl.obolibrary.org/obo/>"
                + "PREFIX oboInOWL: <http://www.geneontology.org/formats/oboInOwl#>"
                + "PREFIX mv: <http://purl.obolibrary.org/obo/doid.owl#>"
                + "PREFIX rdfs: <" + RDFS.getURI() + ">"
                + "PREFIX owl: <" + com.hp.hpl.jena.vocabulary.OWL.getURI() + ">"
                + "PREFIX rdf: <" + RDF.getURI() + ">"
                + "PREFIX xsd: <" + XSD.getURI() + ">"
                + "SELECT distinct ?id ?label ?ar_label {"
                + "?d rdf:type %s."
                + "?d doid:has_symptom ?sym."
                + "?sym rdf:type ?id."
                + "FILTER NOT EXISTS {"
                + "?sub rdfs:subClassOf ?id."
                + "FILTER(?sub != ?id && ?sub != owl:Nothing)."
                + "}"
                + "?id rdfs:label ?label."
                + "optional { ?id mv:arabic_label ?ar_label.}";

        String queryString2 = ""
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                + "PREFIX doid: <http://purl.obolibrary.org/obo/doid#>"
                + "PREFIX obo: <http://purl.obolibrary.org/obo/>"
                + "PREFIX oboInOWL: <http://www.geneontology.org/formats/oboInOwl#>"
                + "PREFIX mv: <http://purl.obolibrary.org/obo/doid.owl#>"
                + "SELECT  ?concept  ?ar"
                + "WHERE {"
                + "?concept rdfs:subClassOf obo:DOID_4."
                + "?concept mv:arabic_label ?ar."
                + "}";

        String queryString3 = ""
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                + "PREFIX doid: <http://purl.obolibrary.org/obo/doid#>"
                + "PREFIX obo: <http://purl.obolibrary.org/obo/>"
                + "PREFIX oboInOWL: <http://www.geneontology.org/formats/oboInOwl#>"
                + "PREFIX mv: <http://purl.obolibrary.org/obo/doid.owl#>"
                + "SELECT  ?concept ?def"
                + "WHERE {"
                + "?concept  rdf:type  obo:DOID_13250."
                + "obo:DOID_13250 obo:IAO_0000115  ?def."
                + "}";

        Query query = QueryFactory.create(queryString3);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        // Output query results
        ResultSetFormatter.out(System.out, results, query);
        // Important - free up resources used running the query
        qe.close();
    }

}
