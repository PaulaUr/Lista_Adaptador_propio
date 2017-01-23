package edu.upc.eetac.dsa.lista_adaptador_propio;

/**
 * Created by pauli on 21/12/2016.
 */
//Esta estructura hace de Handler

public class Lista_estructura {
    //Lista_entrada o
    // Muntanyes(ejemploClase)

    private int idImagen;
    private String name;
    private int altitude;

    public Lista_estructura(int idImagen, String name, int altitude) {
        this.idImagen = idImagen;
        this.name = name;
        this.altitude = altitude;
    }

    public int getIdImagen() {
        return idImagen;
    }

 //   public void setIdImagen(int idImagen) {
   //     this.idImagen = idImagen;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAltitude() {
        return String.valueOf(altitude);
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
}
