package com.mindstix.login.models

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * This unit test exercises the OfflineScreenDataModel class.
 */
class OfflineScreenDataModelTest {

    @Test
    fun `create OfflineScreenDataModel with default values`() {
        // When
        val offlineScreenDataModel = OfflineScreenDataModel.emptyValue

        // Then
        assertEquals("", offlineScreenDataModel.title)
        assertEquals("", offlineScreenDataModel.subTitle)
        assertEquals("", offlineScreenDataModel.imageUrl)
        assertEquals("", offlineScreenDataModel.offlineCTALabel)
    }

    @Test
    fun `create OfflineScreenDataModel with custom values`() {
        // Given
        val title = "Title"
        val subTitle = "Subtitle"
        val imageUrl = "image/image.jpg"
        val offlineCTALabel = "Retry"

        // When
        val offlineScreenDataModel = OfflineScreenDataModel(
            title = title,
            subTitle = subTitle,
            imageUrl = imageUrl,
            offlineCTALabel = offlineCTALabel,
        )

        // Then
        assertEquals(title, offlineScreenDataModel.title)
        assertEquals(subTitle, offlineScreenDataModel.subTitle)
        assertEquals(imageUrl, offlineScreenDataModel.imageUrl)
        assertEquals(offlineCTALabel, offlineScreenDataModel.offlineCTALabel)
    }
}
