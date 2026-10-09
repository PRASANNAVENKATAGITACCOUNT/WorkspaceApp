package com.learning.chat.data

data class User(val id: Int)


/*
* User -> Chat List (friends, Group) -> Chat detials(messages)
*
* DB Mapping
* List Of User (id, name, phoneNumber, email)
* User -> List Of -> Connection(connectionId, List<ConnectedUserId>) ->
 -> List Of Conversation(conversation id, message:String, data:(Object(Files, Images)))
*
* */
