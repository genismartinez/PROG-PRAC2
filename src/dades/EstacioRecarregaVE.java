package dades;
/**
 * Clase con la info de las estaciones
 * @author Carlos y Genis
 *
 */
public class EstacioRecarregaVE {
	private String promotor;
	private String[] tipusVelocitat;
	private int numTipusVelocitat;
	private float latitud;
	private float longitud;
	private String provincia;
	private String municipi;
	private int numPlaces;
	
	/**
	 * Metodo constructor
	 * @param promotor promotor
	 * @param tipusVelocitat velocidad
	 * @param latitud latitud
	 * @param longitud longitud
	 * @param provincia provincia
	 * @param municipi municipio
	 * @param nplaces numero de plazas
	 */
	public EstacioRecarregaVE(String promotor, String tipusVelocitat, float latitud, float longitud, String provincia, String municipi, int nplaces) {
		this.promotor=promotor;
		this.tipusVelocitat=new String[1]; // reservem espai per guardar tres tipus de velocitat diferents de l'estació
		this.tipusVelocitat[0]=tipusVelocitat;
		this.numTipusVelocitat=1; // en el constructor rebem només un tipus
		this.latitud=latitud;
		this.longitud=longitud;
		this.provincia=provincia;
		this.municipi=municipi;
		this.numPlaces=nplaces;
		
	}
	/**
	 * Objeto vacio para hacer pruebas
	 */
	public EstacioRecarregaVE() {
		this.tipusVelocitat=new String[1];
		tipusVelocitat[0]="rapid";
		
	}
	
	
	/**
	 * Metodo que nos permmite añadir un nuevo tipo de velocidad al objeto
	 * @param tipoVelocitat nombre de la velocidad que le queremos añadir
	 */
	public void addSpeed(String tipoVelocitat) {
		numTipusVelocitat++;
		String[]aux=new String[numTipusVelocitat];
		if(numTipusVelocitat==2) {
			for(int i=0;i<numTipusVelocitat-1;i++) {
				aux[i]=tipusVelocitat[i];
				tipusVelocitat=new String[numTipusVelocitat];
				tipusVelocitat[i]=aux[i];
			}
		tipusVelocitat[1]=tipoVelocitat;
		}
		
	}
	
	
	/**
	 * Getter
	 * @return tipusVelocitat
	 */
	public String[] getTipusVelocitat() {
		return tipusVelocitat;
	}
	/**
	 * Setter
	 * @param index en que posi del array queremos establecer la velocidad
	 * @param velocidad velocidad nueva que queremos establecer
	 */
	public void setTipusVelocitat(int index,String velocidad) {
		tipusVelocitat[1]=velocidad;
	}
	
	/**
	 * Getter
	 * @return latitud
	 */
	public float getLatitud() {
		return latitud;
	}
	
	/**
	 * Settrer
	 * @param latitud latitud a establecer
	 */
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	/**
	 * Getter
	 * @return longitud
	 */
	public float getLongitud() {
		return longitud;
	}
	
	/**
	 * Setter
	 * @param longitud longitud a establecer
	 */
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	
	/**
	 * Getter
	 * @return numero de plazas
	 */
	public int getNumPlaces() {
		return numPlaces;
	}
	
	/**
	 *Getter 
	 * @return municipio de la estacion
	 */
	public String getMunicipi() {
		return municipi;
	}
	
	/**
	 * Metodo booleano que nos dice si nuestra estacion se encuentra en el municipio que
	 * pasamos por parámetro
	 * @param municipi municipio donde queremos comprboar si esta nuestra estacion
	 * @return true si se encuentra/false en caso contrario
	 */
	public boolean esTrobaEnAquestMunicipi(String municipi) {
		/*this.municipi.replaceAll(" ", "");
		municipi.replaceAll(" ", "");*/
		
		return (municipi.equalsIgnoreCase(this.municipi));
	}
	
	/**
	 * Metodo booleano que nos dice si nuestra estacion se encuentra en el municipio que
	 * pasamos por parametro
	 * @param provincia provincia donde queremos comprobar si se encuentra nuestra estacion
	 * @return true si se encuentra/false en caso contrario
	 */
	public boolean esTrobaEnAquestaProvincia(String provincia) {
		return (provincia.equalsIgnoreCase(this.provincia));
	}
	
	/**
	 * Metodo booleano que nos dice si nuestra estacion tiene el tipo de recarga que 
	 * le pasamos por parametro
	 * @param tipusRecarrega tipo de recarga a commprobar si la tiene nuestra estacion
	 * @return true si la tiene/false en caso contrario
	 */
	public boolean teAquestTipusRecarrega(String tipusRecarrega) {
		boolean trobat=false;
		int pos=0;
		
		while ((pos<numTipusVelocitat) && (!trobat)) {
			if (tipusVelocitat[pos].equalsIgnoreCase(tipusRecarrega))
				trobat=true;
			else
				pos++;
		}
		
		return trobat;
	}
	
	@Override
	public String toString() {
		String aux="EstacioRecarregaVE =>";
		aux=aux+"\n\tpromotor= " + promotor + ", tipusVelocitat= [ ";
		aux=aux+tipusVelocitat[0];
		for (int i=1; i<numTipusVelocitat; i++)
			aux=aux+", "+tipusVelocitat[i];
		aux=aux+"], ";
		aux=aux+"\n\tlatitud= " + latitud + ", longitud= "+longitud;
		aux=aux+"\n\tprovincia= " + provincia + ", municipi= "+municipi;
		aux=aux+"\n\tnumPlaces= " + numPlaces + "\n\n";
		return aux;
	}
	
	/**
	 * Metodo que nos devuelve una copia del objeto actual, si tiene mas de una velocidad se
	 * la añadimos tambien
	 * @return copia del objeto sobre el que se llama al metodo
	 */
	public EstacioRecarregaVE copia() {
		EstacioRecarregaVE est=new EstacioRecarregaVE(promotor, tipusVelocitat[0], latitud, longitud, provincia, municipi, numPlaces);
		if(est.getTipusVelocitat().length==2) {
			est.addSpeed(tipusVelocitat[1]);
		}
		return est;
	}
	
	/**
	 * Metodo que calcula la distancia que hay desde nuestra ubicacion hasta las coordenadas pasadas como parametro
	 * @param latitud latitud de la estacion para comprobar a que distancia se encuentra
	 * @param longitud longitud de la estacion para comprobar a que distancia se encuentra
	 * @return distancia del objeto actual hasta la posi pasada por parametro
	 */
	public double distanciaA(float latitud, float longitud) {
		double dist=0;
		double lat1, lat2, lon1, lon2, incLat, incLon, a, r;
		
		lat1=latitud*Math.PI/180;
		lat2=this.latitud*Math.PI/180;
		lon1=longitud*Math.PI/180;
		lon2=this.longitud*Math.PI/180;
		
		incLat=lat2-lat1;
		incLon=lon2-lon1;
		
		a=Math.sin(incLat/2)*Math.sin(incLat/2)+Math.cos(lat1)*Math.cos(lat2)*Math.sin(incLon/2)*Math.sin(incLon/2);
		
		r= 6378.137;
		dist=r*2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return(dist);
	}
	
}