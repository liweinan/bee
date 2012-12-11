package net.bluedash.bee.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 10 29 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    private Boolean timeTracked;

    public Boolean getTimeTracked() {
        return timeTracked;
    }

    public void setTimeTracked(Boolean timeTracked) {
        this.timeTracked = timeTracked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
