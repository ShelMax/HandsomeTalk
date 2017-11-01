package kr.sofac.handsometalk.model.server.type

/**
 * Created by Maxim on 03.08.2017.
 */

class ServerRequest<T> {

    var requestType: String? = null
    var dataTransferObject: T? = null

    constructor() {}

    constructor(requestType: String, dataTransferObject: T) {
        this.requestType = requestType
        this.dataTransferObject = dataTransferObject
    }

    override fun toString(): String {
        return "ServerRequest{" +
                "requestType='" + requestType + '\'' +
                ", dataTransferObject=" + dataTransferObject +
                '}'
    }
}
