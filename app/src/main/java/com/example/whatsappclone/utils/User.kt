package com.example.whatsappclone.utils

data class User(
    val email: String? = "",    //Model merupakan layer yang menunjuk pada objek
    val phone: String? = "",    // dan data yang ada pada aplikasi sehingga User
    val name: String? = "",     //disini akan  memiliki data" disamping
    val imageUrl: String? = "",
    val status: String? = "",
    val statusUrl: String? = "",
    val statusTime: String? = ""
)

data class Contact(
    val name: String?,
    val phone: String?
)



data class Chat(
    val chatParticipants: ArrayList<String>
)

data class Message(
    val sentBy: String? = "",
    val message: String? = "",
    val messageTime: Long? = 0
)