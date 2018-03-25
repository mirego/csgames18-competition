//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListView: UIView, UITableViewDataSource {
    //private let partCellView = PartCellView()
    private let tableView = UITableView()
    private var data : [Part]

    init() {
        data = []

        super.init(frame: .zero)
        
        DataHelper.getData { (parts) in
            self.data = parts
            self.tableView.reloadData()
        }

        backgroundColor = .white
        DataHelper.getData()
        
        let testPart = Part(name: "Générateur de flammèches", latitude: 46.7909223, longitude: -71.2873006, type: "generator", notes: "Passer au Bureau 200", address: "2327 Boulevard du Versant N, Ville de Québec, QC G1N 4C2, Canada", component: "Propulsion intergalactique")
        
        data.append(testPart)


        //partCellView.configure(partImageName: "part-sensor", title: "Bougie 4W", subTitle: "Moteur principal", coordinates: "46.7552° N, 71.2265° W", distance: "(0.62 km)")

        
        tableView.dataSource = self
        addSubview(tableView)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let part = data[indexPath.row]
        let view = PartCellView()

<<<<<<< HEAD
        view.configure(partImageName: "part-sensor", title: part.name, subTitle: part.component, coordinates: part.formattedCoordinates, distance: "(idk yet lol)")
        return view
=======
        view.configure(partImageName: "part-sensor", title: part.name, subTitle: part.component, coordinates: part.formattedCoordinates!, distance: "(idk yet lol)")
        return partCellView
>>>>>>> c9d5d873f787ae3abb880a189adcdc01566e8753
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
        tableView.contentInset.top = 64
    }
}
