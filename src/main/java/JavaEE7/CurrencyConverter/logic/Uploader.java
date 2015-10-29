package JavaEE7.CurrencyConverter.logic;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by alex on 10/26/15.
 */
@Named
@SessionScoped
public class Uploader implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(Uploader.class.getName());

    @Getter
    @Setter
    private Part file;

    public void upload() {
        List<String> lines = new ArrayList<>();
        try {
            if (file != null) {
                Scanner scanner = new Scanner(file.getInputStream(),
                        "UTF-8").useDelimiter("\\n");
                while (scanner.hasNext()) {
                    lines.add(scanner.next());
                }
                new LineParser().parse(lines);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Upload successfully ended!"));
            }
        } catch (IOException | NoSuchElementException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Upload failed!"));
        }
    }
}
