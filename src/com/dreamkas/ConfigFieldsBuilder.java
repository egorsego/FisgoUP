package com.dreamkas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class ConfigFieldsBuilder {

    public Map<String, String> filterConfigFields(ArrayList<ArrayList<String>> config) {
        Map<String, String> mapFilterConfigFields = new HashMap<>();
        for (ArrayList<String> field : config) {
           mapFilterConfigFields.put(field.get(0), field.get(2));
        }
        return mapFilterConfigFields;
    }

}

