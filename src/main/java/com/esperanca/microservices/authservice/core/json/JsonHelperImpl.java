package com.esperanca.microservices.authservice.core.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonHelperImpl implements JsonHelper {

  private final Gson gson;

  @Override
  public String getValueFromKey(String json, String key) {
    final JsonObject jsonObject = this.gson.fromJson(json, JsonObject.class);
    return jsonObject.get(key).getAsString();
  }
}
