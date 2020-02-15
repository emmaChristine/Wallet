package com.demo.wallet.ui.adapter


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.wallet.R
import com.demo.wallet.data.dto.txs
import com.demo.wallet.ui.utils.formatDate
import com.demo.wallet.ui.utils.longToBTC
import com.demo.wallet.ui.utils.longToBTCWithStatus
import kotlinx.android.synthetic.main.transaction_item_row.view.*
import java.util.*


// transactions list adapter
internal class WalletAdapter(private val activity: Activity) : RecyclerView.Adapter<WalletAdapter.TxsViewHolder>() {

    private var TXSList: List<txs> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxsViewHolder {
        val itemView = LayoutInflater.from(this.activity).inflate(R.layout.transaction_item_row, parent, false)
        return TxsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return TXSList.size
    }

    override fun onBindViewHolder(holder: TxsViewHolder, position: Int) {
        val transaction = TXSList[position]
        holder.setData(transaction)
    }

    fun setData(list: List<txs>) {
        this.TXSList = list
        notifyDataSetChanged()
    }

    inner class TxsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(transaction: txs) {

            itemView.transaction_date.text = formatDate(Date(transaction.time))
            itemView.transaction_fee.text = longToBTC(transaction.fee)
            itemView.transaction_amount.text = longToBTCWithStatus(transaction.result)
        }
    }

}