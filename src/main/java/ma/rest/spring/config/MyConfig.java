package ma.rest.spring.config;
import org.springframework.context.annotation.*;import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;import ma.rest.spring.web.CompteRestJaxRSAPI;
/** Jersey reste un filtre pour laisser la console H2 accessible. */
@Configuration
public class MyConfig {
    @Bean public ResourceConfig resourceConfig(){return new ResourceConfig().register(CompteRestJaxRSAPI.class).property(ServletProperties.FILTER_FORWARD_ON_404,true);}
}
