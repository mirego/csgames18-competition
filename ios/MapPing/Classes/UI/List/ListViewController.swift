//
//  ListViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import CoreLocation

class ListViewController: BaseViewController {

    // Static member to store any location updates to.
    static var location:CLLocation?
    
    var locationManager:CLLocationManager?
    
    private var mainView: ListView {
        return self.view as! ListView
    }

    private let partService: PartService

    init(partService: PartService) {
        self.partService = partService
        super.init(nibName: nil, bundle: nil)
        title = "Map Ping"
        navigationIcon = #imageLiteral(resourceName: "icn-list")
        
        // Prepare location manager to get the user's current location.
        if CLLocationManager.locationServicesEnabled() {
            locationManager = CLLocationManager()
            locationManager?.delegate = self
            locationManager?.desiredAccuracy = kCLLocationAccuracyBest
            locationManager?.startUpdatingLocation()
        }
        
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
        }
    }
}

// Extension to handle CLLocationManagerDelegate functions.
extension ListViewController: CLLocationManagerDelegate {
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = locations.last! as CLLocation
        ListViewController.location = location
    }
}
