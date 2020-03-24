package co.edu.javeriana.restaurante.negocio;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TxtFilter extends FileFilter{
    @Override
    public boolean accept(File f){
        return f.getName().toLowerCase().endsWith(".txt")||f.isDirectory();
    }
    public String getDescription(){
        return "Text files (*.txt)";
    }
}