package com.example.binchecker.core.navigation

interface BinCheckerDestination {
    val route: String
}

object CheckCardBin : BinCheckerDestination {
    override val route = "CheckCardBin"
}

object RequestHistory : BinCheckerDestination {
    override val route = "RequestHistory"
}