
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         this.records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr  = new FileResource(filename);
         
         //iterating over lines
         for(String s : fr.lines()){
             LogEntry data = WebLogParser.parseEntry(s);
             records.add(data);
         }
     }
     
     public int countUniqueIps(){
         ArrayList<String> UniqueIps = new ArrayList<String>();
         
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             
             if(!UniqueIps.contains(ipAddr)){
                 UniqueIps.add(ipAddr);
             }
         }
         return UniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num){
          ArrayList<Integer> codes = new ArrayList<Integer>();
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status > num && !codes.contains(status)){
                 //System.out.println(le);
                 codes.add(status);
             }
         }
         
         System.out.println(codes + " " + codes.size());
     } 
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> ipadds = new ArrayList<String>();
         for(LogEntry le : records){
             Date d = le.getAccessTime();
             String date = d.toString();
             
             if(date.substring(4,10).equals(someday) && !ipadds.contains(le.getIpAddress())){
                 ipadds.add(le.getIpAddress());
                 
             }
         }
         return ipadds;
     }
     
     public int countUniqueIPsINRange(int low,int high){
         ArrayList<String> ipadds = new ArrayList<String>();
          int numOfIPs = 0;
         for(LogEntry le : records){
             int status = le.getStatusCode();
             if(status >= low && status <= high && !ipadds.contains(le.getIpAddress())){
                 //System.out.println(le);
                 ipadds.add(le.getIpAddress());
                 numOfIPs++;
             }
         }
         return numOfIPs;
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> ipcounts = new HashMap<String,Integer>();
         
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!ipcounts.containsKey(ip)){
                 ipcounts.put(ip , 1);
             }
             else{
                 ipcounts.put(ip , ipcounts.get(ip) + 1);
             }
         }
         
         return ipcounts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int max_visit = 0;
         String max_visit_ip = "";
         for(String ip : counts.keySet()){
             int no_visit = counts.get(ip);
             if (no_visit > max_visit){
                 max_visit = no_visit;
                 max_visit_ip = ip;
             }
             
         }
         //System.out.println("IP with max visits  :" + max_visit_ip);
         return max_visit;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> ips = new ArrayList<String>();
         int max_visit = mostNumberVisitsByIP(counts);
         for(String ip : counts.keySet()){
           int no_visit = counts.get(ip);
           if(max_visit == no_visit && !ips.contains(ip)){
               ips.add(ip);
           }
         }
         return ips;
     }
     
     public HashMap<String , ArrayList<String>> iPsForDays(){
         HashMap<String , ArrayList<String>> dateIPs = new HashMap<String,ArrayList<String>>();
         
         for(LogEntry le : records){
             Date d = le.getAccessTime();
             String date = d.toString().substring(4,10);
             dateIPs.put(date , uniqueIPVisitsOnDay(date));
             
         }
         return dateIPs;
     }
     
     public String dayWithMostIPVisits(HashMap<String , ArrayList<String>> counts){
         String date_max = "";
         int max = 0;
         for(Map.Entry<String, ArrayList<String>> entry : counts.entrySet()){
             
             if(entry.getValue().size() >= max){
                 max = entry.getValue().size();
                 date_max =entry.getKey();
             }
             
         }
         return date_max;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String date){
        HashMap <String,Integer> visits = new HashMap <String,Integer>();

        for(LogEntry le: records){
            String IP = le.getIpAddress();
            if(le.getAccessTime().toString().substring(4,10).equals(date)){
                if(!visits.containsKey(IP)){
                    visits.put(IP, 1);
                }else{ visits.put(IP, visits.get(IP)+1); }
            }
        }return iPsMostVisits(visits);
    }
    
    
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
