package springtest111.restserviceconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Value(Long id, String quote) { }