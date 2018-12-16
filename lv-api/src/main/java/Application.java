import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
@SpringBootApplication
@ServletComponentScan(value = "com.jarasy.lv.api")
@MapperScan(basePackages = {"com.jarasy.lv.api.mapper"})
@ComponentScan(value = {"com.jarasy.lv.api"})
public class Application {

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(900000);
        httpRequestFactory.setConnectTimeout(900000);
        httpRequestFactory.setReadTimeout(900000);

       /* RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getInterceptors().add(new com.cyyz.spt.springbase.util.RequestInterceptor());
        return restTemplate;*/
        return new RestTemplate(httpRequestFactory);
    }


    @Bean(name = "unLoadBalancedrestTemplate")
    public RestTemplate unLoadBalancedrestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(900000);
        httpRequestFactory.setConnectTimeout(900000);
        httpRequestFactory.setReadTimeout(900000);

        /*RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getInterceptors().add(new com.cyyz.spt.springbase.util.RequestInterceptor());
        return restTemplate;*/
        return new RestTemplate(httpRequestFactory);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
