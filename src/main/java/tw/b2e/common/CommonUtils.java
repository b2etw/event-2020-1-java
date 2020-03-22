package tw.b2e.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class CommonUtils {



  public static RestTemplate getTrustRestTemplate () {
    try {
      TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
      SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
          .build();
      SSLConnectionSocketFactory connectionSocketFactory =
          new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

      HttpClientBuilder httpClientBuilder = HttpClients.custom();
      httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
      CloseableHttpClient httpClient = httpClientBuilder.build();
      HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
      factory.setHttpClient(httpClient);
      factory.setConnectTimeout(10 * 1000);
      factory.setReadTimeout(30 * 1000);

      final RestTemplate restTemplate = new RestTemplate(factory);
      MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
      messageConverter.setObjectMapper(Jackson2ObjectMapperBuilder.json()
          .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
          .featuresToDisable(SerializationFeature.INDENT_OUTPUT)
          .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
          .featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
          .build());
      messageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
      restTemplate.getMessageConverters().add(messageConverter);

      return restTemplate;
    } catch (Exception e) {
      throw new RuntimeException("createTrustRestTemplateError", e);

    }

  }
}
