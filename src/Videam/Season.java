/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Videam;

import java.util.ArrayList;

/**
 *
 * @author josh
 */
public class Season {
    private ArrayList<Episode> episodes = new ArrayList<>(); //season hasMany episodes
    private int number;
    private String url;
    public Season(){
        
    }
    public void setNumber(int number){
        this.number = number; 
    }
    public void setUrl(String url){
        this.url = url;
    }
    public int getNumber(){
        return this.number;
    }
    public String getUrl(){
        return this.url;
    }
    public void addEpisode(Episode episode){
        episodes.add(episode);
        
    }
    public ArrayList<Episode> getEpisodes(){
        return episodes;
    }
    @Override
    public String toString(){
        return "Season "+this.number;
    }
}
