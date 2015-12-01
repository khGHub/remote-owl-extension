/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyLayer;

import java.util.HashMap;
import utils.Constants;
import utils.OntologyConstants;

/**
 *
 * @author Administrator
 */
public class OntologyManager {
    
    
    public static HashMap findSemanticMeaning(String tokens){
        HashMap semanticMean=new HashMap();
        
        String anatomyMeaning=AnatomyEncapsulate.phraseMeanings(tokens);
        String diseaseMeaning=DiseasesEncapsulate.phraseMeanings(tokens);
        String edamMeaning=EdamEncapsulate.phraseMeanings(tokens);
        String genoMeaning=GenoEncapsulate.phraseMeanings(tokens);
        String meshMeaning=MeshEncapsulate.phraseMeanings(tokens);
        String ogmsMeaning=OgmsEncapsulate.phraseMeanings(tokens);
        String wordNetMeaning=WordNetEncapsulate.phraseMeanings(tokens);
        
        
        
        
        
        
        if(!anatomyMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.ANATOMY_ONTOLOGY, anatomyMeaning);
        
        if(!diseaseMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.DISEASE_ONTOLOGY, diseaseMeaning);
        
        if(!edamMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.EDAM_ONTOLOGY, edamMeaning);
        
        if(!genoMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.GENO_ONTOLOGY, genoMeaning);
        
        if(!meshMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.MESH_ONTOLOGY, meshMeaning);
        
        if(!ogmsMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.OGMS_ONTOLOGY, ogmsMeaning);
        
        if(!wordNetMeaning.equals(Constants.NO_VALUE))
            semanticMean.put(OntologyConstants.WORDNET_ONTOLOGY, wordNetMeaning);
        
        return semanticMean;
    }
}
