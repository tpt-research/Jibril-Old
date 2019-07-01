package thepublictransport.schildbach.pte.service.framework.tripoptions

import thepublictransport.schildbach.pte.NetworkProvider
import thepublictransport.schildbach.pte.dto.Product
import thepublictransport.schildbach.pte.dto.TripOptions

object TripOptionResolver {

    fun optionBuilder(accessibility: String, optimization: String, walkspeed: String): TripOptions {
        val product_finalized : Set<Product> = Product.ALL
        var accessibility_finalized : NetworkProvider.Accessibility = NetworkProvider.Accessibility.NEUTRAL
        var optimization_finalized : NetworkProvider.Optimize = NetworkProvider.Optimize.LEAST_DURATION
        var walkspeed_finalized: NetworkProvider.WalkSpeed = NetworkProvider.WalkSpeed.NORMAL

        if (accessibility == "LIMITED")
            accessibility_finalized = NetworkProvider.Accessibility.LIMITED

        if (accessibility == "BARRIER_FREE")
            accessibility_finalized = NetworkProvider.Accessibility.BARRIER_FREE

        if (optimization == "LEAST_CHANGES")
            optimization_finalized = NetworkProvider.Optimize.LEAST_CHANGES


        if (optimization == "LEAST_WALKING")
            optimization_finalized = NetworkProvider.Optimize.LEAST_WALKING

        if (walkspeed == "SLOW")
            walkspeed_finalized = NetworkProvider.WalkSpeed.SLOW

        if (walkspeed == "FAST")
            walkspeed_finalized = NetworkProvider.WalkSpeed.FAST


        return TripOptions(product_finalized, optimization_finalized, walkspeed_finalized, accessibility_finalized, null)
    }
}