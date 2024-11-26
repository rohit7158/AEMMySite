package com.mysite.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;
import java.util.stream.Collectors;

@Model(
    adaptables = {Resource.class, SlingHttpServletRequest.class},
    resourceType = "mysite/components/key-value",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class KeyValueModel {

    @ChildResource(name = "keyValuePairs")
    private List<Resource> keyValuePairs;

    public List<KeyValue> getKeyValuePairs() {
        return keyValuePairs.stream()
                .map(resource -> new KeyValue(
                        resource.getValueMap().get("key", ""),
                        resource.getValueMap().get("value", "")
                ))
                .collect(Collectors.toList());
    }

    public static class KeyValue {
        private final String key;
        private final String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}