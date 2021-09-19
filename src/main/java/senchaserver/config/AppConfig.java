package senchaserver.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${cache.time-to-live-seconds.user}")
    private int userCacheTimeToLive;

    @Value("${cache.time-to-live-seconds.personnel}")
    private int personnelCacheTimeToLive;

    @Bean
    public Config config() {
        final Config result = new Config();
        MapConfig userMapConfig = new MapConfig();
        userMapConfig.setTimeToLiveSeconds(userCacheTimeToLive);
        result.getMapConfigs().put("userCache", userMapConfig);

        MapConfig personnelMapConfig = new MapConfig();
        personnelMapConfig.setTimeToLiveSeconds(personnelCacheTimeToLive);
        result.getMapConfigs().put("personnelCache", personnelMapConfig);

        return result;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        Config config = new Config();
//        config.getCPSubsystemConfig().setCPMemberCount(3); // TODO разбраться с этим
        return Hazelcast.newHazelcastInstance(config);
    }
}
