package com.example.whatsappclone.listener

interface ChatsClickListener {
    fun onChatClicked(
    chatId: String?,
    otheruserId: String?,
    chatsImageUrl: String?,
    chatsName: String?
    )
}