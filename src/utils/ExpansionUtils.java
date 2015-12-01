/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Vector;
import nlpOperations.StopWordList;

/**
 *
 * @author A
 */
public class ExpansionUtils {

    public static Vector getSent(String text) {

        return StopWordList.segmenttxt(text);
    }

}
