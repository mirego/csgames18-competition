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

    private let partService: PartService

    init(partService: PartService) {
        self.partService = partService
        super.init(nibName: nil, bundle: nil)
        title = "Map Ping"
        navigationIcon = #imageLiteral(resourceName: "icn-list")
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

    override func viewDidLoad() {
        super.viewDidLoad()

        _ = partService.partsObservable.register { (_, parts) in
            print("Nb of parts received: \(parts.count)")
            
            self.mainView.configure(parts: parts)
        }
    }
}
