package de.floribe2000.warships_java.api;

import java.util.Set;

/**
 * An interface to define a class as request class.
 */
public interface Request {

    default <T extends IResponseFields> String buildFieldString(String type, Set<T> fields) {
        StringBuilder strb = new StringBuilder();
        if (fields != null && fields.size() > 0) {
            strb.append("&").append(type).append("=");
            String prefix = "";
            for (IResponseFields field : fields) {
                strb.append(prefix);
                strb.append(field.retrieveKey());
                prefix = ",";
            }
        }
        return strb.toString();
    }
}
