package work.financeflowtracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CurrencyRatesResponse {
    private Meta meta;
    private List<Value> values;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    @Getter
    @Setter
    public static class Meta {
        private String symbol;
        private String interval;
        private String currency_base;
        private String currency_quote;
        private String type;

    }

    @Getter
    @Setter
    public static class Value {
        private String datetime;
        private String open;
        private String high;
        private String low;
        private String close;

    }
}