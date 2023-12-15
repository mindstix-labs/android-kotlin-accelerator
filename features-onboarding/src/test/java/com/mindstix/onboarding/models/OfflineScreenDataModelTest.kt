/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.models

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * This unit test exercises the OfflineScreenDataModel class.
 *
 * @author Abhishek Singh
 */
class OfflineScreenDataModelTest {
    /**
     * This test verifies that creating an OfflineScreenDataModel using the emptyValue companion object
     * results in a model with default values for title, subTitle, imageUrl, and offlineCTALabel.
     */
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

    /**
     * This test verifies that creating an OfflineScreenDataModel with custom values for title, subTitle, imageUrl, and offlineCTALabel
     * results in a model with the specified values.
     */
    @Test
    fun `create OfflineScreenDataModel with custom values`() {
        // Given
        val title = "Title"
        val subTitle = "Subtitle"
        val imageUrl = "image/image.jpg"
        val offlineCTALabel = "Retry"

        // When
        val offlineScreenDataModel =
            OfflineScreenDataModel(
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
