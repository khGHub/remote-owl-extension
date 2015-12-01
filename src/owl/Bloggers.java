/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.sparql.*;
import java.io.*;

public class Bloggers extends Object {

    static final String inputFileName = "E:\\OWL\\src\\owl\\bloggers.rdf";

    public static void main(String args[]) {
// Create an empty in-memory model
        Model model = ModelFactory.createDefaultModel();
// use the FileManager to open the bloggers RDF graph from the filesystem
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }
// read the RDF/XML file
        model.read(in, "");

// Create a new query
        String queryString
                = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "SELECT ?url "
                + "WHERE {"
                + " ?contributor foaf:name \"Jon Foobar\" . "
                + " ?contributor foaf:weblog ?url . "
                + " }";
        Query query = QueryFactory.create(queryString);
// Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
// Output query results
        ResultSetFormatter.out(System.out, results, query);
// Important - free up resources used running the query
        qe.close();
    }
}
