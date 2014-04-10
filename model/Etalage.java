/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


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
    
    public void piocheCarte(Carte e){
    	for(int i=0;i<this.ligne1.length;i++){
    		if(ligne1[i].equals(e)){
    			ligne1[i]=null;
    		}
    	}
    	for(int i=0;i<this.ligne1.length;i++){
    		if(ligne2[i].equals(e)){
    			ligne2[i]=null;
    		}
    	}
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
    
    public boolean isEmpty(){
    	boolean res=true;
    	int i=0;
    	while(i<this.ligne1.length&&res){
    		if(ligne1[i]!=null||ligne2[i]!=null){
    			res=false;
    		}
    		i++;
    	}
    	return res;
    }
}
