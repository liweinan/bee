package net.bluedash.bee.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 12 21 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@DiscriminatorValue("dp")
public class DisplayPosition extends Setting {

    public static long POS_LIST = 0x1;
    public static long POS_ENTITY = 0x10;

    @NotNull
    @Column(nullable = false)
    private Long pos;

    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }
}
