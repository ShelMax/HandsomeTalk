package kr.sofac.handsometalk.model.server.type

/**
 * Created by Maxim on 03.08.2017.
 */

class ServerResponse<T> {

    var responseStatus: String? = null
    var dataTransferObject: T? = null

    constructor() {}

    constructor(responseStatus: String, dataTransferObject: T) {
        this.responseStatus = responseStatus
        this.dataTransferObject = dataTransferObject
    }

    override fun toString(): String {
        return "ServerResponse{" +
                "responseStatus='" + responseStatus + '\'' +
                ", dataTransferObject=" + dataTransferObject +
                '}'
    }
}