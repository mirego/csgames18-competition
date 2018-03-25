//
//  ListViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import CoreLocation

class ListViewController: BaseViewController, UITableViewDelegate, UITableViewDataSource, CLLocationManagerDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: PartCellView = PartCellView()
        
        var part = self.data[indexPath.row]
        
        let partCellView = PartCellView()

        if let longitude = part.longitude {
            
            var distance = "unkown"
            
            if self.last_location != nil{
                
                /***
                 This giant behemoth creates 2 CLLocation objects from the latitudes and longitudes, and then calcules the distance in m between them
                 them converts to km, then converts to a string format.
                ***/
                
                distance = (NSString(format: "%.2f", CLLocation(latitude: CLLocationDegrees(part.latitude!), longitude: CLLocationDegrees(part.longitude!)).distance(from: CLLocation(latitude: (self.last_location?.latitude)!, longitude: (self.last_location?.longitude)!))/1000.0) as String) + " km"
            }
            
            // configure cell to have all the relevant information to properly display icon, text, distance, etc.
            cell.configure(partImageName: "part-" + part.type, title: part.name, subTitle: part.notes, coordinates: (NSString(format: "%.2f", part.longitude!) as String) + "° N, " + (NSString(format: "%.2f", part.latitude!) as String) + "° W", distance: distance)
        } else {
            
            // use geo coder to get lat and lon from address when lat and lon not available
            
            if let address = part.address{
                let geoCoder = CLGeocoder()
                geoCoder.geocodeAddressString(address) { (placemarks, error) in
                    guard
                        let placemarks = placemarks,
                        let location = placemarks.first?.location
                        else {
                            return
                    }
                    
                    var distance = "unkown"
                    
                    if self.last_location != nil{
                        distance = (NSString(format: "%.2f", CLLocation(latitude: CLLocationDegrees((self.last_location?.latitude)!), longitude: CLLocationDegrees((self.last_location?.longitude)!)).distance(from: CLLocation(latitude: location.coordinate.latitude, longitude: location.coordinate.longitude))/1000.0) as String) + " km"
                    }
                    
                    cell.configure(partImageName: "part-" + part.type, title: part.name, subTitle: part.notes, coordinates: (NSString(format: "%.2f", location.coordinate.longitude) as String) + "°, " + (NSString(format: "%.2f", location.coordinate.latitude) as String) + "°", distance: distance)
                    
                }
            }
        }
        
        return cell
        
    }
    

    private var tableView: UITableView = UITableView()
    private let partService: PartService
    private var data = [Part]()
    private var last_location: CLLocationCoordinate2D?
    let locationManager = CLLocationManager()

    init(partService: PartService) {
        self.partService = partService
        super.init(nibName: nil, bundle: nil)
        title = "Map Ping"
        self.data = [Part]()
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
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let locValue: CLLocationCoordinate2D = manager.location?.coordinate else { return }
        self.last_location = locValue
        print(locValue)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.locationManager.requestAlwaysAuthorization()
        
        // For use in foreground
        self.locationManager.requestWhenInUseAuthorization()
        
        if CLLocationManager.locationServicesEnabled() {
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
            locationManager.startUpdatingLocation()
        }
        
        self.tableView = UITableView(frame: UIScreen.main.bounds, style: UITableViewStyle.plain)
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        self.view.addSubview(self.tableView)

        _ = partService.partsObservable.register { (_, parts) in
            self.data = parts
        }

    }
}
