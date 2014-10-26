/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Videam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author josh
 */
public class Website {
    protected String name;
    protected String url;
    protected String search;
    protected String type;
    protected int show;
    protected int season;
    protected int episode;
    
    protected ArrayList<Show> shows = new ArrayList<>();
    
    public Website(){
        
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSearch(String search){
        this.search = search;
    }
    public void setType(String type){
        this.type = type;
    }
    
    public void setShow(int show){
        this.show = show;
    }
    public int getShow(){
        return this.show;
    }
    public void setSeason(int season){
        this.season = season - 1; //off by one error because arraylist starts at 0
    }
    public int getEpisode(){
        return this.episode; //ts at 0
    }
    public void setEpisode(int episode){
        this.episode = episode; //ep number
    }
    
    protected String getContent(String urlin){
        //System.out.println("Getting content for "+urlin);
         try {
            URL url = new URL(urlin);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            String html = "";
            while ((inputLine = in.readLine()) != null) {
                html = html + inputLine + "\n";
            }
            in.close();
            return html;
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
         return null;
    }
    protected boolean checkShows(String name){
        for (Show show : shows) {
            if(show.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public String getName(){
        return this.name;
    }
    public String getUrl(){
        return this.url;
    }
    public Show getShow(int id){
        return this.shows.get(id);
    }
    public ArrayList<Show> getShows(){
        return shows;
    }
    public void addShow(Show show){
        this.shows.add(show);
    }
    
    
    public ArrayList<String> parse(String regex, String content){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        ArrayList<String> out = new ArrayList<>();
        while(matcher.find()) {
            out.add(matcher.group(1));
        }
        if(out.isEmpty()){
            out.add("Parse Error - please check your website class");
        }
        return out;
    }
}
