package dades;

/**
 * Llista de estaciones
 * @author Carlos y Genis
 *
 */
public class LlistaEstacions {
	private  EstacioRecarregaVE[]llista;
	private int nElems;
	private static int lindarAprop=30;
	/**
	 * Metodo constructor
	 * @param mida tamaño de la lista de estaciones
	 */
	public LlistaEstacions(int mida ) {
		nElems=0;
		llista=new EstacioRecarregaVE[mida];

	}
	
	/**
	 * Getter
	 * @return lindarAprop
	 */
	public static int getLindarAprop() {
		return lindarAprop;
	}
	
	/**
	 * Setter
	 * @param lindarAprop nuevo lindar a establecer
	 */
	public static void setLindarAprop(int lindarAprop) {
		LlistaEstacions.lindarAprop = lindarAprop;
	}

	/**
	 * Getter
	 * @return numero de elementos que contiene nuestra lista
	 */
	public int getElems() {
		return nElems;
	}
	/**
	 * Metodo que añade un elemento al final de la lista, usado cuando separamos el array
	 * y vamos creando objetos y añadiendolos a la lista
	 * @param elem le pasamos por parametro el elemento que queremos añadir a la lista
	 */
	public void afegirElem(EstacioRecarregaVE elem) {
		if(nElems<llista.length) {
			llista[nElems]=elem.copia();
			if(elem.getTipusVelocitat().length==2)
				llista[nElems].addSpeed(elem.getTipusVelocitat()[1]);
			nElems++;
		}
		else {
			if(nElems>=llista.length) {
				if(nElems==0)nElems++;
				EstacioRecarregaVE[] aux=new EstacioRecarregaVE[nElems];
				int i=0;
				while(i<nElems) {
					aux[i]=llista[i];
					//llista=new EstacioRecarregaVE[nElems*2];
					i++;
				}
				llista=new EstacioRecarregaVE[nElems*2];
				i=0;
				while(i<nElems) {
					llista[i]=aux[i];
							i++;
				}
				llista[nElems]=elem.copia();
				nElems++;
				
			}}

	}
	
