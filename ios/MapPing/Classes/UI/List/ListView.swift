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


        
        tableView.dataSource = self
        tableView.register(PartCellView.self, forCellReuseIdentifier: "cell")
        addSubview(tableView)
    }
    
    func refresh() {
        tableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let part = data[indexPath.row]
        let view = tableView.dequeueReusableCell(withIdentifier: "cell") as? PartCellView ?? PartCellView()

        view.configure(partImageName: "part-\(part.type)", title: part.name, subTitle: part.component, coordinates: part.formattedCoordinates, distance: "(distance tbd)")
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
        tableView.contentInset.top = 16
        tableView.rowHeight = 100
    }
}
