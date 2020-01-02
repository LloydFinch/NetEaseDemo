package com.devloper.lloydfinch.neteasedemo.provider

class AVFile(var id: String) {
    var name: String = ""
    var path: String = ""
    var duration: Long = 0
    var lastModifiedTime: Long = 0
    var type: TYPE = TYPE.PICTURE
}
