package uz.mahmudxon.abdullaqahhorhikoyalari.core.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Story(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var additional: String?,
    var epic: String?,
    var authEpic: String?,
    var story: String,
    var year: Int?
)