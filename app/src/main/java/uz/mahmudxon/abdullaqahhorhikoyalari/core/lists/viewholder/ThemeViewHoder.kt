package uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mahmudxon.abdullaqahhorhikoyalari.R

class ThemeViewHoder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_item_theme, parent, false
    )
)