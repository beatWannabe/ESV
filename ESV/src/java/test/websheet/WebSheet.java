package test.websheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.inject.Inject;
import org.tiefaces.components.websheet.TieWebSheetBean;

/**
 *
 * @author pdgomezl
 */
/*
@Named(value = "webSheetBean")
@ViewScoped
 */
public class WebSheet extends TieWebSheetBean {

    /* private String rutaDelArchivo;*/
    private InputStream stream;
    @Inject
    private FileProperties streamUrl;

    @Override
    public void initialLoad() {
        if (streamUrl.getUrlFile() != null) {
            File file = new File(streamUrl.getUrlFile());
            try {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                System.out.println("Ocurrio un error al intentar leer la ruta el archivo");
                System.out.println(ex.getMessage());
            }
            //InputStream stream = this.getClass().getClassLoader().getResourceAsStream("websheet/Libro1.xlsx");
        } else if (streamUrl.getFileIo() != null) {
            try {
                stream = streamUrl.getFileIo();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al intentar leer el archivo");
                System.out.println(e.getMessage());
            }
        }
        loadWebSheet(stream);
    }

    /*
    public String getRutaDelArchivo() {
        return rutaDelArchivo;
    }

    public void setRutaDelArchivo(String rutaDelArchivo) {
        this.stream = null;
        this.rutaDelArchivo = rutaDelArchivo;
        initialLoad();
    }
     */
}
