package cz.filipklimes.bachelor.inspection.metadata;

import com.google.gson.Gson;

/**
 * @author klimesf
 */
abstract public class Metadata implements SerializableMetadata {

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
