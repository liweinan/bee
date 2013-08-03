package net.bluedash.bee.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 10 29 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "bee_statuses")
public class Status {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn
    private Task task;

    @NotNull
    @Column(nullable = false)
    private Boolean timeTracked;

    @NotNull
    @Column(nullable = false)
    private Boolean global = false;

    private String styleSheet;

    public Boolean getTimeTracked() {
        return timeTracked;
    }

    public void setTimeTracked(Boolean timeTracked) {
        this.timeTracked = timeTracked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }

    public String getStyleSheet() {
        return styleSheet;
    }

    public void setStyleSheet(String styleSheet) {
        this.styleSheet = styleSheet;
    }
}
