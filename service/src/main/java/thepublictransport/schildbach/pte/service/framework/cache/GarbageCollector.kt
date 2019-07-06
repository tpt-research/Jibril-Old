package thepublictransport.schildbach.pte.service.framework.cache

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
object GarbageCollector {

    @Scheduled(fixedDelay = 300000, initialDelay = 1000)
    fun emptyCachePeriodically() {
        CacheManagement.discardAllCache()
    }
}