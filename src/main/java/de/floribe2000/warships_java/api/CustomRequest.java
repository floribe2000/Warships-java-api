package de.floribe2000.warships_java.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static de.floribe2000.warships_java.api.IRequestAction.GSON;

/**
 * A class that allows custom requests.
 * There is no syntax checking, the user is responsible to make sure that his requests params are valid!
 */
public class CustomRequest implements IRequest<CustomRequest> {

    private final Logger LOG = LoggerFactory.getLogger("CustomRequest");

    private Region region = null;

    private String path = null;

    private Map<String, Set<CustomField>> fields = new HashMap<>();

    @Override
    public CustomRequest region(Region region) {
        return null;
    }

    public CustomRequest addField(CustomField... fields) {
        for (CustomField cf : fields) {
            this.fields.computeIfAbsent(cf.type, k -> new HashSet<>());
            this.fields.get(cf.type).add(cf);
        }
        return this;
    }

    public CustomRequest path(String path) {
        this.path = path;
        return this;
    }

    public Object fetch(Object pojo) {
        String url = region.getBaseURL() + path + ApiBuilder.getApiKeyAsParam() + processFields();
        Object result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            result = GSON.fromJson(reader, pojo.getClass());
        } catch (Exception e) {
            try {
                result = pojo.getClass().newInstance();
            } catch (Exception ie) {
                result = null;
                LOG.error("Error", ie);
            }
        }
        return result;
    }

    private String processFields() {
        StringBuilder strb = new StringBuilder();
        for (Map.Entry<String, Set<CustomField>> entry : fields.entrySet()) {
            strb.append("&").append(entry.getKey()).append("=");
            String prefix = "";
            for (CustomField cf : entry.getValue()) {
                strb.append(prefix).append(cf.name);
                prefix = ",";
            }
        }
        return strb.toString();
    }

    @AllArgsConstructor
    public static class CustomField {
        String name;

        String type;
    }
}
