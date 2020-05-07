package uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_theme.view.*
import uz.mahmudxon.abdullaqahhorhikoyalari.core.lists.viewholder.ThemeViewHoder
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.Prefs
import uz.mahmudxon.abdullaqahhorhikoyalari.core.util.setIconColor
import uz.mahmudxon.abdullaqahhorhikoyalari.ui.base.theme.Theme
import javax.inject.Inject

class ThemeAdapter @Inject constructor() : RecyclerView.Adapter<ThemeViewHoder>() {

    var setOnThemeChangeListener: INotifyThemeChange? = null

    @Inject
    lateinit var prefs: Prefs

    val data = ArrayList<Theme>()

    fun swapData(data: ArrayList<Theme>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ThemeViewHoder(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ThemeViewHoder, position: Int) {
        holder.itemView.apply {
            val currentId = prefs.get(prefs.theme, Theme.THEME_CLASSIC)
            val theme = data[position]
            val primaryDark = ContextCompat.getColor(context, theme.primaryColorDark)
            val secondaryText = ContextCompat.getColor(context, theme.secondaryTextColor)
            //background_layout?.setBackgroundColor(primaryDark)
            background_card?.setCardBackgroundColor(primaryDark)
            checked?.setIconColor(secondaryText)
            checked?.visibility = if (theme.id == currentId) View.VISIBLE else View.GONE
            setOnClickListener {
                prefs.save(prefs.theme, theme.id)
                if (theme.id != Theme.THEME_NIGHT)
                    prefs.save(prefs.stockTheme, theme.id)
                setOnThemeChangeListener?.onChangeTheme()
                notifyDataSetChanged()
            }
        }
    }

    interface INotifyThemeChange {
        fun onChangeTheme()
    }
}