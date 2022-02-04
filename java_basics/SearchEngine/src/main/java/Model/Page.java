package Model;

import javax.persistence.*;

@Table(name = "_page", indexes = {
        @Index(name = "idx_page_path", columnList = "path")
})
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, columnDefinition = "text")
    private String path;

    private int code;

    @Column(columnDefinition = "mediumtext")
    private String content;

    @Column(name = "site_id")
    private int siteId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
