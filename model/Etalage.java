/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author FT
 */
public class Etalage {
    private Carte[] ligne1;
    private Carte[] ligne2;

    public Etalage(int nbcolonne) {
        this.ligne1 = new Carte[nbcolonne];
        this.ligne2 = new Carte[nbcolonne];
    }

    public Carte[] getLigne1() {
        return ligne1;
    }

    public Carte[] getLigne2() {
        return ligne2;
    }
    
    public Carte piocheCarte(int ligne,int indice){
    	Carte res=null;
    	switch(ligne){
    	case 1:
    		res=ligne1[indice];
    		ligne1[indice]=null;
    		break;
    	case 2:
    		res= ligne2[indice];
    		ligne2[indice]=null;
    		break;
    	}
    	return res;
    }

    public void addCarte(Carte carte){        
        if(this.isFull(this.ligne1)){
            if(this.isFull(this.ligne2)){
                  this.ligne1 = this.ligne2.clone();
                  this.ligne2 = new Carte[this.ligne2.length];
                  this.ligne2[0] = carte;
            }
            else{
                int i=0;
                boolean array=false;
                while(i<this.ligne2.length && array==false){
                    if(this.ligne2[i] == null){
                        array=true;
                    }
                    else{
                        i++;
                    }
                }
                this.ligne2[i] = carte;
            }
        }
        else{
            int i=0;
            boolean array=false;
              while(i<this.ligne1.length && array==false){
                    if(this.ligne1[i] == null){
                        array=true;
                    }
                    else{
                        i++;
                    }
                }
            this.ligne1[i] = carte;
        }
    }
    
    public boolean isFull(Carte[] tab){
       boolean res=true;
       int i=0;
       while(i<tab.length && res==true){
           if(tab[i]==null){
                res=false;
           }
            i++;
        }
       return res;
    }
    public String toString(){
    	StringBuffer tmpStr=new StringBuffer();
    	for(int i=0;i<this.ligne1.length;i++){
    		tmpStr.append(ligne1[i]);
    		tmpStr.append(" ");
    	}
    	for(int i=0;i<this.ligne2.length;i++){
    		tmpStr.append(ligne2[i]);
    		tmpStr.append(" ");
    	}
		return new String(tmpStr);
    	
    }
    
}
