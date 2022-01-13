package com.boot.main;

import java.io.*;
import java.util.zip.*;

import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static List<String> getInsideZipFiles(String zipFile) {
        List<String> list = new ArrayList<>();
        
        FileInputStream fs = null;
        ZipInputStream zs = null;
        ZipEntry ze = null;
        
        try {
            fs = new FileInputStream(zipFile);
            zs = new ZipInputStream(new BufferedInputStream(fs));
            
            while ((ze = zs.getNextEntry()) != null) {
                list.add(ze.getName());
            }
            
            zs.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    /*public static void decompressZip(File zipInput, File zipOutput) throws IOException {
        final byte[] buffer = new byte[1024];
        
        ZipInputStream zis = null;
        FileOutputStream fos = null;
        
        try {
            zis = new ZipInputStream(new FileInputStream(zipInput));
            fos = new FileOutputStream(zipOutput);
            
            ZipEntry ze = null;
            while ((ze = zis.getNextEntry()) != null) {
                String fileName = ze.getName();
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public static void unzipFile(String zipFile, String outputFolder) {
    
      byte[] buffer = new byte[1024];
    
      File folder = new File(outputFolder);
      if (!folder.exists()) {
          folder.mkdir();
      }
    
      try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
          // get the zipped file list entry
          ZipEntry ze = zis.getNextEntry();
          
          while (ze != null) {
        
          String fileName = ze.getName();
          File newFile = new File(outputFolder + File.separator + fileName);
        
          System.out.println("file unzip : " + newFile.getAbsoluteFile());
          
          // create all non exists folders
          // else you will hit FileNotFoundException for compressed folder
          new File(newFile.getParent()).mkdirs();
          
          try (FileOutputStream fos = new FileOutputStream(newFile)) {
              int len;
              while ((len = zis.read(buffer)) > 0) {
                  fos.write(buffer, 0, len);
              }
          }
        ze = zis.getNextEntry();
       }
    
       zis.closeEntry();
       zis.close();
    
       System.out.println("Done");
    
      } catch (IOException e) {
       e.printStackTrace();
      }
     }
    
    public static void main(String[] args) {
        /*List<String> files = getInsideZipFiles(new File("G:/Meu Drive/Projetos/Teste.zip"));
        
        for (String s : files) {
            System.out.println(s);
        }
        
        unZipIt("G:/Meu Drive/Projetos/Teste.zip", "G:/Meu Drive/Projetos/Teste");*/
    }
}
