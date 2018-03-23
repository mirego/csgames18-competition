//
//  ListViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListViewController: BaseViewController {

    private var mainView: ListView {
        return self.view as! ListView
    }

    init() {
        super.init(nibName: nil, bundle: nil)
        title = "Map Ping" 
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }

    override func loadView() {
        view = ListView()
    }
}
