package de.schildbach.pte.service.framework.source

import de.schildbach.pte.*

class SourceResolver {

    fun getSource(source: String): NetworkProvider {
        var provider: NetworkProvider = RtProvider()

        provider = getSourceFree(source)

        /*
        TODO: Care about nonfree providers
        if (provider.javaClass == RtProvider().javaClass)
            provider = getSourceNonfree(source)
        */

        return provider;
    }

    fun getSourceFree(source: String): NetworkProvider {
        when (source) {
            "KVV" -> {
                return KvvProvider()
            }
            "AVV" -> {
                return AvvProvider()
            }
            "Bayern" -> {
                return BayernProvider()
            }
            "BSVAG" -> {
                return BsvagProvider()
            }
            "CMTA" -> {
                return CmtaProvider()
            }
            "Ding" -> {
                return DingProvider()
            }
            "Dubai" -> {
                return DubProvider()
            }
            "Eireann" -> {
                return EireannProvider()
            }
            "GVH" -> {
                return GvhProvider()
            }
            "Linz" -> {
                return LinzProvider()
            }
            "Mersey" -> {
                return MerseyProvider()
            }
            "MVG" -> {
                return MvgProvider()
            }
            "MVV" -> {
                return MvvProvider()
            }
            "Negentwee" -> {
                return NegentweeProvider(NegentweeProvider.Language.EN_GB)
            }
            "NS" -> {
                return NsProvider()
            }
            "NVBW" -> {
                return NvbwProvider()
            }
            "Poland" -> {
                return PlProvider()
            }
            "RTA_Chicago" -> {
                return RtaChicagoProvider()
            }
            "SBB" -> {
                return SbbProvider()
            }
            "STV" -> {
                return StvProvider()
            }
            "Sydney" -> {
                return SydneyProvider()
            }
            "TFI_Ireland" -> {
                return TfiProvider()
            }
            "TLEM" -> {
                return TlemProvider()
            }
            "VBL" -> {
                return VblProvider()
            }
            "VGN" -> {
                return VgnProvider()
            }
            "VMS" -> {
                return VmsProvider()
            }
            "VMV" -> {
                return VmvProvider()
            }
            "VRN" -> {
                return VrnProvider()
            }
            "VRR" -> {
                return VrrProvider()
            }
            "VRS" -> {
                return VrsProvider()
            }
            "VVM" -> {
                return VvmProvider()
            }
            "VVO" -> {
                return VvoProvider()
            }
            "VVS" -> {
                return VvsProvider()
            }
            "VVV" -> {
                return VvvProvider()
            }
            "Wien" -> {
                return WienProvider()
            }
        }

        return RtProvider()
    }

    fun getSourceNonfree(source: String): NetworkProvider {
        // TODO: Care about nonfree providers
        return RtProvider()
    }
}