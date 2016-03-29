package beans;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadHandler")
public class FileUploadHandler {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            try {
                in = new BufferedInputStream(file.getInputstream());
                out = new BufferedOutputStream(new FileOutputStream(
                        new File(".." + File.separator + "download" + File.separator + file.getFileName())));
                int line;

                while ((line = in.read()) != -1) {
                    out.write(line);
                }

            } catch (IOException ex) {
                message = new FacesMessage("Upload error", "Ex: <" + ex.toString() + ">");
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException ex) {
                    message = new FacesMessage("CloseStream error", "Ex: <" + ex.toString() + ">");
                }
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
}
