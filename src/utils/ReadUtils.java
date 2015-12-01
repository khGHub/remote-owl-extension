/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class ReadUtils {
    
    public static Map readTxtFile(String filePath) {
        Map code2Names=new HashMap();
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String ontCode="";
                                String ontFullName="";
                                StringTokenizer st=new StringTokenizer(line);
                                while(st.hasMoreTokens()){
                                    if(ontCode.equals(""))
                                        ontCode=st.nextToken();
                                    else
                                      ontFullName+=" "+st.nextToken();
                                }
                                
			code2Names.put(ontCode, ontFullName);
			}
			fileReader.close();
			System.out.println("Contents of file:");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
                
                return code2Names;
	}
    
    public static String printConceptsMap(Map concepts){
        String conceptStr="";
        Iterator iter=concepts.keySet().iterator();
        while(iter.hasNext()){
            String conceptName=iter.next().toString();
            Map conceptInfo=(Map)concepts.get(conceptName);
            //get the concept Weight
            String conceptWeight=conceptInfo.get("weight").toString();
            conceptStr+="["+conceptName+":"+conceptWeight+"] ";
        }
        return conceptStr;
    } 
    
    public static String printConceptsVector(Vector concepts){
        String conceptStr="";
        for (int i = 0; i <concepts.size(); i++) {
           String conceptW= concepts.get(i).toString();
           conceptStr+="["+conceptW+"] ";
        } 
        return conceptStr;
    } 
}
