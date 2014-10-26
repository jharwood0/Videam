/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Videam;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 *
 * @author josh
 */
public class WatchSeries extends Website {

    private final String SearchResultsPattern = "\\<a title=\"watch serie (.*?)\\>"; //gets each tv show
    private final String SearchNamePattern = "(.*?)\" href="; //gets the name of tv show from searchresultpattern
    
    private final String UrlPattern = "href=\"(.*?)\""; //gets url from searchresultspattern

    private final String SeasonPattern = "\\<h2 class=\"lists\"\\>(.*?)\\</a\\>"; //get seasons
    private final String SeasonNumberPattern = "\\Season (.*)";
    
    private final String EpisodePattern = "value=\"1\" /\\>\\<a title=\"Watch(.*?)an\\>";
    //private final String EpisodePattern = "(value=\"1\" /\\>\\<a title=\"Watch )|(style=\"color\\: rgb)(.*?)an\\>";
    private final String EpisodeNamePattern = "\\<span class=\"\" \\>(.*)\\</sp"; //this needs to work for both valid and invalid links
    private final String EpisodeNumberPattern = "Episode ([0-9]+)";
    
    private final String FirstLinkPattern = "Watch This Link\\!\\</a\\>\\</td\\>\\<td\\> \\<a target=\"\\_blank\"(.*?)\\>Free ";
    private final String SecondLinkPattern = "\\<a class=\"myButton\" (.*?)\\>";
    //private
    
    public WatchSeries() {
        setUrl("http://watchseries.lt");
        setName("Watch Series");
        setSearch("/search/");
        setType("tv");
    }
    
    public String getWorkingLink(String url){ //temp input because cba to find episode link
        //parse all links
        String content = getContent(url);
        //parse episode
        ArrayList<String> links = parse(FirstLinkPattern, content);
        for (String link : links) {
            //System.out.println(link);
            String temp = parse(UrlPattern,link).get(0);
            System.out.println(this.url+temp);
            content = getContent(this.url+temp);
            ArrayList<String> link2 = parse(SecondLinkPattern, content); //get link
            temp = parse(UrlPattern, link2.get(0)).get(0);
            return temp;
        }
        //navigate to each link until it works
        //
        
        return null;
    }
    
    public ArrayList<Season> getSeasons(){
        //get content for show selected
        String content = getContent(shows.get(show).getUrl());
        
        //parse seasons
        ArrayList <String> seasonlist = parse(SeasonPattern, content);
        for (String seasonlist1 : seasonlist) {
            Season temp = new Season();
            
            temp.setNumber(Integer.parseInt(parse(SeasonNumberPattern,seasonlist1).get(0)));
            temp.setUrl(getUrl()+parse(UrlPattern,seasonlist1).get(0));
            
            shows.get(show).addSeason(temp); //add seasons to show
        }
        return shows.get(show).getSeasons();
    }
    
    public ArrayList<Episode> getEpisodes(){
        //get content for show selected
        String content = getContent(shows.get(show).getSeasonUrl(season)); //get
        //System.out.println(content);
        //parse episodes
        ArrayList <String> episodelist = parse(EpisodePattern, content); //these contain both url, number and name
        
        for (String episodelist1 : episodelist) {
            Episode temp = new Episode();
            //System.out.println(episodelist1);
            temp.setTitle(parse(EpisodeNamePattern, episodelist1).get(0).replace("&nbsp;"," "));
            temp.setUrl(getUrl()+parse(UrlPattern,episodelist1).get(0));
            temp.setNumber(Integer.parseInt(parse(EpisodeNumberPattern,episodelist1).get(0)));
            //System.out.println("number: "+parse(EpisodeNumberPattern,episodelist1).get(0));
            shows.get(show).getSeason(season).addEpisode(temp);
        }
        return shows.get(show).getSeason(season).getEpisodes();
    }
    public void search(String input) {
        String content = getContent(url + search + URLEncoder.encode(input));
        
        ArrayList<String> results = parse(SearchResultsPattern, content); //get all the results
        
        ArrayList<String> resultsnames;
        ArrayList<String> resultsurls;
        
        //for each result, parse name and url
        for (String result : results) {
            //for each tv show found get url and name
            resultsnames = parse(SearchNamePattern, result);
            resultsurls = parse(UrlPattern, result);
            
            Show temp = new Show();
            
            temp.setName(resultsnames.get(0)); //will always be 0 as there is only one to parse
            temp.setUrl(getUrl()+resultsurls.get(0)); // will always be 0
            
            if(!checkShows(temp.getName())){
                shows.add(temp);
            }
            
        }
        System.out.println(shows.size() + " shows have been found");
    }
}
