package aplicacio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

/**
 * Programa principal
 * @author Carlos y Genis
 *
 */
public class UsaLlistaEstacionsVE {
	static Scanner teclat = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		mostrarMenu();
		
	}
	
	public static void mostrarMenu() throws FileNotFoundException {
		float mylatitud=(float)41.1328273;
		float mylongitud=(float) 1.2440310;
		float latitud=0;
		float longitud=0;
		boolean posible=false,sevenDone=false;
		
		System.out.println("Indica el número de línies a llegir del fitxer (màxim 418)");
		int userInput = Integer.parseInt(teclat.nextLine());
		String[] fileData = llegirLiniesFitxer(userInput);
		LlistaEstacions llistaE = new LlistaEstacions(userInput);	//cremos lista de EstacioRecarregaVE de tanta dimension
																	//como lineas del fichero lea el usuario
		llistaE=llistaE.separateArray(fileData, userInput);			//separamos el array y creamos objetos y los añadimos a la lista
		boolean sortir=false;
	while(sortir==false) {	//programa se ejecuta  hasta que el usuario decida salir, introduciendo por teclado la opcion indicada
			System.out.println("********************************************************************");
			System.out.println("Introdueix la opció que vols executar: ");
			System.out.println("1-Eliminar un element");
			System.out.println("2- Trobar si és més propera la 1a instància de Lleida o la de BCN");
			System.out.println("3 -Num estacions amb la mateixa velocitat");
			System.out.println("4- Mostrar la estació amb més places");
			System.out.println("5- Mostrar la estació més propera");
			System.out.println("6- Mostrar elements que estan a prop");
			System.out.println("7- Modificar lindar");
			System.out.println("8- Estacio propera amb més capacitat");
			System.out.println("9- Mostrar elements de la llista");
			System.out.println("10- Sortir");
			System.out.println("********************************************************************");
			do {
			userInput=teclat.nextInt();
			if(!posible&&(userInput==7||userInput==8))System.out.println("Primer has de triar la opcio 6");}while(!posible&&(userInput==7
					||userInput==8));
			
			switch (userInput) {
			case 1:	//Eliminar estacion con provincia
				System.out.println("Escriu el nom de la població, les estacions de la qual  vols"
						+ " eliminar");
				Scanner name=new Scanner(System.in);
				
				String nom=name.nextLine();
				if(llistaE.hayAlgunMunicipio(nom)) {	//Comprobamos si hay algun elemento en la lista con ese municipio
					System.out.println("*ABANS: *\n"+llistaE);	//mostramos la lista antes de borrarlo
					llistaE.deleteElem(nom);
					System.out.println("*DESPRÉS: *\n"+llistaE);	//mostramos la lista despues de borrarlo
				}
				else
					//Si no hay ninguna instancia con ese nombre, lo decimos por pantalla
					System.out.println("No hi ha cap instància amb el nom: "+nom);

				break;
			case 2:	//estacion mas cercana
				//Comprobamos que haya alguna instancia con la provincia pasada commo parametro
				
				if((llistaE.retFirstInst("Lleida")!=null)&&llistaE.retFirstInst("Barcelona")!=null)	{
					System.out.println("Introdueix la latitud: ");
					mylatitud=teclat.nextFloat();
					System.out.println(mylatitud);
					System.out.println("Introdueix la longitud: ");
					mylongitud=teclat.nextFloat();
					System.out.println("1a inst Lleida: "+llistaE.retFirstInst("Lleida"));
					System.out.println("1a inst Bcn: "+llistaE.retFirstInst("Barcelona"));
					System.out.println("Estacio més propera: "+llistaE.whichCloser(llistaE.retFirstInst("Lleida"),
						llistaE.retFirstInst("Barcelona"),mylatitud,mylongitud));}
				else
					System.out.println("Hi ha d'haver mínim una instancia amb "
							+ "el nom de: Lleida i de: Barcelona");
				break;
			case 3:	//num estaciones con velocidad indicada
				teclat.nextLine();
				System.out.println("Introdueix el nom de la velocitat:");
				String nomVeloci=teclat.nextLine();
				System.out.println("*Num estacions amb la velocitat '"+nomVeloci+"': "+llistaE.howManySpeeds(nomVeloci)+"*");
				break;
			case 4:	//Devuelve una copia de la estacion con mas plazas
				System.out.println(llistaE.moreCapacity());
				break;
			case 5:
				teclat.nextLine();
				System.out.println("Introdueix la latitud amb el separador ',': ");
				latitud=(float)teclat.nextDouble();
				System.out.println("Introdueix la longitud amb el separador ',': ");
				longitud=(float)teclat.nextDouble();
				System.out.println("La estació més propera a la teva ubicació és :");
				System.out.println(llistaE.closestStation(latitud,longitud));
				break;
			
				
				/*System.out.println("Lindar original"+EstacioRecarregaVE.lindarDistancia);
				teclat.nextLine();
				System.out.println("Introduce el nuevo lindar");
				llistaE.modificarLindar(teclat.nextInt());
				System.out.println(EstacioRecarregaVE.lindarDistancia);*/


			case 6:
				posible=true;
				teclat.nextLine();
				System.out.println("Introdueix la latitud amb el separador ',': ");
				latitud=(float)teclat.nextDouble();
				System.out.println("Introdueix la longitud amb el separador ',': ");
				longitud=(float)teclat.nextDouble();
				System.out.println("*Estacions més properes amb llindar 30*: ");
				System.out.println(llistaE.closeStations(latitud, longitud));
				System.out.println("");
				break;
			case 7:
				sevenDone=true;
				LlistaEstacions.setLindarAprop(50);
				System.out.println("*Estacions més properes amb llindar 50*: ");
				System.out.println(llistaE.closeStations(latitud, longitud));
				break;
			
			case 8:
				if(sevenDone)
				System.out.println("Estacio propera amb llindar 50 amb més capacitat");
				else
					System.out.println("Estacio propera amb llindae 30 amb més capacitat");
				System.out.println(llistaE.closeStations(latitud, longitud).moreCapacity());
				break;
			
			case 9:	//mostrar lista de elementos
				System.out.println(llistaE);
				break;
			case 10:	//salir del programa
				sortir=true;
				break;

			}
		}
	}


	/**
	 * Llegim el fitxer i guardem les seves dades en un array
	 * @param nLinies	Número de línies que volem llegir del fitxer
	 * @return Vector amb les dades llegides
	 * @throws FileNotFoundException checks if file is found
	 */
	public static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {

		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 418)
			nLinies = 418;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("EstacionsRecarregaReduit.csv"));
		//System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		int i = 0;
		String capcalera=f.nextLine();
		for (i = 0; i < nLinies; i++) {

			result[i] = f.nextLine();

		}
		f.close();
		return result;
	}

}