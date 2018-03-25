//
//  LocationService.swift
//  MapPing
//
//  Created by Gabriel T on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation
import CoreLocation

class LocationService: NSObject {
    private static let _instance = LocationService()
    
    static var instance: LocationService {
        return _instance
    }
    
    var locationManager = CLLocationManager()
    
    var lastReportedLocation: CLLocationCoordinate2D?
    
    func getCurrentLocation() -> CLLocationCoordinate2D {
        //todo
        return CLLocationCoordinate2D()
    }
    
    func initiateLocationService() {
        // Ask for Authorisation from the User.
        self.locationManager.requestAlwaysAuthorization()
        
        // For use in foreground
        self.locationManager.requestWhenInUseAuthorization()
        
        if CLLocationManager.locationServicesEnabled() {
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
            locationManager.startUpdatingLocation()
        }
    }
}

extension LocationService: CLLocationManagerDelegate {
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let locValue: CLLocationCoordinate2D = manager.location?.coordinate else { return }
        self.lastReportedLocation = locValue
        print("location:", locValue)
    }
}
