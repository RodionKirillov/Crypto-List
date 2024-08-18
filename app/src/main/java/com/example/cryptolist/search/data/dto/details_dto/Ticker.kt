package com.example.cryptolist.search.data.dto.details_dto

data class Ticker(
    val base: String,
    val bid_ask_spread_percentage: Double,
    val coin_id: String,
    val converted_last: com.example.cryptolist.search.data.dto.details_dto.ConvertedLast,
    val converted_volume: com.example.cryptolist.search.data.dto.details_dto.ConvertedVolume,
    val is_anomaly: Boolean,
    val is_stale: Boolean,
    val last: Double,
    val last_fetch_at: String,
    val last_traded_at: String,
    val market: com.example.cryptolist.search.data.dto.details_dto.Market,
    val target: String,
    val target_coin_id: String,
    val timestamp: String,
    val token_info_url: Any,
    val trade_url: String,
    val trust_score: String,
    val volume: Double
)