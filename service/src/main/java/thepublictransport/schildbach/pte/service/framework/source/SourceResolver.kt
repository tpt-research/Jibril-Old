package thepublictransport.schildbach.pte.service.framework.source

import thepublictransport.schildbach.pte.*
import thepublictransport.schildbach.pte.service.framework.api.APIKeys


class SourceResolver {

    val navitia_apikey = APIKeys.NAVITIA_APIKEY

    fun getSource(source: String): NetworkProvider {
        val provider: NetworkProvider = getFirstSources(source)

        return provider
    }

    fun getFirstSources(source: String): NetworkProvider {
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

        return getSourceNonfree(source)
    }

    fun getSourceNonfree(source: String): NetworkProvider {
        when (source) {
            "BVG" -> {
                return BvgProvider("{\"aid\":\"1Rxs112shyHLatUX4fofnmdxK\",\"type\":\"AID\"}")
            }
            "DB" -> {
                return DbProvider("{\"type\":\"AID\",\"aid\":\"n91dB8Z77MLdoR0K\"}", "bdI8UVj40K5fvxwf".toByteArray(Charsets.UTF_8))
            }
            "VBB" -> {
                return VbbProvider("{\"type\":\"AID\",\"aid\":\"hafas-vbb-apps\"}", "RCTJM2fFxFfxxQfI".toByteArray(Charsets.UTF_8))
            }
            "INVG" -> {
                return InvgProvider("{\"type\":\"AID\",\"aid\":\"GITvwi3BGOmTQ2a5\"}", "ERxotxpwFT7uYRsI".toByteArray(Charsets.UTF_8))
            }
            "NASA" -> {
                return NasaProvider("{\"aid\":\"nasa-apps\",\"type\":\"AID\"}")
            }
            "NVV_RMV" -> {
                return NvvProvider("{\"type\":\"AID\",\"aid\":\"Kt8eNOH7qjVeSxNA\"}")
            }
            "VGS" -> {
                return VgsProvider("{\"type\":\"AID\",\"aid\":\"51XfsVqgbdA6oXzHrx75jhlocRg6Xe\"}", "HJtlubisvxiJxss".toByteArray(Charsets.UTF_8))
            }
            "VMT" -> {
                return VmtProvider("{\"aid\":\"vj5d7i3g9m5d7e3\",\"type\":\"AID\"}")
            }
            "OEBB" -> {
                return OebbProvider("{\"type\":\"AID\",\"aid\":\"OWDL4fE4ixNiPBBm\"}")
            }
            "ZVV" -> {
                return ZvvProvider("{\"type\":\"AID\",\"aid\":\"hf7mcf9bv3nv8g5f\"}")
            }
            "SNCB" -> {
                return SncbProvider("{\"type\":\"AID\",\"aid\":\"sncb-mobi\"}")
            }
            "LU" -> {
                return LuProvider("{\"type\":\"AID\",\"aid\":\"Aqf9kNqJLjxFx6vv\"}")
            }
            "DSB" -> {
                return DsbProvider("{\"type\":\"AID\",\"aid\":\"irkmpm9mdznstenr-android\"}")
            }
            "SE" -> {
                return SeProvider("{\"type\":\"AID\",\"aid\":\"h5o3n7f4t2m8l9x1\"}")
            }
        }
        return getSourceNavitia(source)
    }

    fun getSourceNavitia(source: String): NetworkProvider {
        when (source) {
            "Finland" -> {
                return FinlandProvider(navitia_apikey)
            }
            "Italy" -> {
                return ItalyProvider(navitia_apikey)
            }
            "Poland_Navitia" -> {
                return PlNavitiaProvider(navitia_apikey)
            }
            "Paris" -> {
                return ParisProvider(navitia_apikey)
            }
            "France_Southwest" -> {
                return FranceSouthWestProvider(navitia_apikey)
            }
            "France_Southeast" -> {
                return FranceSouthEastProvider(navitia_apikey)
            }
            "France_Northwest" -> {
                return FranceNorthWestProvider(navitia_apikey)
            }
            "France_Northeast" -> {
                return FranceNorthEastProvider(navitia_apikey)
            }
            "Spain" -> {
                return SpainProvider(navitia_apikey)
            }
            "Ghana" -> {
                return GhanaProvider(navitia_apikey)
            }
            "Oregon" -> {
                return OregonProvider(navitia_apikey)
            }
            "Ontario" -> {
                return OntarioProvider(navitia_apikey)
            }
            "Quebec" -> {
                return QuebecProvider(navitia_apikey)
            }
            "BritishColumbia" -> {
                return BritishColumbiaProvider(navitia_apikey)
            }
            "Nicaragua" -> {
                return NicaraguaProvider(navitia_apikey)
            }
            "Australia" -> {
                return AustraliaProvider(navitia_apikey)
            }
            "NewZealand" -> {
                return NzProvider(navitia_apikey)
            }
        }
        return RtProvider()
    }
}