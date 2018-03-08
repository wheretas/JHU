package com.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;


public class RatesSocket {

    /**
     * Method that creates connection and retreives information from websocket server
     * @param hike
     * @param duration
     * @param year
     * @param month
     * @param day
     * @return
     */
    public HashMap<Integer,String> connection(Rates.HIKE hike, int duration, int year, int month, int day) {

        Socket echoSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String costOutput = "";


        //Open Connection
        try {
            echoSocket = new Socket("web6.jhuep.com", 20025);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Invalid Connection");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //format to retrive cost information
            String serverInput = "" + hike.hikeIndex() + ":" + year + ":" + month + ":" + day + ":" + duration;
            out.println(serverInput);
           costOutput = in.readLine();

            //close Sockets
            out.close();
            in.close();
            echoSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

       return validateOutput(costOutput);

    }


    private HashMap<Integer,String> validateOutput(String output) {

        //Parse the output into legible format for ui
        HashMap<Integer,String> hashMap = new HashMap<>();
        if (!output.contains("Quoted Rate")) {
            hashMap.put(0, output.substring(output.indexOf(":")+1,output.length()));
            return hashMap;
        } else {
            hashMap.put(1,output.substring(0,output.indexOf(":")));
            return hashMap;

        }


    }

}