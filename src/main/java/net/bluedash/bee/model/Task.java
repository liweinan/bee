package net.bluedash.bee.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 10 29 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Date labelChangedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLabelChangedAt() {
        return labelChangedAt;
    }

    public void setLabelChangedAt(Date labelChangedAt) {
        this.labelChangedAt = labelChangedAt;
    }
}
