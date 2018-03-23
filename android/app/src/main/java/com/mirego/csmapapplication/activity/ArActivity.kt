/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mirego.csmapapplication.activity

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast

import com.google.ar.core.Anchor
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Plane
import com.google.ar.core.Point
import com.google.ar.core.Point.OrientationMode
import com.google.ar.core.Session
import com.google.ar.core.TrackingState
import com.google.ar.core.exceptions.UnavailableApkTooOldException
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException
import com.google.ar.core.exceptions.UnavailableSdkTooOldException
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException
import com.mirego.csmapapplication.R
import com.mirego.csmapapplication.helper.CameraPermissionHelper
import com.mirego.csmapapplication.helper.DisplayRotationHelper
import com.mirego.csmapapplication.rendering.BackgroundRenderer
import com.mirego.csmapapplication.rendering.ObjectRenderer
import com.mirego.csmapapplication.rendering.PlaneRenderer
import com.mirego.csmapapplication.rendering.PointCloudRenderer
import kotlinx.android.synthetic.main.activity_ar.surfaceView

import java.io.IOException
import java.util.ArrayList
import java.util.concurrent.ArrayBlockingQueue

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * This is a simple example that shows how to create an augmented reality (AR) application using the
 * ARCore API. The application will display any detected planes and will allow the user to tap on a
 * plane to place a 3d model of the Android robot.
 */
class ArActivity : AppCompatActivity(), GLSurfaceView.Renderer {

    // Rendering. The Renderers are created here, and initialized when the GL surface is created.

    private var installRequested: Boolean = false

    private var session: Session? = null
    private var gestureDetector: GestureDetector? = null
    private var messageSnackbar: Snackbar? = null
    private var displayRotationHelper: DisplayRotationHelper? = null

    private val backgroundRenderer = BackgroundRenderer()
    private val virtualObject = ObjectRenderer()
    private val virtualObjectShadow = ObjectRenderer()
    private val planeRenderer = PlaneRenderer()
    private val pointCloud = PointCloudRenderer()

    // Temporary matrix allocated here to reduce number of allocations for each frame.
    private val anchorMatrix = FloatArray(16)

