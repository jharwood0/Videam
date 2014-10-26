///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Videam;
//
//import java.awt.Desktop;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author josh
// */
//public class Videam {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//        Scanner input = new Scanner(System.in);
//        WatchSeries watchseries = new WatchSeries();
//
//        System.out.println("Enter search query:");
//        String in = input.nextLine();
//
//        watchseries.search(in);
//        System.out.println("These shows have been found");
//        ArrayList<Show> shows = watchseries.getShows(); //might not need this
//        //could create a class that will ask user to select season
//
//        for (int i = 0; i < shows.size(); i++) {
//            System.out.println((i + 1) + ".) " + shows.get(i).getName());
//        }
//
//        System.out.println("Which show would you like to watch");
//        int choice = input.nextInt();
//
//        watchseries.setShow(choice - 1); //set the show choice so we can find season list later
//
//        System.out.println(shows.get(choice - 1).getName()); //because we incremented in the for loop to avoid 0
//        System.out.println(shows.get(choice - 1).getUrl());
//        System.out.println("Now we will get Season list");
//        ArrayList<Season> seasons = watchseries.getSeasons();
//        for (Season season : seasons) {
//            System.out.println("Season " + season.getNumber());
//        }
//        System.out.println("Which season would you like to watch");
//        choice = input.nextInt();
//        System.out.println(choice);
//        System.out.println("Now we will get episode list");
//
//        watchseries.setSeason(choice); //set season choice
//
//        ArrayList<Episode> episodes = watchseries.getEpisodes();
//
//        for (Episode episode : episodes) {
//            System.out.println(episode.getNumber() + " .)" + episode.getTitle());
//        }
//        System.out.println("Which episode would ou like to watch?");
//        choice = input.nextInt();
//        
//        //this is needed because sometimes there is an episode 0, therefore episode 2 can be in both 1 or 2;
//        //so we will get the id of the episode that has the same number then set that id as choice so we can grab is easily in future
//        int i = 0;
//        for (Episode episode : episodes) {
//            if(episode.getNumber() == choice){
//                watchseries.setEpisode(i);
//                //System.out.println("set episode is: ");
//                System.out.println(episodes.get(watchseries.getEpisode()).getTitle()); //
//                break;
//            }
//            i++;
//        }
//        
//        if (Desktop.isDesktopSupported()) {
//            try {
//                Desktop.getDesktop().browse(new URI(episodes.get(watchseries.getEpisode()).getUrl()));
//            } catch (IOException | URISyntaxException ex) {
//                Logger.getLogger(Videam.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }else{
//            try {
//                // Ubuntu
//                Runtime runtime = Runtime.getRuntime();
//                runtime.exec("/usr/bin/google-chrome -new-window " + episodes.get(watchseries.getEpisode()).getUrl());
//            } catch (IOException ex) {
//                Logger.getLogger(Videam.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//
//}
