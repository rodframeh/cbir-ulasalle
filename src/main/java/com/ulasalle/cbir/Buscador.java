package com.ulasalle.cbir;

import net.semanticmetadata.lire.builders.DocumentBuilder;
import net.semanticmetadata.lire.imageanalysis.features.*;
import net.semanticmetadata.lire.searchers.GenericFastImageSearcher;
import net.semanticmetadata.lire.searchers.ImageSearchHits;
import net.semanticmetadata.lire.searchers.ImageSearcher;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Buscador
{

    public String[] buscar(File imagen, String rutaBD, int numeroResultados, Class<? extends GlobalFeature> caracteristica)
    {
        String[] imagenes=null;
        try {
            IndexReader lector= DirectoryReader.open(FSDirectory.open(Paths.get(rutaBD)));
            ImageSearcher buscadorImagenes=new GenericFastImageSearcher(numeroResultados, caracteristica);
            ImageSearchHits resultados=buscadorImagenes.search(ImageIO.read(imagen),lector);
            imagenes=new String[resultados.length()];
            for(int i=0;i<resultados.length();i++)
            {
                imagenes[i]=lector.document(resultados.documentID(i)).getValues(DocumentBuilder.FIELD_NAME_IDENTIFIER)[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagenes;
    }

}
