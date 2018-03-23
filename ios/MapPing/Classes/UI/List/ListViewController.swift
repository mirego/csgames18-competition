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
        navigationIcon = #imageLiteral(resourceName: "icn-list")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = ListView()
    }
}

