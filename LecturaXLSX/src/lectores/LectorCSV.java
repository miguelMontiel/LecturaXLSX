package lectores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LectorCSV 
{
	private static Scanner scanner;
	
	public static void main(String[] args)
	{
		scanner = new Scanner(System.in);
		System.out.println("Archivo a leer: ");
		String archivoCSV = "src/" + scanner.nextLine();
		
		BufferedReader bufferedreader = null;
		String linea = "";
		String cortarCSV = ",";
		
		try
		{
			// Leer el archivo a leer, el cual se lo indique arriba.
			bufferedreader = new BufferedReader(new FileReader(archivoCSV));
			
			// Mientras exista una linea siguiente el while se va a ejecutar.
			while((linea = bufferedreader.readLine()) != null)
			{
				String[] texto = linea.split(cortarCSV);
				
				// Por cada linea que está en el archivo CSV que me están dando voy a imprimirla en una linea diferente. 
				// Para hacerlo más dinámico puedo guardar cada linea en una variable y exportarla más fácil.
				for(int i = 0; i < texto.length; i++)
				{
					System.out.print(" " + i + ": " + texto[i]);
				}
				System.out.println();
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
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
	}
}
