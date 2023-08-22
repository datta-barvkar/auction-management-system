package org.scsmksn.npl.auction.common.template;

import org.apache.camel.ProducerTemplate;
import org.scsmksn.npl.auction.common.exceptions.AuctionWebErrorException;
import org.scsmksn.npl.auction.common.utils.ClientErrorHelper;
import org.scsmksn.npl.auction.model.Params;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
public class WebCamelRequestTemplate {

    @Autowired
    private ProducerTemplate template;

    public <T> T request(final String endpoint, final Class<T> tClass) {
        try {
            return requestWithBody(endpoint, null, tClass);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public <T> T requestWithBody(final String endpoint, final Object body, final Class<T> tClass) {
        try {
            return requestWithBodyAndHeaders(endpoint, body, null, tClass);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public void sendBody(final String endpoint, final Object body) {
        try {
            template.sendBody(endpoint, body);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> requestList(final String endpoint) {
        try {
            final List<?> list = requestWithBody(endpoint, null, List.class);
            return list.stream().map(obj -> (T) obj).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> requestListWithBody(final String endpoint, final Object body) {
        try {
            final List<?> list = requestWithBody(endpoint, body, List.class);
            return list.stream().map(obj -> (T) obj).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> requestListWithHeaders(final String endpoint, final Params params) {
        try {
            final List<?> list = requestWithHeaders(endpoint, params, List.class);
            return list.stream().map(obj -> (T) obj).collect(Collectors.toList());
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public <T> List<T> requestListWithHeader(final String endpoint, final String header, final Object headerValue) {
        try {
            final Params params = Params.getParamsBuilder().putJpaQueryParam(header, headerValue).build();
            return requestListWithHeaders(endpoint, params);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public <T> T requestWithBodyAndHeaders(final String endpoint, final Object body, final Params params
            , final Class<T> tClass) {
        try {
            return template.requestBodyAndHeaders(endpoint, body, params, tClass);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public <T> T requestWithHeaders(final String endpoint, final Params params, final Class<T> tClass) {
        try {
            return requestWithBodyAndHeaders(endpoint, null, params, tClass);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public void sendHeaders(final String endpoint, final Params params) {
        try {
            sendBodyAndHeaders(endpoint, null, params);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public void sendBodyAndHeaders(final String endpoint, final Object body, final Params params) {
        try {
            template.sendBodyAndHeaders(endpoint, body, params);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public <T> T requestWithBodyAndHeader(final String endpoint, final Object body, final String header
            , final Object headerValue, final Class<T> tClass) {
        try {
            final Params params = Params.getParamsBuilder().putJpaQueryParam(header, headerValue).build();
            return requestWithBodyAndHeaders(endpoint, body, params, tClass);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public void sendBodyAndHeader(final String endpoint, final Object body, final String header
            , final Object headerValue) {
        try {
            template.sendBodyAndHeader(endpoint, body, header, headerValue);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    public void sendHeader(final String endpoint, final String header, final Object headerValue) {
        try {
            sendBodyAndHeader(endpoint, null, header, headerValue);
        } catch (HttpClientErrorException ex) {
            throw ClientErrorHelper.convertToAuctionWebErrorException(ex);
        } catch (Exception ex) {
            throw new AuctionWebErrorException(ex.getMessage(), INTERNAL_SERVER_ERROR
                    , INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
