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

    public Etalage(int nbcartes) {
        this.ligne1 = new Carte[nbcartes];
        this.ligne2 = new Carte[nbcartes];
    }

    public Carte[] getLigne1() {
        return ligne1;
    }

    public void setLigne1(Carte[] ligne1) {
        this.ligne1 = ligne1;
    }

    public Carte[] getLigne2() {
        return ligne2;
    }

    public void setLigne2(Carte[] ligne2) {
        this.ligne2 = ligne2;
    }

    public void addCarte(Carte carte){        
        if(this.isFull(this.ligne1)){
            if(this.isFull(this.ligne2)){
                  this.ligne1 = this.ligne2.clone();
                  this.ligne2[0] = carte;
            }
            else{
                int i=0;
                boolean array=false;
                while(i<this.ligne2.length && array==true){
                    if(this.ligne2[i] != null)
                        array=true;
                    i++;
                }
                this.ligne2[i] = carte;
            }
        }
        else{
            int i=0;
            boolean array=false;
              while(i<this.ligne1.length && array==true){
                    if(this.ligne1[i] != null)
                        array=true;
                    i++;
                }
            this.ligne1[i] = carte;
        }
    }
    
    public boolean isFull(Carte[] tab){
       boolean res=true;
       int i=0;
       while(i<tab.length && res==true){
           if(tab[i]==null)
           res=false;
            i++;
        }
       return res;
    }
    
}