	/**
	 * Metodo que devuelve una copia de la estacion con mas plazas
	 * @return EstacioRecarregaVE.copia de la estacion con mas capacidad
	 */
	public EstacioRecarregaVE moreCapacity() {
        EstacioRecarregaVE max = llista[0];
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getNumPlaces() >= max.getNumPlaces()) {
                max = llista[i].copia();
            }
            
        }
        return max;
    }
	
	/**
	 * Metodo que devuelve una lista con las estaciones que se encuentran "cerca" dependiendo del lindar establecido
	 * @param latitud longitud a usar
	 * @param longitud longitud a usar
	 * @return lista de estaciones mas cercanas
	 */
	public LlistaEstacions closeStations(float latitud,float longitud) {
		int i;
		LlistaEstacions close=new LlistaEstacions(1);
		for(i=0;i<nElems;i++) {
			
			if (llista[i].distanciaA(latitud, longitud)<lindarAprop)
				close.afegirElem(llista[i]);
		}
		return close;
	}
	
	/**
	 * Metodo que comprueba si la primera instancia de Lleida es más cercana que la de Bcn
	 * @param lleida primera instancia de Lleida
	 * @param bcn primera instancia de Bcn
	 * @param latitud latitud de nuestra ubicacion
	 * @param longitud longitud de nuestra ubicacion
	 * @return instancia de la estacion mas cercana
	 */
	public EstacioRecarregaVE whichCloser(EstacioRecarregaVE lleida,EstacioRecarregaVE bcn,float latitud,float longitud) {
		if(lleida.distanciaA(latitud, longitud)>bcn.distanciaA(latitud, longitud)) {
			return bcn.copia();
		}
		else
			return lleida.copia();
	}
	/**
	 * Metodo que devuelve una copia de la estacion que se encuentra mas cercana a las coordenadas pasadas como parametro
	 * @param latitud latitud de nuestra ubicacion
	 * @param longitud longitud de nuestra ubicacion
	 * @return copia de la estacion mas cercana
	 */
	public EstacioRecarregaVE closestStation(float latitud,float longitud) {
		EstacioRecarregaVE closest=llista[0].copia();
		int i;
		for(i=0;i<nElems;i++) {
			if(llista[i].distanciaA(latitud, longitud)<closest.distanciaA(latitud, longitud)) {
				closest=llista[i].copia();
			}
		}
		return closest;
	}

	/**
	 * Metodo que comprueba si hay algun elemento en la provincia indicada
	 * @param provincia provincia a comprobar si está en la lista
	 * @return true si hay alguna/false en caso contrario
	 */
	public boolean hayAlgunaProvincia(String provincia) {
		int i=0;
		while(i<nElems) {
			if(llista[i].esTrobaEnAquestaProvincia(provincia))
				return true;
			else
				i++;
		}
		return false;
	}
	/**
	 * Metodo que comprueba si hay algun municipio con ese nombre en la lista
	 * @param municipio nombre para hacer la comprobacion
	 * @return true si hay alguno/false en caso contrario
	 */
	public boolean hayAlgunMunicipio(String municipio) {
		int i=0;
		while(i<nElems) {
			if(llista[i].esTrobaEnAquestMunicipi(municipio)) {
				return true;
			}
			else i++;
		}
		return false;
	}


	/**
	 * Metodo que devuelve cuantas estaciones tienen un tipo de velocidad que le pasamos por parametro,
	 * no tenemos en cuentas las mayusculas con el "equalsIgnorecase"
	 * @param velocidad velocidad leida por teclado y pasada por parámetro
	 * @return numero de estaciones con esa velocidad
	 */
	public int howManySpeeds(String velocidad) {
		int numSpeeds=0;
		for(int i=0;i<nElems;i++) {
			if(llista[i].getTipusVelocitat().length==2) {
				if(llista[i].getTipusVelocitat()[0].equalsIgnoreCase(velocidad)||llista[i].getTipusVelocitat()[1].equalsIgnoreCase(velocidad)) {
					numSpeeds++;
				}

			}
			else {
				if(llista[i].getTipusVelocitat()[0].equalsIgnoreCase(velocidad))
					numSpeeds++;
			}
		}
		return numSpeeds;
	}
	/**
	 * Devuelve una copia de la primera estacion que se encuentra con la provincia pasada como parametro
	 * @param provincia le pasamos el nombre de la privincia para devolver una copia de su primera instancia
	 * @return devuelve una copia de la primera instancia de la provincia pasada como parametro
	 */
	public EstacioRecarregaVE retFirstInst(String provincia) {
		boolean sortir=false;
		int i=0;
		while(i<nElems&&!sortir) {
			if(llista[i].esTrobaEnAquestaProvincia(provincia))
				return llista[i].copia();
			else
				i++;
		}
		return null;
	}

	/**
	 * Devuelve una lista de las estaciones que se encuentran en la poblacion pasada como parametro
	 * @param poblacion le pasamos la poblacion deseada para eliminar las estaciones que se encuentran ahi
	 * @return lista de las estaciones que estan en esa poblacion
	 */
	public LlistaEstacions returnSamePobl(String poblacion) {
		LlistaEstacions samePobl=new LlistaEstacions(0);
		int i=0;
		while(i<nElems) {
			if(llista[i].esTrobaEnAquestMunicipi(poblacion)) {
				samePobl.afegirElem(llista[i]);
			}
			i++;
		}
		return samePobl;
	}

	/**
	 * Metodo que elimina los elementos que se encuentran en la poblacion pasada como parametro, 
	 * que se lee por teclado
	 * @param nom nombre de la la poblacion, cuyas estaciones se quieren elimminar
	 */
	public void deleteElem(String nom) {
		int posi=0;
		if(llista.length>1) {
			int i=0;
			while(i<nElems) {
				if(llista[i].esTrobaEnAquestMunicipi(nom)) {
					nElems--;
					posi=i;
					while(i<nElems) {

						llista[i]=llista[i+1];
						i++;

					}
					EstacioRecarregaVE[] aux=new EstacioRecarregaVE[nElems];
					for(i=0;i<nElems;i++) {
						aux[i]=llista[i];
					}
					this.llista=new EstacioRecarregaVE[nElems];
					for(i=0;i<nElems;i++) {
						llista[i]=aux[i];
					}
					i=posi;
				}
				else
					i++;				
			}
		}
	}

	/**
	 * Metodo que se encarga de separar los datos pasados como parametro
	 * Se lo pasamos todo en una lista de string e iremos creando objetos asignada a cada campo 
	 * los datos correspondientes
	 * @param array lista de datos leidos del fichero
	 * @param nLinies numero de lineas que se han leido del fichero, sera el numero de objetos que crearemos
	 * @return lista de estaciones con todos los elementos añadidos
	 */
	public LlistaEstacions separateArray(String[] array, int nLinies) {
		int i = 0;
		int j = 0;
		int pos = 0;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 418)
			nLinies = 418;
		LlistaEstacions list = new LlistaEstacions(nLinies); // Creamos lista para ir guardando elementos

		while (j < nLinies) {
			String[] split = array[i].split(";"); // Separo por ; en diferentes campos
			pos = i;
			for (i = 0; i < split.length; i++) {
				split[i] = split[i].replaceAll(",", ".");
			}
			String[] speed=split[1].split(" i ");
			i = pos;
			// Guardo los diferentes campos en un objeto nuevo
			//Ordre de pas de paràmetres per a crear l'objecte:
			//promotor,tipusVelocitat,  latitud
			EstacioRecarregaVE station = new EstacioRecarregaVE(split[0], speed[0], Float.parseFloat(split[2]),
					//,  longitud,  			provincia,municipi, nplaces
					Float.parseFloat(split[3]), split[4], split[5], Integer.parseInt(split[6]));

			// Añado el objeto a la lista
			if(speed.length>1)
				station.addSpeed(speed[1]);
			list.afegirElem(station);

			i++;
			j++;
		}
		return list;
	}
	
	@Override
	public String toString() {
		String aux=new String();
		for(int i=0;i<nElems;i++) {
			aux=aux+i+"-"+llista[i]+"\n";
		}
		return aux;
	}

}