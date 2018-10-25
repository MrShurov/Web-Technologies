package web.web.dao.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "site")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Site {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "siteId", unique = true, nullable = false)
    private Integer siteId;
    @Column(name = "url", nullable = false, length = 100)
    private String url;

    public Site(String url) {
        this.url = url;
    }
}
