package net.bluedash.bee.model;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 10 29 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "track_time")
public class TrackTime implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    private Label label;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;

    // in milliseconds
    private long timeConsumed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
