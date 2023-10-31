/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class Algorithm {
    Library l = new Library();
    Validation val = new Validation();
    public void readFileConfig() {
        File pFile = new File("config.properties");
        Properties p = new Properties();
        if (pFile.exists()) {
            boolean checkFileConfig = val.checkFileConfig(pFile);
            if (checkFileConfig) {
                try {
                    FileReader fReader = new FileReader(pFile);
                    p.load(fReader);
                    copyFolder(p.getProperty("COPY_FOLDER"), p.getProperty("PATH"));
                    fReader.close();
                } 
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("System shutdown!");
        }
        else {
            System.out.println("File configure is not found!");
            createFileConfig(pFile, p);
            readFileConfig();
        }
    }

    public void createFileConfig(File pFile, Properties p) {
        OutputStream output = null;
        try {
            String copyFolder = l.getString("Copy Folder: ");
            String dataType = l.getString("Data Type: ");
            String path = l.getString("Path: ");
            output = new FileOutputStream(pFile);
            p.setProperty("COPY_FOLDER", copyFolder);
            p.setProperty("DATA_TYPE", dataType);
            p.setProperty("PATH", path);
            //save file config
            p.store(output, null);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("File cannot create");
            System.out.println("System shutdown");
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("File cannot create");
            System.out.println("System shutdown");
            return;
        } 
        finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("File cannot create");
                    System.out.println("System shutdown");
                }
            }
        }
    }

    

    public void copyFolder(String copyFolder, String path) {
        File f1 = new File(copyFolder);
        File f2 = new File(path);
        if (val.checkInformationConfig(f1, f2)) {
            File[] listOfFiles = f1.listFiles();
            for (int i = 0; i < listOfFiles.length; i++)
                if (listOfFiles[i].isFile()) {
                    copyFile(copyFolder + "\\" + listOfFiles[i].getName(), path);
                    System.out.println("File name: " + listOfFiles[i].getName());
                }
            System.out.println("Copy is finished...");
        } else
            System.err.println("System shutdown");
    }

    public void copyFile(String file, String folder) {
        File f1 = new File(file);
        File f2 = new File(folder);
        if (f1.exists() && f1.isFile() && f2.exists() && f2.isDirectory())
            try {
                FileInputStream fis = new FileInputStream(f1);
                FileOutputStream fos = new FileOutputStream(folder + "/" + f1.getName());
                int b = fis.read();
                while (b != -1) 
                    fos.write(b);
                fis.close();
                fos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
    }
}
