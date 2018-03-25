//
//  ListViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListViewController: BaseViewController {

//    var partsInListView = [Part]()
//    let tableView = UITableView()
//    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        return partsInListView.count
//    }
//    
//    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let lat = String(describing: partsInListView[indexPath.row].latitude)
//        let lon = String(describing: partsInListView[indexPath.row].longitude)
//        let coordinate = lat + " " + lon
//        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! PartCellView
//        cell.configure(partImageName: partsInListView[indexPath.row].name, title: partsInListView[indexPath.row].component, subTitle: partsInListView[indexPath.row].type, coordinates: coordinate, distance: "(0.62 km)")
//        
//    }
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
        
        self.partService.refreshParts()
        _ = partService.partsObservable.register { (_, parts) in
            print("Nb of parts received: \(parts.count)")
            (self.view as? ListView)!.partsInListView = parts
           (self.view as? ListView)!.tableView.reloadData()
        }
    }
    
    
}
