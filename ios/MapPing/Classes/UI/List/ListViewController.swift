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
            
            for part in parts {
                
            self.mainView.insert(partImageName: "part-"+part.type, title: part.name, subTitle: part.component,
                                 coordinates: "\(part.latitude ?? 0.0),\(part.longitude ?? 0.0)",
                distance: "20m")
            }
        }
    }
}
