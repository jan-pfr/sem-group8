package com.napier.semgroup.ui;

import com.napier.semgroup.logic.BusinessLogic;

import java.io.*;
import java.util.*;
import java.util.Properties;

public class CmdUserInterface {
    private BusinessLogic businessLogic;

    //public CmdUserInterface(BusinessLogic businessLogic){
        //this.businessLogic = businessLogic;
    //}


    public void showMenu(){
        System.out.println("Welcome to to the Population Information System.");
        System.out.println("Please select one option of the menu provided below by typing the number:");
        System.out.println("_________________________________________________________________________\n");
        for (int i = 0; i < 23; i++){

        }

    }
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
