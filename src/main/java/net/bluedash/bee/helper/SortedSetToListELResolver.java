package net.bluedash.bee.helper;

import javax.el.ELContext;
import javax.el.ListELResolver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 12 26 2012
 * http://stackoverflow.com/questions/9327479/how-do-i-use-a-java-util-set-with-uidata-in-jsf-specifically-hdatatable/9327572#9327572
 * http://stackoverflow.com/questions/3222669/cant-access-lazy-annotated-but-initialized-hibernate-collection-from-jsf2
 *
 * Set is a good way to avoid duplications, but we also want to keep the order of the data.
 * So we use TreeSet to store data that have the above two requirements(Such as tags of a task).
 * However, JSF uses List to display data on page view, so we use this resolver to convert the data.
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
public class SortedSetToListELResolver extends ListELResolver {
    public static final String KEY_PROPERTY = "setToList";

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        if (base instanceof Set<?> && KEY_PROPERTY.equals(property)) {
            context.setPropertyResolved(true);
            List list = new ArrayList<Object>((Set<?>) base);
            Collections.sort(list);
            return list;
        }

        return super.getValue(context, base, property);
    }

}
