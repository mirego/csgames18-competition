//
//  PartAnnotation.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PartAnnotation: NSObject, MKAnnotation {
    let coordinate: CLLocationCoordinate2D
    let iconName: String
    let name: String
    
    var title: String? {
        return name
    }

    init(part: Part) {
        self.coordinate = CLLocationCoordinate2DMake(part.latitude, part.longitude)
        iconName = "part-\(part.type)"
        self.name = part.name
    }
}
