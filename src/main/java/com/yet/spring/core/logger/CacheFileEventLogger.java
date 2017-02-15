package com.yet.spring.core.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RStreltsov on 13.02.2017.
 */
import com.yet.spring.core.bean.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    @Value("${cache.size:5}")
    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;

    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @PreDestroy
    public void destroy() {
        if ( ! cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        System.out.println("CacheFileEventLogger eventMess="+event.getMessage());
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        //cache.stream().forEach(super::logEvent);
        for(Object ev : cache.toArray()) {
            super.logEvent((Event)ev);
        }
    }
}