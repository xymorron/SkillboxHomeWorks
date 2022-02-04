package Model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "_site")
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "enum")
    private IndexingState status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date status_time;

    @Column(name = "last_error", columnDefinition = "text")
    private String lastError;

    private String url;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IndexingState getStatus() {
        return status;
    }

    public void setStatus(IndexingState status) {
        this.status = status;
    }

    public Date getStatus_time() {
        return status_time;
    }

    public void setStatus_time(Date status_time) {
        this.status_time = status_time;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
