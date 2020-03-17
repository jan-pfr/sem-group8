package com.napier.semgroup.ui;

import com.napier.semgroup.logic.BusinessLogic;


import java.io.*;
import java.util.*;
import java.util.Properties;

public class CmdUserInterface {
    private BusinessLogic businessLogic;
    Properties prop = null;
    Integer menu = -1;

    public CmdUserInterface(BusinessLogic businessLogic){
        this.businessLogic = businessLogic;
    }
    public Integer showMenu(boolean input){
        System.out.println("Welcome to to the Population Information System!");
        System.out.println("Please select one option of the menu provided below by typing the number:");
        System.out.println("_________________________________________________________________________\n");
        try {
            prop = readPropertiesFile("src/main/resources/ui-menu-options.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < 24; i++){
            System.out.println(prop.getProperty("menu"+i));
        }

        do {
            try {
                Scanner myObj = new Scanner(System.in);
                System.out.println("Type in a Number:");
                if (input){
                menu = myObj.nextInt();
                }else{menu = 1;}
                System.out.println("Your choice is: " + prop.getProperty("menu" + menu));
            } catch (Exception e) {
                System.out.println("Looks like you typed a false number or symbol, try again.");

            }
        }while (menu >= 1 && 24 <= menu);
        return menu;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}
