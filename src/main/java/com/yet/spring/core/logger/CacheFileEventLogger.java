package com.yet.spring.core.logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RStreltsov on 13.02.2017.
 */
import com.yet.spring.core.bean.Event;

public class CacheFileEventLogger extends FileEventLogger {

    private Integer cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(Integer cacheSize, String filename) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    public void destroy() {
        if ( ! cache.isEmpty()) {
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
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