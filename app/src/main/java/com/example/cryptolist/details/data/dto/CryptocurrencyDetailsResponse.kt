package com.example.cryptolist.details.data.dto

import com.example.cryptolist.search.data.dto.Response
import com.google.gson.annotations.SerializedName

data class CryptocurrencyDetailsResponse(
    @SerializedName("additional_notices") val additional_notices: List<Any>,
    @SerializedName("asset_platform_id") val asset_platform_id: Any,
    @SerializedName("block_time_in_minutes") val block_time_in_minutes: Int,
    val categories: List<String>,
    @SerializedName("community_data") val community_data: com.example.cryptolist.details.data.dto.crypto_details_response.CommunityData,
    @SerializedName("country_origin") val country_origin: String,
    val description: com.example.cryptolist.details.data.dto.crypto_details_response.Description,
    @SerializedName("detail_platforms") val detail_platforms: com.example.cryptolist.details.data.dto.crypto_details_response.DetailPlatforms,
    @SerializedName("developer_data") val developer_data: com.example.cryptolist.details.data.dto.crypto_details_response.DeveloperData,
    @SerializedName("genesis_date") val genesis_date: String,
    @SerializedName("hashing_algorithm") val hashing_algorithm: String,
    val id: String,
    val image: com.example.cryptolist.details.data.dto.crypto_details_response.Image,
    @SerializedName("last_updated") val last_updated: String,
    val links: com.example.cryptolist.details.data.dto.crypto_details_response.Links,
    val localization: com.example.cryptolist.details.data.dto.crypto_details_response.Localization,
    @SerializedName("market_cap_rank") val market_cap_rank: Int,
    @SerializedName("market_data") val market_data: com.example.cryptolist.details.data.dto.crypto_details_response.MarketData,
    val name: String,
    val platforms: com.example.cryptolist.details.data.dto.crypto_details_response.Platforms,
    @SerializedName("preview_listing") val preview_listing: Boolean,
    @SerializedName("public_notice") val public_notice: Any,
    @SerializedName("sentiment_votes_down_percentage") val sentiment_votes_down_percentage: Double,
    @SerializedName("sentiment_votes_up_percentage") val sentiment_votes_up_percentage: Double,
    @SerializedName("status_updates") val status_updates: List<Any>,
    val symbol: String,
    val tickers: List<com.example.cryptolist.details.data.dto.crypto_details_response.Ticker>,
    @SerializedName("watchlist_portfolio_users") val watchlist_portfolio_users: Int,
    @SerializedName("web_slug") val web_slug: String
) : Response()