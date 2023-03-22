package dmit2015.faces;

import dmit2015.restclient.Todo;
import dmit2015.restclient.TodoMpRestClient;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;

@Named("currentTodoEditView")
@ViewScoped
public class TodoEditView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    @RestClient
    private TodoMpRestClient _todoMpRestClient;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private String editId;

    @Getter
    private Todo existingTodo;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            if (editId != null) {
                existingTodo = _todoMpRestClient.findById(editId);
                if (existingTodo == null) {
                    Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index.xhtml");
                }
            } else {
                Faces.redirect(Faces.getRequestURI().substring(0, Faces.getRequestURI().lastIndexOf("/")) + "/index.xhtml");
            }
        }
    }

    public String onUpdate() {
        String nextPage = null;
        try {
            _todoMpRestClient.update(editId, existingTodo);
            Messages.addFlashGlobalInfo("Update was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Update was not successful.");
        }
        return nextPage;
    }
}