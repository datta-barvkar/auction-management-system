package org.scsmksn.npl.auction.model;

import org.springframework.util.NumberUtils;

import java.util.HashMap;
import java.util.Map;

public class Params extends HashMap<String, Object> {

    public static final String JPA_QUERY_PARAMS = "JpaQueryParams";

    private Params() {
    }

    private Params(final Map<String, Object> params) {
        if (params != null) {
            this.putAll(params);
        }
    }

    public Object getJpaQueryParam(final String key) {
        return getJpaQueryParams().get(key);
    }

    public String getJpaQueryParamString(final String key) {
        final Object o = getJpaQueryParams().get(key);
        return o instanceof String ? (String) o : String.valueOf(o);
    }

    public Long getJpaQueryParamLong(final String key) {
        final Object o = getJpaQueryParams().get(key);
        return o instanceof Long ? (Long) o : NumberUtils.parseNumber(getJpaQueryParamString(key), Long.class);
    }

    public void putJpaQueryParam(final String key, final Object value) {
        getJpaQueryParams().put(key, value);
    }

    private Map<String, Object> getJpaQueryParams() {
        return safeGet(JPA_QUERY_PARAMS);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> safeGet(final String key) {
        Map<String, Object> returnVal = (Map<String, Object>) get(key);
        if (returnVal == null) {
            returnVal = new HashMap<>();
            put(key, returnVal);
        }
        return returnVal;
    }

    private void setJpaQueryParams(final Map<String, Object> jpaQueryParams) {
        if (jpaQueryParams != null) {
            put(JPA_QUERY_PARAMS, jpaQueryParams);
        }
    }

    public static ParamsBuilder getParamsBuilder() {
        return new ParamsBuilder();
    }

    public static class ParamsBuilder {

        private Params params;
        private ParamsBuilder() {
            params = new Params();
        }

        public ParamsBuilder put(final String key, final Object value) {
            params.put(key, value);
            return this;
        }

        public ParamsBuilder putAll(final Map<String, Object> params) {
            if (params != null) {
                this.params.putAll(params);
            }
            return this;
        }

        public ParamsBuilder putJpaQueryParam(final String key, final Object value) {
            params.putJpaQueryParam(key, value);
            return this;
        }

        public ParamsBuilder putAllJpaQueryParams(final Map<String, Object> jpaQueryParams) {
            this.params.getJpaQueryParams().putAll(jpaQueryParams);
            return this;
        }

        public Params build() {
            return params;
        }
    }
}
