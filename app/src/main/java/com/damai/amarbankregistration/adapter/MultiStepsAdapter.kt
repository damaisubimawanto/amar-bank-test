package com.damai.amarbankregistration.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.damai.amarbankregistration.R
import com.damai.amarbankregistration.databinding.ItemRecyclerMultiStepsBinding
import com.damai.base.extension.gone
import com.damai.base.extension.visible
import com.damai.domain.models.MultiStepsModel

/**
 * Created by damai007 on 05/July/2023
 */
class MultiStepsAdapter(
    private var dataList: List<MultiStepsModel>
) : RecyclerView.Adapter<MultiStepsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRecyclerMultiStepsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recycler_multi_steps,
            parent,
            false
        )
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            data = dataList[position],
            position = position
        )
    }

    override fun getItemCount(): Int = dataList.size

    //region Public Functions
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDataList: List<MultiStepsModel>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

    fun getData() = dataList
    //endregion `Public Functions`

    inner class ViewHolder(
        private val binding: ItemRecyclerMultiStepsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: MultiStepsModel,
            position: Int
        ) {
            with(binding) {
                tvName.text = data.name
                ivIcon.setImageResource(data.svgIconRes)
                ivIconTinted.setImageResource(data.svgIconRes)

                /* Show / hide on the selected and unselected step. */
                if (data.isSelected) {
                    val selectedColor = ContextCompat.getColor(
                        ivIcon.context,
                        R.color.light_sky_blue
                    )
                    tvName.setTextColor(selectedColor)

                    ivIcon.gone()
                    ivIconTinted.visible()
                } else {
                    val unselectedColor = ContextCompat.getColor(
                        ivIcon.context,
                        R.color.black
                    )
                    tvName.setTextColor(unselectedColor)

                    ivIcon.visible()
                    ivIconTinted.gone()
                }

                /* Show / hide the connector line. */
                when (position) {
                    0 -> {
                        viewConnectorLineLeft.gone()
                        viewConnectorLineRight.visible()
                    }
                    itemCount - 1 -> {
                        viewConnectorLineLeft.visible()
                        viewConnectorLineRight.gone()
                    }
                    else -> {
                        viewConnectorLineLeft.visible()
                        viewConnectorLineRight.visible()
                    }
                }
            }
        }
    }
}