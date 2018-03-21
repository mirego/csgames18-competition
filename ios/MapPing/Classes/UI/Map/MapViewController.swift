//
//  MapViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class MapViewController: BaseViewController {

    private var mainView: MapView {
        return self.view as! MapView
    }

    init() {
        super.init(nibName: nil, bundle: nil)
        navigationIcon = UIImage(named: "icn-map")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = MapView()
    }
}
