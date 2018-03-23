//
//  AugmentedRealityViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import ARKit

class AugmentedRealityViewController: BaseViewController {

    private var mainView: ARSKView {
        return self.view as! ARSKView
    }

    init() {
        super.init(nibName: nil, bundle: nil)
        navigationIcon = #imageLiteral(resourceName: "icn-ar")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = ARSKView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        let scene = AugmentedRealityScene(size: view.bounds.size)
        mainView.delegate = self
        mainView.presentScene(scene)
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let configuration = ARWorldTrackingConfiguration()
        mainView.session.run(configuration)
    }

    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        mainView.session.pause()
    }
}

extension AugmentedRealityViewController: ARSKViewDelegate {
    func session(_ session: ARSession, didFailWithError error: Error) {
    }

    func sessionWasInterrupted(_ session: ARSession) {
    }

    func sessionInterruptionEnded(_ session: ARSession) {
        mainView.session.run(session.configuration!, options: [.resetTracking, .removeExistingAnchors])
    }

    func view(_ view: ARSKView, nodeFor anchor: ARAnchor) -> SKNode? {
        let bug = SKSpriteNode(imageNamed: "bug")
        bug.name = "bug"
        return bug
    }
}
