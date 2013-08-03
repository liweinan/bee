package net.bluedash.bee.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 10 29 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name = "bee_packages")
public class Package {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    private Date labelChangedAt;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="bee_package_tag", inverseJoinColumns = {@JoinColumn(name = "ref_tag")})
    private Set<Tag> tags = new TreeSet<Tag>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User assignee;

    @NotNull
    @Column(nullable = false)
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLabelChangedAt() {
        return labelChangedAt;
    }

    public void setLabelChangedAt(Date labelChangedAt) {
        this.labelChangedAt = labelChangedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
