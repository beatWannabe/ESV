package test.websheet;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.InvalidPathException;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author pdgomezl
 */
@Named(value = "streamUrl")
@SessionScoped
public class FileProperties implements Serializable {

    private String urlFile;
    private InputStream fileIo;

    /**
     * Creates a new instance of StreamUrl
     */
    public FileProperties() {
    }

    public void upload(FileUploadEvent event) throws IOException, SQLException, InterruptedException {
        fileIo = null;
        urlFile = null;
        if (event.getFile() != null) {
            UploadedFile file = event.getFile();
            try {
                fileIo = file.getInputstream();
                redirect();
            } catch (OutOfMemoryError | ArrayIndexOutOfBoundsException | IllegalAccessError | InvalidPathException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void redirect() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/faces/View.xhtml");
    }

    public String getUrlFile() {
        return urlFile/* = "C:\\Users\\pdgomezl\\Desktop\\Libro1.xlsx"*/;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public InputStream getFileIo() {
        return fileIo;
    }

    public void setFileIo(InputStream fileIo) {
        this.fileIo = fileIo;
    }

}
