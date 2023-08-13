package com.example.achievements_app

object MyBadges {

    private var id: Int = 0
    private val badges: MutableList<ItemData> = mutableListOf()


    init {
        badges.add(ItemData(id++, R.drawable.hundred_hours, "100 hours in Android Dev!"))
        badges.add(ItemData(id++, R.drawable.yandex_logo, "Completed Yandex Lecturerium!"))
    }

    fun getAllBadges(): List<ItemData> = badges
}