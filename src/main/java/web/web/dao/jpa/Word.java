package web.web.dao.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "word")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Word {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "wordId", unique = true, nullable = false)
    private Integer wordId;
    @Column(name = "word", nullable = false, length = 100)
    private String word;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siteId")
    private String site;
}
