package lectores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorCSV extends seleccionadorCSV
{
    public String getTodo(String todo)
    {
        seleccionadorCSV seleccionadorcsv = new seleccionadorCSV();
        String archivoSeleccionado = null;
        BufferedReader bufferedreader = null;
        String linea = "";
        String cortarCSV = ",";
        String[] texto;

        try
        {
            // Leer el archivo a leer, el cual se lo indique arriba.
            bufferedreader = new BufferedReader(new FileReader(seleccionadorcsv.getArchivo(archivoSeleccionado)));

            // Mientras exista una linea siguiente el while se va a ejecutar.
            while((linea = bufferedreader.readLine()) != null)
            {                
                texto = linea.split(cortarCSV);
                
                // Por cada linea que está en el archivo CSV que me están dando voy a imprimirla en una linea diferente. 
                // Para hacerlo más dinámico puedo guardar cada linea en una variable y exportarla más fácil.
                for(int i = 0; i < texto.length; i++)
                {
                	todo = texto[i].toString();
                	System.out.println(todo);
                }

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(bufferedreader != null)
            {
                try 
                {
                    bufferedreader.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
		return todo;
    }
}
