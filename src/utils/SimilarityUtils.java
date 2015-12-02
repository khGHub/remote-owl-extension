/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author fadel
 */
public class SimilarityUtils {

    public static double cosSimilarity(Vector originalConcepts, Vector plagConcepts) {
        //get all concepts from original concepts vector
        Map<String, Double> originalMap = new HashMap<>();

        for (int i = 0; i < originalConcepts.size(); i++) {
            String s = (String) originalConcepts.get(i);
            String[] splits = s.split("_");
            originalMap.put(splits[0], Double.parseDouble(splits[1]));
        }
        //get all concepts,weight from plag concepts vector
        Map<String, Double> plagMap = new HashMap<>();

        for (int i = 0; i < plagConcepts.size(); i++) {
            String s = (String) plagConcepts.get(i);
            String[] splits = s.split("_");
            plagMap.put(splits[0], Double.parseDouble(splits[1]));
        }
        Set<String> intersectionSet = new TreeSet<>(originalMap.keySet());
        intersectionSet.retainAll(plagMap.keySet());
        double[] w1 = new double[intersectionSet.size()];
        double[] w2 = new double[intersectionSet.size()];
        int i = 0;
        for (String key : intersectionSet) {
            w1[i] = originalMap.get(key);
            w2[i] = plagMap.get(key);
            i++;
        }
        return cos(w1, w2);
    }

    public static double cos(double[] a1, double[] a2) {
        double product = 0;
        for (int i = 0; i < a1.length; i++) {
            product += a1[i] * a2[i];
        }
        double mag1 = 0, mag2 = 0;
        for (int i = 0; i < a1.length; i++) {
            mag1 += a1[i] * a1[i];
        }
        mag1 = Math.sqrt(mag1);
        for (int i = 0; i < a2.length; i++) {
            mag2 += a2[i] * a2[i];
        }
        mag2 = Math.sqrt(mag2);
        double cos = product / (mag1 * mag2);
        return cos;
    }

    public static void main(String[] args) {
        Vector v1 = new Vector();
        Vector v2 = new Vector();

        v1.add("c1_0.1");
        v1.add("c2_0.2");
        v1.add("c3_0.3");
        v1.add("c4_0.4");
        v1.add("c5_0.5");
        v1.add("c6_0.6");

        v2.add("c3_0.9");
        v2.add("c2_0.9");
        v2.add("c7_0.3");
        v2.add("c8_0.4");
        v2.add("c5_0.1");
        v2.add("c6_0.01");
        v2.add("c9_0.1");
        v2.add("c0_0.2");
        
        System.out.println(cosSimilarity(v1, v2));

    }
}
