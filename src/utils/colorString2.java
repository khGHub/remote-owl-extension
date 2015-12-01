/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.StringTokenizer;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class colorString2
{

public static void main( String[] args )
{
    new colorString2();   

}

public colorString2( )
{
    kFrame f = new kFrame();
	f.setSize( 400, 400 );
	f.setVisible( true );
}

private static class kFrame extends JFrame
{
	public void paint(Graphics g) 
    {
		super.paint( g );
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor( new Color(255, 0, 0) );
               
                int wordSpace=20;
                int initOffset=5;
                String fullSent="how are you today there is some thing rare";
                g2d.setColor( new Color(255,0,0));
                StringTokenizer st=new StringTokenizer(fullSent);
                int counter=1;
                String currentTokens="";
                while(st.hasMoreTokens()){
                    String oneToken=st.nextToken();
                    if(counter==1){
                        g2d.drawString(oneToken,initOffset,50);
                        currentTokens=oneToken;
                    }
                    else{
                        currentTokens+=" "+oneToken;
                        int spaces=counter*wordSpace;
                        int newX=initOffset+currentTokens.length()+spaces;
                        g2d.drawString(oneToken,newX,50);
                        
                    }
                        
                   counter++;     
                }
//             //   String three="ok";
//		g2d.drawString(first,initOffset,50);
//                g2d.setColor( new Color(255,255, 0) );
//                int newx=initOffset+first.length()+space;
//                System.out.println("first length :"+first.length());
//                  System.out.println("new  length :"+newx);
//                  
//                g2d.drawString(tow,newx,50);
//                  g2d.setColor( new Color(255,255,255) );
//                  int newx2=initOffset+first.length()+tow.length()+space+space;
//               // this.repaint();
//               // g2d.setColor( new Color(255,255,255) );
//                g2d.drawString(three,newx2,50);
                
                
	}
}
}