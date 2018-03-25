//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView,UITableViewDataSource, UITableViewDelegate {
    
    
    
    var partsInListView = [Part]()
    private let partCellView = PartCellView()
    let tableView = UITableView()

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

//        partCellView.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")
//        addSubview(partCellView)
        addSubview(tableView)
        tableView.register(PartCellView.self, forCellReuseIdentifier: "cell")
        tableView.delegate = self
        tableView.dataSource = self
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        tableView.pin.all()
    }
    
    
        func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
            return partsInListView.count
        }

        func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
            let lat = "\(partsInListView[indexPath.row].latitude ?? 0.0)"
            let lon = "\(partsInListView[indexPath.row].longitude ?? 0.0)"
            let coordinate = lat + " " + lon
            let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! PartCellView
            cell.configure(partImageName: partsInListView[indexPath.row].name, title: partsInListView[indexPath.row].component, subTitle: partsInListView[indexPath.row].type, coordinates: coordinate , distance: "(0.62 km)")
            
            return cell
        }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 150
    }
}
