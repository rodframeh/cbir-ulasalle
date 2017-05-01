package com.ulasalle.cbir;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Aplicacion
{

    public static void main(String[] args) throws IOException
    {
        IndexadorParalelo indexadorParalelo=new IndexadorParalelo();
        indexadorParalelo.setRutaBD("nombre_mi_bd");
        indexadorParalelo.indexar("/home/rodrigo/imagenes/",new Caracteristica.Opcion[]{Caracteristica.Opcion.CEDD});

        Buscador buscador=new Buscador();
        File imagen=new File("/home/rodrigo/imagenes/img1.jpg");

        Arrays.stream(buscador
                .buscar(
                        imagen,
                        "nombre_mi_bd",
                        30,
                        Caracteristica.getInstancia().getCaracteristica(Caracteristica.Opcion.CEDD)))
                .forEach(elemento->System.out.println(elemento));
    }

}
