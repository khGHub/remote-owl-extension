package uk.ac.ebi.demo.ols.business;

import gui.MultiOntology;
import gui.OntologyLoader;
import uk.ac.ebi.demo.ols.soap.Query;
import uk.ac.ebi.demo.ols.soap.QueryServiceLocator;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * User: rcote
 * Date: 18-May-2007
 * Time: 12:21:47
 * $Id: $
 *
 * This is the main business class that will communicate with the OLS
 * webservice and serve data to the GUI classes.
 */
public class OLSClient {

    /**
     * calls OLS webserver and gets map of all ontologies. It will then
     * return a list of all ontology labels.
     * @return List of all ontology labels.
     * List should not be null.
     */
    public List<String> getOntologies(){

        List retval = new ArrayList<String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap ontologies = service.getOntologyNames();
            if (ontologies != null){
                retval.addAll(ontologies.keySet());
                Collections.sort(retval);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retval;
    }

    ////////////////////////// khaled////////////////////////////////
    public static void getOntologies2(){

        List retval = new ArrayList<String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap mm0=service.getTermsByName("cell proliferation", "GO", false);
            HashMap mm00=service.getTermsByExactName("cell proliferation", "GO");
            
            
            String sss = service.getTermById("topic:3303","EDAM");
            HashMap mm=service.getTermParents("topic:3303","EDAM");
            HashMap mm2=service.getTermRelations("topic:3303","EDAM");
            HashMap mm3=service.getTermChildren("topic:3303","EDAM",1, null);
            HashMap mm4=service.getTermMetadata("topic:3303","EDAM");
          
            System.out.println(sss);

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

       
    }
    
    public static void loadOntology(String ontCode) {
        HashMap allTermsInfo = new HashMap();
        List retval = new ArrayList<String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap allTerms = service.getAllTermsFromOntology(ontCode);
            //get full info
            Iterator iter = allTerms.keySet().iterator();
            int i = 0;
            while (iter.hasNext() && i < 100) {
                String termId = (String) iter.next();
                HashMap parentsHash = service.getTermParents(termId, ontCode);
                HashMap relationsHash = service.getTermRelations(termId, ontCode);
                HashMap childHash = service.getTermChildren(termId, ontCode, 1, null);
                HashMap metaDataHash = service.getTermMetadata(termId, ontCode);

                HashMap oneTermInfo = new HashMap();
                oneTermInfo.put("ontology", ontCode);
                oneTermInfo.put("parents", parentsHash);
                oneTermInfo.put("childs", childHash);
                oneTermInfo.put("relations", relationsHash);
                oneTermInfo.put("metaData", metaDataHash);

                allTermsInfo.put(termId, oneTermInfo);
                System.out.println(i);
                i++;
            }
            System.out.println("done");
            System.out.println("");

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
 }
    
    
    public  HashMap getAllTermInfo(String termName,String conceptPosition,String termOntology,String conceptWeight){

        HashMap infoMap=new HashMap();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            //get the id of the term
            HashMap idHash=service.getTermsByExactName(termName,termOntology);
            String termId=(String) idHash.keySet().toArray()[0];
            
            //get term childs,parents,relations,metadata
            HashMap parentsHash=service.getTermParents(termId,termOntology);
            HashMap relationsHash=service.getTermRelations(termId,termOntology);
            HashMap childHash=service.getTermChildren(termId,termOntology,1, null);
            HashMap metaDataHash=service.getTermMetadata(termId,termOntology);
          
            infoMap.put("weight",conceptWeight);
            infoMap.put("position",conceptPosition);
            infoMap.put("ontology",termOntology);
            infoMap.put("parents",parentsHash);
            infoMap.put("childs",childHash);
            infoMap.put("relations",relationsHash);
            infoMap.put("metaData",metaDataHash);
            System.out.println("done");

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

       return infoMap;
    }
    
    
    public  String geTermOntology(String termName) {
        /*
        here we have to find the ontology of the term 
        there are tow cases 
        1- the term belongs to one ontology then we 
           find the first ontology containing the term
        2- the term belongs to more than one ontology then
           we find all ontlogy names which contins the term
        
        and there are other parameter which is the loaded ontologies 
        that we can check a few not all ones
        */

        String allContainedOnt="";
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            //get the id of the term
            HashMap ontologyNamesHash = service.getOntologyNames();
            Iterator iter = ontologyNamesHash.keySet().iterator();
            while (iter.hasNext()) {
                String ontologyCode = (String) iter.next();
                //System.out.println("onto name is :" + ontologyCode);
                if(OntologyLoader.loadedOnt.containsKey(ontologyCode)){
                HashMap idHash = service.getTermsByExactName(termName, ontologyCode);
                if (idHash.size() > 0) {
                    if(MultiOntology.multiOntology)
                        allContainedOnt+="#"+ontologyCode;
                    else
                        return ontologyCode;
                }
                }

            }

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
        return allContainedOnt;
    }

    /**
     * calls OLS webserver and gets root terms of an ontology
     * @return Map of root terms - key is termId, value is termName.
     * Map should not be null.
     */
    public Map<String, String> getOntologyRoots(String ontology) {

        Map retval = new HashMap<String, String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap roots = service.getRootTerms(ontology);
            if (roots != null){
                retval.putAll(roots);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retval;

    }

    /**
     * calls OLS webserver and gets child terms for a termId
     * @return Map of child terms - key is termId, value is termName.
     * Map should not be null.
     */
    public Map<String, String> getTermChildren(String ontology, String termId) {

        Map retval = new HashMap<String, String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap terms = service.getTermChildren(ontology, termId, 1, null);
            if (terms != null){
                retval.putAll(terms);
            } 
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retval;

    }

    /**
     * calls OLS webserver and gets metadata for a termId
     * @return Map of metadata - key is data type, value is data value.
     * Map should not be null.
     */
    public Map<String, String> getTermMetadata(String termId, String ontology) {

        Map retval = new HashMap<String, String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap terms = service.getTermMetadata(termId, ontology);
            if (terms != null){
                retval.putAll(terms);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retval;

    }

    /**
     * calls OLS webserver and gets suggestions of terms for a given query
     * @return Map of suggested terms - key is termId, value is termName.
     * Map should not be null.
     */
    public Map<String, String> getTermsByName(String text, String ontology) {

        Map retval = new HashMap<String, String>();
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            HashMap terms = service.getTermsByName(text, ontology, true);
            if (terms != null){
                retval.putAll(terms);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return retval;

    }
    
    
    public Map getTermByExactName(String termCode, String ontology){
        Map termInfo=null;
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            termInfo = service.getTermsByExactName(termCode, ontology);
            
        } catch (ServiceException e){
            e.printStackTrace();
        } catch (RemoteException e){
            e.printStackTrace();
        }
        return termInfo;
    }
    
    public String getRelationTermById(String termId, String ontology){
        String  termName="";
        QueryServiceLocator locator = new QueryServiceLocator();
        try {
            Query service = locator.getOntologyQuery();
            termName = service.getTermById(termId, ontology);
            
        } catch (ServiceException e){
            e.printStackTrace();
        } catch (RemoteException e){
            e.printStackTrace();
        }
        return termName;
    }
    
    public static void main (String [] args){
//            Properties props= new Properties(System.getProperties());
//            props.put("http.proxySet", "true");
//            props.put("http.proxyHost", "10.1.8.66");     //the host and port need to be configured
//            props.put("http.proxyPort", "3128");             //for your network environment.
//            props.put("http.proxyUser", "khaled-omar");       //optional username
//            props.put("http.proxyPassword", "654321");   //optional password
//            Properties newprops = new Properties(props);
//            System.setProperties(newprops);
            loadOntology("DDANAT");
            //getOntologies2();
            //getAllTermInfo("cell proliferation","GO");
            //geTermOntology("cell proliferation");
    }
}
