package petstore.model;
import java.util.List;

public class Pet {
    public long id;
    public String name;
    public String status;
    public Category category;
    public List<Tag> tags;
    public List<String> photoUrls;

    
    public Pet(long id, String name, String status, Category category, List<Tag> tags, List<String> photoUrls) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.category = category;
        this.tags = tags;
        this.photoUrls = photoUrls;
    }

    public Pet(String name, List<String> photoUrls) {
        this.name = name;
        this.photoUrls = photoUrls;
    }
}
