package com.ulasalle.cbir;

import net.semanticmetadata.lire.indexers.parallel.ParallelIndexer;
import java.util.Arrays;

public class IndexadorParalelo {

    private ParallelIndexer indexador;
    private String rutaBD;
    private int numeroHilos;

    public IndexadorParalelo()
    {
        this.rutaBD="index";
        this.numeroHilos=getNumeroHilos();
    }

    private int getNumeroHilos()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public void setRutaBD(String rutaBD)
    {
        this.rutaBD=rutaBD;
    }

    public void setNumeroHilos(int numeroHilos)
    {
        this.numeroHilos=numeroHilos;
    }

    public void indexar(String directorioImagenes,Caracteristica.Opcion[] opciones)
    {
        this.indexador=new ParallelIndexer(this.numeroHilos,this.rutaBD,directorioImagenes);
        this.agregarCaracteristicas(opciones, Caracteristica.getInstancia());
        this.indexador.run();
    }

    private void agregarCaracteristicas(Caracteristica.Opcion[] opciones,Caracteristica caracteristica)
    {
        Arrays.stream(opciones).forEach( opcion -> {
            indexador.addExtractor(caracteristica.getCaracteristica(opcion));
        });
    }

}
