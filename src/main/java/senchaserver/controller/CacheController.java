package senchaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Markitanov Vadim
 * @since 12.09.2021
 */
@RestController
@RequestMapping("/cache")
public class CacheController {
    private CacheManager cacheManager;

    @GetMapping(value = "/clear")
    public ResponseEntity<String> clear() {
        for (String name : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(name);
            Objects.requireNonNull(cache).clear();
        }

        return ResponseEntity.ok("Caches clear.");
    }

    @GetMapping(value = "/list")
    public ResponseEntity<String> list() {
        List<String> result = new ArrayList<>(cacheManager.getCacheNames());

        return ResponseEntity.ok("Caches list: " + result);
    }

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
