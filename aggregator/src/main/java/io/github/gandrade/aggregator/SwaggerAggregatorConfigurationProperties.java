package io.github.gandrade.aggregator;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "swagger")
public class SwaggerAggregatorConfigurationProperties {

    private List<SwaggerApplication> applications;

    public List<SwaggerApplication> getApplications() {
        return applications;
    }

    public void setApplications(List<SwaggerApplication> applications) {
        this.applications = applications;
    }

    public static final class SwaggerApplication {

        private String name;
        private String version;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
