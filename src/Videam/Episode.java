/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Videam;

/**
 *
 * @author josh
 */
public class Episode {
    private String title;
    private String url;
    private int number;
    Episode(){
        
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public int getNumber(){
        return number;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getTitle(){
        return this.title;
    }
    //public int getNumber(){
    //    return this.number;
   // }
    public String getUrl(){
        return this.url;
    }
    
}