    // Tap handling and UI.
    private val queuedSingleTaps = ArrayBlockingQueue<MotionEvent>(16)
    private val anchors = ArrayList<Anchor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)
        displayRotationHelper = DisplayRotationHelper(/*context=*/this)

        // Set up tap listener.
        gestureDetector = GestureDetector(
            this,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    onSingleTap(e)
                    return true
                }

                override fun onDown(e: MotionEvent): Boolean {
                    return true
                }
            })

        surfaceView!!.setOnTouchListener { v, event -> gestureDetector!!.onTouchEvent(event) }

        // Set up renderer.
        surfaceView!!.preserveEGLContextOnPause = true
        surfaceView!!.setEGLContextClientVersion(2)
        surfaceView!!.setEGLConfigChooser(8, 8, 8, 8, 16, 0) // Alpha used for plane blending.
        surfaceView!!.setRenderer(this)
        surfaceView!!.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY

        installRequested = false
    }

    override fun onResume() {
        super.onResume()

        if (session == null) {
            var exception: Exception? = null
            var message: String? = null
            try {
                when (ArCoreApk.getInstance().requestInstall(this, !installRequested)) {
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED -> {
                        installRequested = true
                        return
                    }
                    ArCoreApk.InstallStatus.INSTALLED -> {
                    }
                    else -> {
                    }
                }

                // ARCore requires camera permissions to operate. If we did not yet obtain runtime
                // permission on Android M and above, now is a good time to ask the user for it.
                if (!CameraPermissionHelper.hasCameraPermission(this)) {
                    CameraPermissionHelper.requestCameraPermission(this)
                    return
                }

                session = Session(/* context= */this)
            } catch (e: UnavailableArcoreNotInstalledException) {
                message = "Please install ARCore"
                exception = e
            } catch (e: UnavailableUserDeclinedInstallationException) {
                message = "Please install ARCore"
                exception = e
            } catch (e: UnavailableApkTooOldException) {
                message = "Please update ARCore"
                exception = e
            } catch (e: UnavailableSdkTooOldException) {
                message = "Please update this app"
                exception = e
            } catch (e: Exception) {
                message = "This device does not support AR"
                exception = e
            }

            if (message != null) {
                showSnackbarMessage(message, true)
                Log.e(TAG, "Exception creating session", exception)
                return
            }

            // Create default config and check if supported.
            val config = Config(session!!)
            if (!session!!.isSupported(config)) {
                showSnackbarMessage("This device does not support AR", true)
            }
            session!!.configure(config)
        }

        showLoadingMessage()
        // Note that order matters - see the note in onPause(), the reverse applies here.
        session!!.resume()
        surfaceView!!.onResume()
        displayRotationHelper!!.onResume()
    }

    public override fun onPause() {
        super.onPause()
        if (session != null) {
            // Note that the order matters - GLSurfaceView is paused first so that it does not try
            // to query the session. If Session is paused before GLSurfaceView, GLSurfaceView may
            // still call session.update() and get a SessionPausedException.
            displayRotationHelper!!.onPause()
            surfaceView!!.onPause()
            session!!.pause()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        results: IntArray
    ) {
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(
                this,
                "Camera permission is needed to run this application",
                Toast.LENGTH_LONG
            )
                .show()
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this)
            }
            finish()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            // Standard Android full-screen functionality.
            window
                .decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    private fun onSingleTap(e: MotionEvent) {
        // Queue tap if there is space. Tap is lost if queue is full.
        queuedSingleTaps.offer(e)
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f)

        // Create the texture and pass it to ARCore session to be filled during update().
        backgroundRenderer.createOnGlThread(/*context=*/this)

        // Prepare the other rendering objects.
        try {
            virtualObject.createOnGlThread(/*context=*/this, "radiator.obj", "andy_shadow.png")
            virtualObject.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f)

            virtualObjectShadow.createOnGlThread(/*context=*/this,
                "andy_shadow.obj",
                "andy_shadow.png"
            )
            virtualObjectShadow.setBlendMode(ObjectRenderer.BlendMode.Shadow)
            virtualObjectShadow.setMaterialProperties(1.0f, 0.0f, 0.0f, 1.0f)
        } catch (e: IOException) {
            Log.e(TAG, "Failed to read obj file")
        }

        try {
            planeRenderer.createOnGlThread(/*context=*/this, "trigrid.png")
        } catch (e: IOException) {
            Log.e(TAG, "Failed to read plane texture")
        }

        pointCloud.createOnGlThread(/*context=*/this)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        displayRotationHelper!!.onSurfaceChanged(width, height)
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10) {
        // Clear screen to notify driver it should not load any pixels from previous frame.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        if (session == null) {
            return
        }
        // Notify ARCore session that the view size changed so that the perspective matrix and
        // the video background can be properly adjusted.
        displayRotationHelper!!.updateSessionIfNeeded(session)

        try {
            session!!.setCameraTextureName(backgroundRenderer.textureId)

            // Obtain the current frame from ARSession. When the configuration is set to
            // UpdateMode.BLOCKING (it is by default), this will throttle the rendering to the
            // camera framerate.
            val frame = session!!.update()
            val camera = frame.camera

            // Handle taps. Handling only one tap per frame, as taps are usually low frequency
            // compared to frame rate.

            val tap = queuedSingleTaps.poll()
            if (tap != null && camera.trackingState == TrackingState.TRACKING) {
                for (hit in frame.hitTest(tap!!)) {
                    // Check if any plane was hit, and if it was hit inside the plane polygon
                    val trackable = hit.trackable
                    // Creates an anchor if a plane or an oriented point was hit.
                    if (((trackable is Plane && (trackable as Plane).isPoseInPolygon(hit.hitPose)) || ((trackable is Point && ((trackable as Point).orientationMode == OrientationMode.ESTIMATED_SURFACE_NORMAL))))) {
                        // Hits are sorted by depth. Consider only closest hit on a plane or oriented point.
                        // Cap the number of objects created. This avoids overloading both the
                        // rendering system and ARCore.
                        if (anchors.size >= 20) {
                            anchors[0].detach()
                            anchors.removeAt(0)
                        }
                        // Adding an Anchor tells ARCore that it should track this position in
                        // space. This anchor is created on the Plane to place the 3D model
                        // in the correct position relative both to the world and to the plane.
                        anchors.add(hit.createAnchor())
                        break
                    }
                }
            }

            // Draw background.
            backgroundRenderer.draw(frame)

            // If not tracking, don't draw 3d objects.
            if (camera.trackingState == TrackingState.PAUSED) {
                return
            }

            // Get projection matrix.
            val projmtx = FloatArray(16)
            camera.getProjectionMatrix(projmtx, 0, 0.1f, 100.0f)

            // Get camera matrix and draw.
            val viewmtx = FloatArray(16)
            camera.getViewMatrix(viewmtx, 0)

            // Compute lighting from average intensity of the image.
            val lightIntensity = frame.lightEstimate.pixelIntensity

            // Visualize tracked points.
            val pointCloud = frame.acquirePointCloud()
            this.pointCloud.update(pointCloud)
            this.pointCloud.draw(viewmtx, projmtx)

            // Application is responsible for releasing the point cloud resources after
            // using it.
            pointCloud.release()

            // Check if we detected at least one plane. If so, hide the loading message.
            if (messageSnackbar != null) {
                for (plane in session!!.getAllTrackables(Plane::class.java)) {
                    if ((plane.type == com.google.ar.core.Plane.Type.HORIZONTAL_UPWARD_FACING && plane.trackingState == TrackingState.TRACKING)) {
                        hideLoadingMessage()
                        break
                    }
                }
            }

            // Visualize planes.
            planeRenderer.drawPlanes(
                session!!.getAllTrackables(Plane::class.java), camera.displayOrientedPose, projmtx
            )

            // Visualize anchors created by touch.
            val scaleFactor = 2f
            for (anchor in anchors) {
                if (anchor.trackingState != TrackingState.TRACKING) {
                    continue
                }
                // Get the current pose of an Anchor in world space. The Anchor pose is updated
                // during calls to session.update() as ARCore refines its estimate of the world.
                anchor.pose.toMatrix(anchorMatrix, 0)

                // Update and draw the model and its shadow.
                virtualObject.updateModelMatrix(anchorMatrix, scaleFactor)
                virtualObjectShadow.updateModelMatrix(anchorMatrix, scaleFactor)
                virtualObject.draw(viewmtx, projmtx, lightIntensity)
                virtualObjectShadow.draw(viewmtx, projmtx, lightIntensity)
            }

        } catch (t: Throwable) {
            // Avoid crashing the application due to unhandled exceptions.
            Log.e(TAG, "Exception on the OpenGL thread", t)
        }

    }

    private fun showSnackbarMessage(message: String, finishOnDismiss: Boolean) {
        messageSnackbar = Snackbar.make(
            this@ArActivity.findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_INDEFINITE
        )
        messageSnackbar!!.view.setBackgroundColor(-0x40cdcdce)
        if (finishOnDismiss) {
            messageSnackbar!!.setAction(
                "Dismiss"
            ) { messageSnackbar!!.dismiss() }
            messageSnackbar!!.addCallback(
                object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        finish()
                    }
                })
        }
        messageSnackbar!!.show()
    }

    private fun showLoadingMessage() {
        runOnUiThread { showSnackbarMessage("Searching for surfaces...", false) }
    }

    private fun hideLoadingMessage() {
        runOnUiThread {
            if (messageSnackbar != null) {
                messageSnackbar!!.dismiss()
            }
            messageSnackbar = null
        }
    }

    companion object {
        private val TAG = ArActivity::class.java!!.simpleName
    }
}
