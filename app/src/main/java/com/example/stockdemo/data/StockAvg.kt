package com.example.stockdemo.data

import com.google.gson.annotations.SerializedName

// 上市個股日收盤價及月平均價
const val ExchangeReportStockDayAvgAll = "v1/exchangeReport/STOCK_DAY_AVG_ALL"

data class StockAvg(
    @SerializedName("Code")
    val code: String,                   //股票代號,
    @SerializedName("Name")
    val name: String,                   //股票名稱,
    @SerializedName("ClosingPrice")
    val closingPrice: String,           //收盤價,
    @SerializedName("MonthlyAveragePrice")
    val monthlyAveragePrice: String,    //月平均價
) {
    override fun equals(other: Any?): Boolean {
        return (
                this.code == (other as StockAvg).code &&
                        this.name == other.name &&
                        this.closingPrice == other.closingPrice &&
                        this.monthlyAveragePrice == other.monthlyAveragePrice
                )
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}
