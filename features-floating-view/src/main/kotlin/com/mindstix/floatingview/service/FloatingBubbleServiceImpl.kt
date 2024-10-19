package com.mindstix.floatingview.service

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.mindstix.core.logger.Logger
import com.mindstix.features.floatingview.R
import com.mindstix.floatingview.CloseBubbleBehavior
import com.mindstix.floatingview.FloatingBubbleListener
import com.mindstix.floatingview.helper.NotificationHelper
import com.mindstix.floatingview.helper.ViewHelper
import com.mindstix.floatingview.service.expandable.BubbleBuilder
import com.mindstix.floatingview.service.expandable.ExpandableBubbleService
import com.mindstix.floatingview.view.BubbleComposeView
import com.mindstix.floatingview.view.Product
import com.mindstix.floatingview.view.bitmapState
import javax.inject.Inject

/**
 * @author Abhijeet Kokane
 */
val response = mutableStateOf("")
val suggestedProduct : MutableState<Product?> = mutableStateOf(Product())
val step = mutableIntStateOf(0)
val isGoodChoice = mutableStateOf(false)
class FloatingBubbleServiceImpl@Inject constructor() : ExpandableBubbleService() {

    override fun startNotificationForeground() {
        val noti = NotificationHelper(this)
        noti.createNotificationChannel()
        startForeground(noti.notificationId, noti.defaultNotification())
    }

    override fun onCreate() {
        super.onCreate()
        minimize()
    }

    @SuppressLint("DiscouragedApi")
    override fun configBubble(): BubbleBuilder {
        return BubbleBuilder(this)
            .triggerClickablePerimeterPx(5f)
            .bubbleCompose {
                BubbleComposeView(
                    step = step,
                    text = response,
                    expand = {
                        Logger.d { "at 2" }
//                        step.intValue = 1 //Here
//                        bitmapState.value.let {
//                            Logger.d { "at 1.3 $it" }
//                            it?.let { it1 ->
//                                Logger.d { "at 1.4" }
//                                test(it1) { isGood, responseData ->
//                                    Logger.d { "at 8 result" }
//                                    isGoodChoice.value = isGood
//                                    response.value = responseData
//                                    step.intValue = 2
//                                }
//                            }
//                        }
                    },
                    good = isGoodChoice,
                    popBack = {
                        bitmapState.value = null
                        step.intValue = 0
                        minimize()
                    },
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(suggestedProduct.value?.detailUrl)).apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            it.startActivity(intent)

                        } catch (e: ActivityNotFoundException) {
                            Logger.e("ActivityNotFoundException","Activity not found to handle the URL intent from Profile section")
                        }
                    },
                    suggestedProduct = suggestedProduct
                )
            }
            .forceDragging(true)
            // set style for the bubble, fade animation by default
            .bubbleStyle(null)
            // set start location for the bubble, (x=0, y=0) is the top-left
            .startLocation(100, 100)    // in dp
            .startLocationPx(100, 100)  // in px
            // enable auto animate bubble to the left/right side when release, true by default
            .enableAnimateToEdge(true)
            // set close-bubble view
            .closeBubbleView(ViewHelper.fromDrawable(this, R.drawable.ic_close_bubble, 60, 60))
            // set style for close-bubble, null by default
            .closeBubbleStyle(null)
            // DYNAMIC_CLOSE_BUBBLE: close-bubble moving based on the bubble's location
            // FIXED_CLOSE_BUBBLE (default): bubble will automatically move to the close-bubble when it reaches the closable-area
            .closeBehavior(CloseBubbleBehavior.FIXED_CLOSE_BUBBLE)
            // the more value (dp), the larger closeable-area
            .distanceToClose(100)
            // enable bottom background, false by default
            .bottomBackground(false)
 //======
//            .startLocation(100, 100)    // in dp
//            .startLocationPx(100, 100)
//            .bubbleDraggable(true)
//            .forceDragging(true)
//            // set style for the bubble, fade animation by default
////            .bubbleStyle(null)
//            // set start location for the bubble, (x=0, y=0) is the top-left
////            .startLocation(50, 50) // in dp
////            .startLocationPx(50, 50) // in px
//            // enable auto animate bubble to the left/right side when release, true by default
//            .enableAnimateToEdge(true)
//            // set close-bubble view
////            .closeBubbleView(ViewHelper.fromDrawable(this, R.drawable.ic_close_bubble, 60, 60))
//            // set style for close-bubble, null by default
//            .closeBubbleStyle(null)
//            // DYNAMIC_CLOSE_BUBBLE: close-bubble moving based on the bubble's location
//            // FIXED_CLOSE_BUBBLE (default): bubble will automatically move to the close-bubble when it reaches the closable-area
//            .closeBehavior(CloseBubbleBehavior.FIXED_CLOSE_BUBBLE)
//
//            // the more value (dp), the larger closeable-area
////            .distanceToClose(100)
//            // enable bottom background, false by default
//            .bottomBackground(false)
//

            .addFloatingBubbleListener(
                object : FloatingBubbleListener {
                    override fun onFingerMove(
                        x: Float,
                        y: Float,
                    ) {
                    } // The location of the finger on the screen which triggers the movement of the bubble.

                    override fun onFingerUp(
                        x: Float,
                        y: Float,
                    ) {
                    } // ..., when finger release from bubble

                    override fun onFingerDown(
                        x: Float,
                        y: Float,
                    ) {
                    } // ..., when finger tap the bubble
                },
            )
    }
}
