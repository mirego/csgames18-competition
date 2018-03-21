//
//  AugmentedRealityScene.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import SpriteKit
import ARKit

class AugmentedRealityScene: SKScene {

    private var sceneView: ARSKView {
        return view as! ARSKView
    }

    private var isSceneSetup = false

    override init(size: CGSize) {
        super.init(size: size)
        scaleMode = .resizeFill
        anchorPoint = CGPoint(x: 0.5, y: 0.5)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func setUpScene() {
        guard let currentFrame = sceneView.session.currentFrame else { return }

        var translation = matrix_identity_float4x4
        translation.columns.3.z = -0.3
        let transform = currentFrame.camera.transform * translation
        let anchor = ARAnchor(transform: transform)
        sceneView.session.add(anchor: anchor)
        isSceneSetup = true
    }

    override func update(_ currentTime: TimeInterval) {
        if !isSceneSetup {
            setUpScene()
        }
    }
}
