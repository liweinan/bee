package net.bluedash.bee.helper;

import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: weinanli
 * Date: 1/7/13
 * Time: 2:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class FlashUtil {
    public static javax.faces.context.Flash scope (){
        return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }
}
