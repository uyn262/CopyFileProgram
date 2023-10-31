/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import common.Algorithm;
import view.Menu;

/**
 *
 * @author Admin
 */
public class CopyFileProgram extends Menu<String> {
    static String[] options = {"Copy file", "Exit"};
    Algorithm ar;
    public CopyFileProgram() {
        super("========== COPY PROGRAM ==========", options);
        ar = new Algorithm();
    }

    @Override
    public void execute(int n) {
        switch(n) {
            case 1: ar.readFileConfig();
                break;
            case 2: System.exit(0);
                break;
        }
        
    }
    
}
