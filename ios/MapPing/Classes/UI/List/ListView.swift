//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView, UITableViewDataSource {
    private let tableView = UITableView()
    var data : [Part]

    init() {
        data = []

        super.init(frame: .zero)

        backgroundColor = .white

        //partCellView.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")

        
        tableView.dataSource = self
        addSubview(tableView)
    }
    
    func refresh() {
        tableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let part = data[indexPath.row]
        let view = PartCellView()

        view.configure(partImageName: "part-sensor", title: part.name, subTitle: part.component, coordinates: part.formattedCoordinates, distance: "(distance tbd)")
        return view
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        tableView.pin.all()
        tableView.contentInset.top = 32
    }
}
