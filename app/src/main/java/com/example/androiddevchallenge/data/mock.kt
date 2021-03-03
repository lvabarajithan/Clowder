package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R

val cats = arrayListOf(
    Cat(
        1,
        "Lizzy",
        2,
        "Lizzy is a dull cat. Eats more and sleeps often. She's a good girl who always listens to you.",
        arrayListOf(Tag.FRIENDLY, Tag.LAZY),
        "Persian Cat",
        "100",
        R.drawable.persian_cat
    ),
    Cat(
        2,
        "Laura",
        3,
        "Laura is an active girl. She's always on the run and ready to play anytime. She's friendly but won't listen to you all time :D",
        arrayListOf(Tag.FRIENDLY, Tag.FURIOUS),
        "Bengal Cat",
        "100",
        R.drawable.bengal_cat
    ),
    Cat(
        3,
        "Pearl",
        1,
        "Pearl is a cute looking girl. She's a foodie and listens to you everytime.",
        arrayListOf(Tag.FRIENDLY, Tag.CUTE),
        "British Shorthair",
        "100",
        R.drawable.british_shorthair
    ),
    Cat(
        4,
        "Selina",
        3,
        "Selina is the most friendliest cat. She has the most soft feature.",
        arrayListOf(Tag.FRIENDLY, Tag.CUTE),
        "Ragdoll Cat",
        "100",
        R.drawable.ragdoll
    ),
    Cat(
        5,
        "Tilly",
        1,
        "Tilly is a boy who might command you. He's lazy and keeps staring at objects everytime.",
        arrayListOf(
            Tag.MASTER, Tag.LAZY
        ),
        "Scottish Fold",
        "100",
        R.drawable.scottish_fold
    ),
    Cat(
        6,
        "Oggy",
        4,
        "Oggy is a good boy. Doesn't break anything in your home. Always listens to you and has a cute looking face all the time.",
        arrayListOf(Tag.FRIENDLY, Tag.CUTE),
        "Russian Blue",
        "100",
        R.drawable.russian_blue
    ),
    Cat(
        7,
        "Otis",
        0,
        "Otis is the most cutest of all. He's just born and is adorable. He's your lazy baby.",
        arrayListOf(Tag.FRIENDLY, Tag.CUTE, Tag.LAZY),
        "Munchkin Cat",
        "100",
        R.drawable.munchkin_cat
    ),
    Cat(
        8,
        "Dexter",
        1,
        "Dexter is naughty. He always keeps playing and jumping. Might defend your commands",
        arrayListOf(Tag.FURIOUS, Tag.CUTE, Tag.MASTER),
        "Singapura Cat",
        "100",
        R.drawable.singapura_cat
    ),
    Cat(
        9,
        "T-Bone",
        2,
        "TBone is the most furious cat. He's his own master. Human babies are his only friends",
        arrayListOf(Tag.FURIOUS, Tag.MASTER),
        "Burmese Cat",
        "100",
        R.drawable.burmese_cat
    ),
    Cat(
        10,
        "Gator",
        1,
        "Gator is a sad cat. He's looks so cute when he sits folding his orange tail. He's also lazy and doesn't wake up quickly.",
        arrayListOf(Tag.FRIENDLY, Tag.LAZY, Tag.CUTE),
        "Van Cat",
        "100",
        R.drawable.van_cat
    )
)

data class Cat(
    val id: Int,
    val name: String,
    val age: Int,
    val description: String,
    val tag: List<Tag>,
    val breed: String,
    val price: String,
    val imageId: Int
)

enum class Tag(val type: String) {
    FRIENDLY("Friendly"), LAZY("Lazy"), CUTE("Cute"), FURIOUS("Furious"), MASTER("The Master")
}

fun Cat.ageString(): String {
    return when (age) {
        0 -> "Just born"
        1 -> "1 year old"
        else -> "$age years old"
    }
}