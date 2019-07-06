package thepublictransport.schildbach.pte.service.framework.cache

import org.springframework.cache.annotation.CacheEvict

object CacheManagement {

    @CacheEvict(value = ["requests"], allEntries = true)
    fun discardAllCache() {
        System.out.println("Cache fully cleared!")
    }
}