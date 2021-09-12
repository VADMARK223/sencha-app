package senchaserver.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Markitanov Vadim
 * @since 12.09.2021
 */
@Configuration
@EnableCaching
public class AppConfig {
    @Bean
    public Config config() {
        final Config result = new Config();
        MapConfig userMapConfig = new MapConfig();
        userMapConfig.setTimeToLiveSeconds(5);
        result.getMapConfigs().put("userCache", userMapConfig);

        MapConfig personnelMapConfig = new MapConfig();
        personnelMapConfig.setTimeToLiveSeconds(60000);
        result.getMapConfigs().put("personnelCache", personnelMapConfig);

        return result;
    }
}
