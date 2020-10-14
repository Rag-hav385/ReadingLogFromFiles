
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        
    } 
    
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int Unique_val_no = la.countUniqueIps();
        System.out.println("Unique Values : " + Unique_val_no);
    }
    
    
    public void testprintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testuniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ipadd_atDay = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(ipadd_atDay.size());
    }
    
    public void testcountUniqueIPsINRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        
        int count = la.countUniqueIPsINRange(200,299);
        System.out.println(count);
    }
    
    public void testcountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        
        HashMap<String,Integer> count = la.countVisitsPerIP();
        System.out.println(count);
        System.out.println("Unique IPs :" + count.size());
        
    }
    
    public void testmostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        
        HashMap<String,Integer> count = la.countVisitsPerIP();
        int max_visits = la.mostNumberVisitsByIP(count);
        System.out.println(max_visits);
    }
    
    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        
        HashMap<String,Integer> count = la.countVisitsPerIP();
        
        ArrayList<String> ips = la.iPsMostVisits(count);
        for(int i = 0; i < ips.size() ; i++){
            System.out.println(ips.get(i));
        }
    }
    
    public void testiPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        
        HashMap<String , ArrayList<String>> count = la.iPsForDays();
        System.out.println(count);
    }
    
    public void testdayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String , ArrayList<String>> count = la.iPsForDays();
        
        //ArrayList<String>
        String  counts = la.dayWithMostIPVisits(count);
        //for(int i = 0 ; i < counts.size() ; i++){
            System.out.println(counts);
        //}
        
    }
    
    public void testiPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String , ArrayList<String>> count = la.iPsForDays();
        
        ArrayList<String> counts = la.iPsWithMostVisitsOnDay(count , "Sep 29");
        System.out.println(counts);
    }
}
