/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Utilitário para compactar e descompactar os arquivos
 *
 * @author Washington Luis
 */
public class ZipUtil {

    /**
     * This method zips the directory
     *
     * @param dir
     * @param zipDirName
     * @throws java.io.IOException
     */
    public static void zipDirectory(File dir, String zipDirName) throws IOException {
        List<String> filesListInDir = new ArrayList<>();

        populateFilesList(dir, filesListInDir);

        //now zip files one by one
        //create ZipOutputStream to write to the zip file
        FileOutputStream fos = new FileOutputStream(zipDirName);
        ZipOutputStream zos = new ZipOutputStream(fos);
        for (String filePath : filesListInDir) {
            System.out.println("Zipping " + filePath);
            //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
            ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length() + 1, filePath.length()));
            zos.putNextEntry(ze);
            //read the file and write to ZipOutputStream
            FileInputStream fis = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            fis.close();
        }
        zos.close();
        fos.close();
    }

    /**
     * This method populates all the files in a directory to a List
     *
     * @param dir
     * @param filesListInDir
     * @throws IOException
     */
    private static void populateFilesList(File dir, List<String> filesListInDir) throws IOException {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                filesListInDir.add(file.getAbsolutePath());
            } else {
                populateFilesList(file, filesListInDir);
            }
        }
    }

    /**
     * This method compresses the single file to zip format
     *
     * @param file
     * @param zipFileName
     * @throws java.io.IOException
     */
    public static void zipSingleFile(File file, String zipFileName) throws IOException {
        //create ZipOutputStream to write to the zip file
        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);
        //add a new Zip Entry to the ZipOutputStream
        ZipEntry ze = new ZipEntry(file.getName());
        zos.putNextEntry(ze);
        //read the file and write to ZipOutputStream
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        //Close the zip entry to write to zip file
        zos.closeEntry();
        //Close resources
        zos.close();
        fis.close();
        fos.close();
        System.out.println(file.getCanonicalPath() + " is zipped to " + zipFileName);
    }

    public static void unzip(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            System.out.println("Unzipping to " + newFile.getAbsolutePath());
            //create directories for sub directories in zip
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            //close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        //close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();
    }
}
