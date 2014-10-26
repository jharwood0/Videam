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
public class Show {
    
    private String name;
    private String url;
    private final ArrayList<Season> seasons = new ArrayList<>();
    public Show(){
        
    }
    public Show(String name, String url){
        
    }
    
    public ArrayList<Season> getSeasons(){
        return seasons;
    }
    public String getSeasonUrl(int id){
        return seasons.get(id).getUrl();
    }
    public Season getSeason(int id){
        return seasons.get(id);
    }
    public void addSeason(Season season){
        seasons.add(season);
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
}
