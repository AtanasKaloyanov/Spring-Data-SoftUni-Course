package hasEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(targetEntity = Article.class, mappedBy = "author",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articleList;

    public User() {
        this.articleList = new ArrayList<>();
    }

    public User(String name) {
        this();
        this.name = name;
    }

    public void addArticle(Article article) {
        this.articleList.add(article);
    }
}
