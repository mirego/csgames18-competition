//
//  LocationService.swift
//  MapPing
//
//  Created by Gabriel T on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation
import CoreLocation
import Contacts

class LocationService: NSObject {
    private static let _instance = LocationService()
    
    private var initiated = false
    
    static var instance: LocationService {
        return _instance
    }
    
    private var geocoder = CLGeocoder()
    var locationManager = CLLocationManager()
    var lastReportedLocation: CLLocationCoordinate2D?
    
    func initiateLocationService() {
        if !initiated {
            // Ask for Authorisation from the User.
            self.locationManager.requestAlwaysAuthorization()
            
            // For use in foreground
            self.locationManager.requestWhenInUseAuthorization()
            
            if CLLocationManager.locationServicesEnabled() {
                locationManager.delegate = self
                locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
                locationManager.startUpdatingLocation()
            }
            
            initiated = true
        }
    }
    
    func getAddressFromLocation(location: CLLocation, completion: @escaping (String) -> ()) {
        geocoder.reverseGeocodeLocation(location, completionHandler: { (placemarks, error) -> Void in
            if error != nil {
                print("Error getting the reverse geocode location", error!)
            } else {
                let placemark = placemarks![0]
                let address = CNPostalAddressFormatter.string(from: placemark.postalAddress!, style: .mailingAddress)
                completion(address)
            }
        })
    }
    
    func getLocationFromAddress(address: String, completion: @escaping (CLLocation) -> ()) {
        geocoder.geocodeAddressString(address, completionHandler: { (placemarks, error) -> Void in
            if error != nil {
                 print("Error getting the coordinates from the address", error!)
            } else {
                let placemark = placemarks![0]
                completion(placemark.location!)
            }
        })
    }
}

extension LocationService: CLLocationManagerDelegate {
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let locValue: CLLocationCoordinate2D = manager.location?.coordinate else { return }
        self.lastReportedLocation = locValue
        print("location:", locValue)
    }
}
