package com.example.stockdemo.data

import com.google.gson.annotations.SerializedName

// 上市個股日成交資訊
const val ExchangeReportStockDayAll = "v1/exchangeReport/STOCK_DAY_ALL"

data class StockAll(
    @SerializedName("Code")
    val code: String,           //證券代號
    @SerializedName("Name")
    val name: String,           //證券名稱
    @SerializedName("TradeVolume")
    val tradeVolume: String,    //成交股數
    @SerializedName("TradeValue")
    val tradeValue: String,     //成交金額
    @SerializedName("OpeningPrice")
    val openingPrice: String,   //開盤價
    @SerializedName("HighestPrice")
    val highestPrice: String,   //最高價
    @SerializedName("LowestPrice")
    val lowestPrice: String,    //最低價
    @SerializedName("ClosingPrice")
    val closingPrice: String,   //收盤
    @SerializedName("Change")
    val change: String,         //漲跌價差
    @SerializedName("Transaction")
    val transaction: String,    //成交筆數
) {
    override fun equals(other: Any?): Boolean {
        return (
                this.code == (other as StockAll).code &&
                        this.name == other.name &&
                        this.tradeVolume == other.tradeVolume &&
                        this.tradeValue == other.tradeValue &&
                        this.openingPrice == other.openingPrice &&
                        this.highestPrice == other.highestPrice &&
                        this.lowestPrice == other.lowestPrice &&
                        this.closingPrice == other.closingPrice &&
                        this.change == other.change &&
                        this.transaction == other.transaction
                )
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}
