package dmit2015.faces;

import dmit2015.restclient.Todo;
import dmit2015.restclient.TodoMpRestClient;

import jakarta.json.JsonObject;
import lombok.Getter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("currentTodoCreateView")
@RequestScoped
public class TodoCreateView {

    @Inject
    @RestClient
    private TodoMpRestClient _todoMpRestClient;

    @Getter
    private Todo newTodo = new Todo();

    public String onCreateNew() {
        String nextPage = null;
        try {
            JsonObject responseBody = _todoMpRestClient.create(newTodo);
            String documentKey = responseBody.getString("name");
            newTodo = new Todo();
            Messages.addFlashGlobalInfo("Create was successful. {0}", documentKey);
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful. {0}", e.getMessage());
        }
        return nextPage;
    }

}