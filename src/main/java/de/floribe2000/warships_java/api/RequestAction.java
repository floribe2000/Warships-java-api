package de.floribe2000.warships_java.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface RequestAction<T> {

    Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    T fetch();
}
