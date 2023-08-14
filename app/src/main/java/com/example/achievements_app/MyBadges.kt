package com.example.achievements_app

object MyBadges {

    private var id: Int = 0
    private val badges: MutableList<ItemData> = mutableListOf()

    init {
        badges.add(
            ItemData(
                id++,
                R.drawable.uni_2,
                "Subject at the RTU_MIREA",
                "The subject at uni has been passed. It's the first goal that I've achieved.\nEstimated time: 108 h.",
                "8th June, 2023"
            )
        )
        badges.add(
            ItemData(
                id++,
                R.drawable.hundred_hours,
                "100 hours",
                "This badge gain for spending first 100 hours in Mobile Development.",
                "28th May, 2023"
            )
        )
        badges.add(
            ItemData(
                id++,
                R.drawable.todo_app_logo,
                "ToDoApp",
                "The first completed project that saw release after two months of development.\nMade for completing Yandex.Lecturerium.\nDates: 11th June - 14th August.",
                "14th August, 2023"
            )
        )
        badges.add(
            ItemData(
                id++,
                R.drawable.yandex_logo,
                "Yandex.Lecturerium",
                "Open Yandex.Lecturerium has been at 6th June through 27th July. There are watched\n28 lectures of Android Development.",
                "10th August, 2023"
            )
        )
    }

    fun getAllBadges(): List<ItemData> = badges
}