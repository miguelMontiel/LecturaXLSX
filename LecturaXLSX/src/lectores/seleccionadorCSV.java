package lectores;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class seleccionadorCSV 
{    
    public String getArchivo(String archivoSeleccionado)
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        String selectedfile = chooser.getSelectedFile().getAbsolutePath();
        
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
            System.out.println("Elegiste el archivo: " + chooser.getSelectedFile().getName() + ", que se encuentra en: " + selectedfile);
        }
        return selectedfile;
    }
}