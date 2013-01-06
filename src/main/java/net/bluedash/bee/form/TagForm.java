package net.bluedash.bee.form;

import net.bluedash.bee.model.Tag;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 1/6/13
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
@SessionScoped
@Named
public class TagForm implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag toTag() {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }
}
