package com.mindstix.floatingview.service

import android.annotation.SuppressLint
import android.view.KeyEvent
import com.mindstix.features.floatingview.R
import com.mindstix.floatingview.CloseBubbleBehavior
import com.mindstix.floatingview.FloatingBubbleListener
import com.mindstix.floatingview.helper.NotificationHelper
import com.mindstix.floatingview.helper.ViewHelper
import com.mindstix.floatingview.service.expandable.BubbleBuilder
import com.mindstix.floatingview.service.expandable.ExpandableBubbleService
import com.mindstix.floatingview.service.expandable.ExpandedBubbleBuilder
import com.mindstix.floatingview.view.BubbleComposeView
import com.mindstix.floatingview.view.ExpandedComposeView

/**
 * @author Apoorv Gupta
 */
class FloatingBubbleServiceImpl: ExpandableBubbleService() {

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
    override fun configBubble(): BubbleBuilder? {
        val imgView = ViewHelper.fromDrawable(this, R.drawable.ic_rounded_blue_diamond, 60, 60)

        imgView.setOnClickListener {
            expand()
        }

        return BubbleBuilder(this)

            // set bubble view
//            .bubbleView(imgView)

            .triggerClickablePerimeterPx(5f)

            // or our sweetie, Jetpack Compose
            .bubbleCompose {
                BubbleComposeView(expand = { expand() })
            }
            .forceDragging(false)

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

            .addFloatingBubbleListener(object : FloatingBubbleListener {
                override fun onFingerMove(
                    x: Float,
                    y: Float
                ) {
                } // The location of the finger on the screen which triggers the movement of the bubble.

                override fun onFingerUp(
                    x: Float,
                    y: Float
                ) {
                }   // ..., when finger release from bubble

                override fun onFingerDown(x: Float, y: Float) {} // ..., when finger tap the bubble
            })

    }

    override fun configExpandedBubble(): ExpandedBubbleBuilder? {

//        val expandedView = LayoutInflater.from(this).inflate(R.layout.layout_view_test, null)
//        expandedView.findViewById<View>(R.id.btn).setOnClickListener {
//            minimize()
//        }

        return ExpandedBubbleBuilder(this)
//            .expandedView(expandedView)
            .expandedCompose {
                ExpandedComposeView(popBack = { minimize() })
            }
            .onDispatchKeyEvent {
                if(it.keyCode == KeyEvent.KEYCODE_BACK){
                    minimize()
                }
                null
            }
            .startLocation(0, 0)
            .draggable(true)
            .style(null)
            .fillMaxWidth(false)
            .enableAnimateToEdge(true)
            .dimAmount(0.5f)
    }
}