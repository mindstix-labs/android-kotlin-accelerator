package com.mindstix.login.viewModels

import com.mindstix.core.utils.emptyValue

data class OfflineErrorModel(
    var title: String,
    var subTitle: String,
    var imageUrl: String,
    var offlineCTALabel: String = "", // Optional field
) {
    companion object {
        val emptyValue = OfflineErrorModel(
            title = String.emptyValue(),
            subTitle = String.emptyValue(),
            imageUrl = String.emptyValue(),
            offlineCTALabel = String.emptyValue(),
        )
    }
}
