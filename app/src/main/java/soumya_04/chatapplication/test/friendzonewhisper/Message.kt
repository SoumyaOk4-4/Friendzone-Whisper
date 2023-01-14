package soumya_04.chatapplication.test.friendzonewhisper

class Message {
    var message : String? = null
    var senderId: String? = null

    constructor(){}

    constructor(message: String?, senderId: String?){
        this.message = message
        this.senderId = senderId
    }
}