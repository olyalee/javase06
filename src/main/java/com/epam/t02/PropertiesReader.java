package com.epam.t02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class PropertiesReader {
    String delemiters = "=: ";
    String fileName = ""; //c:\epam\javase05\javase05\src\resources\some.properties  ; c:\epam\javase06\src\resources\config.properties
    Map<String, String> properties = new HashMap<>();
    String key = "";
    String value = "";

    public void dialogWithUser() {
        System.out.println("Please, enter the path of the properties-file you would like to read:");
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.next();
        try {
            readPropertiesAndWriteMap(fileName);
        }catch (FileNotFoundException e){
            System.out.println("Error: file not found.");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (!key.equalsIgnoreCase("q")) {
            System.out.println("Enter the key if you want to know a value or press q:");
            key = scanner.next();
            if (!key.equalsIgnoreCase("q")) {
                System.out.println(findValueByKey(key));
            }
        }
        System.out.println("Thanks for using this app :)");
    }

    public void readPropertiesAndWriteMap(String fileName) throws IOException {
        String tmp = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((tmp = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(tmp, delemiters);
                if (tokenizer.countTokens() == 2) {
                    properties.put(tokenizer.nextToken(), tokenizer.nextToken());
                }
                //if string has other symbols from delemitrs (but it doesn't include those in value)
                if(tokenizer.countTokens()>2){
                    key = tokenizer.nextToken();
                    while(tokenizer.hasMoreElements())
                    {
                        value+=tokenizer.nextToken();
                    }
                    properties.put(key,value);
                }
            }
        }
    }

    public String findValueByKey(String key) {

        if (properties.containsKey(key)) {
            return properties.get(key);
        } else {
            return "There is no such key in this properties-file.";
        }
    }
}