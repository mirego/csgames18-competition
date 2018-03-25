//
//  ListView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import CoreLocation

class ListView: UIView, UITableViewDataSource, CLLocationManagerDelegate {
    private let tableView = UITableView()
    var data : [Part]
    let locationManager : CLLocationManager
    var location : CLLocation?

    init() {
        data = []
        locationManager = CLLocationManager()
        
        super.init(frame: .zero)
        locationManager.delegate = self
        locationManager.requestWhenInUseAuthorization()
        locationManager.startMonitoringSignificantLocationChanges()

        backgroundColor = .white

        tableView.dataSource = self
        tableView.register(PartCellView.self, forCellReuseIdentifier: "cell")
        addSubview(tableView)
    }

    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = locations.last!
        
        let date = location.timestamp
        let howRecent = date.timeIntervalSinceNow
        
        // If relatively recent
        if (abs(howRecent) < 15) {
            print("Long: \(location.coordinate.longitude) lat: \(location.coordinate.latitude)")
            self.location = location
            DispatchQueue.main.async { self.refresh() }
        }
    }

    func refresh() {
        tableView.reloadData()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let part = data[indexPath.row]
        let view = tableView.dequeueReusableCell(withIdentifier: "cell") as? PartCellView ?? PartCellView()
        let locstring : String
        
        if let location = location {
            if let lat = part.latitude, let long = part.longitude {
                let partLocation = CLLocation(latitude: CLLocationDegrees(lat), longitude: CLLocationDegrees(long))
                
                let distance = location.distance(from: partLocation)
                locstring = "(\(distance))"
            }
            else {
                locstring = "(unavailable)"
            }
        }
        else {
            locstring = "(unavailable)"
        }
        
        view.configure(partImageName: "part-\(part.type)", title: part.name, subTitle: part.component, coordinates: part.formattedCoordinates, distance: locstring)
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
