package com.ufrotest.data.imp;

import com.google.gson.Gson;
import com.ufrotest.data.out.IJsonHandler;

public class JsonHandler<DTO> implements IJsonHandler<DTO> {

    /**
     * Serializes an object using a GSon instance.
     * @param entity It's the entity to be serialized, it can only be of the generic type
     *               indicated in the instantiation or derivatives.
     * @return Returns a string with the content of the object serialized in JSon.
     */
    @Override
    public String serialize(DTO entity) {
        Gson gSon = new Gson();
        return gSon.toJson(entity);
    }

    /**
     * Deserializes an object using a GSon instance.
     * @param json Is the JSon string to deserialize.
     * @param entityClass Is the class to which the object to be deserialized belongs,
     *                    can only be of the generic type indicated in the
     *                    instantiation or derivatives.
     * @return Returns an instance of the generic type indicated in the instantiation
     * with the deserialized data.
     */
    @Override
    public DTO deserialize(String json, Class<? extends DTO> entityClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, entityClass);
    }
}
